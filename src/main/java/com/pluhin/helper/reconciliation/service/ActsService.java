package com.pluhin.helper.reconciliation.service;

import com.pluhin.helper.reconciliation.entity.ActsConfig;
import com.pluhin.helper.reconciliation.common.act.Act;
import com.pluhin.helper.reconciliation.common.act.ActItem;
import com.pluhin.helper.reconciliation.common.dto.CheckErrorsDTO;
import com.pluhin.helper.reconciliation.common.exception.InvalidFileException;
import com.pluhin.helper.reconciliation.processor.XlsProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ActsService {

  private final ActsConfigService configService;
  private final XlsProcessor xlsProcessor;

  @Autowired
  public ActsService(ActsConfigService configService,
      XlsProcessor xlsProcessor) {
    this.configService = configService;
    this.xlsProcessor = xlsProcessor;
  }

  public CheckErrorsDTO doReconciliation(MultipartFile firstFile, MultipartFile secondFile) {
    Workbook firstWorkbook = getWorkbook(firstFile);
    Workbook secondWorkbook = getWorkbook(secondFile);

    ActsConfig firstConfig = configService.getConfig(firstFile.getName());
    ActsConfig secondConfig = configService.getConfig(secondFile.getName());

    Act firstAct = xlsProcessor.processWorkbook(firstWorkbook, firstConfig);
    Act secondAct = xlsProcessor.processWorkbook(secondWorkbook, secondConfig);

    return checkForErrors(firstAct, secondAct);
  }

  private CheckErrorsDTO checkForErrors(Act firstAct, Act secondAct) {
    List<ActItem> errors = checkForErrors(firstAct.getCredit(), secondAct.getDebit());
    errors.addAll(checkForErrors(secondAct.getDebit(), firstAct.getCredit()));
    errors.addAll(checkForErrors(firstAct.getDebit(), secondAct.getCredit()));
    errors.addAll(checkForErrors(secondAct.getCredit(), firstAct.getDebit()));

    CheckErrorsDTO errorsDTO = new CheckErrorsDTO();
    errorsDTO.setErrors(errors);
    return errorsDTO;
  }

  private List<ActItem> checkForErrors(List<ActItem> first, List<ActItem> compareTo) {
    List<ActItem> errors = new ArrayList<>(first);
    compareTo.forEach(errors::remove);
    return errors;
  }

  private Workbook getWorkbook(MultipartFile file) {
    try {
      return WorkbookFactory.create(file.getInputStream());
    } catch (IOException e) {
      log.error("Unable to open act");
      throw new InvalidFileException();
    } catch (InvalidFormatException e) {
      throw new InvalidFileException(e);
    }
  }
}
