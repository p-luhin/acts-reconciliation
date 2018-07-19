package com.pluhin.helper.reconciliation.web.service;

import com.pluhin.helper.reconciliation.common.constants.ErrorConstants;
import com.pluhin.helper.reconciliation.common.dto.CheckErrorsDTO;
import com.pluhin.helper.reconciliation.common.exception.InvalidFileException;
import com.pluhin.helper.reconciliation.common.processor.XlsFileProcessor;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ActsService {

  public CheckErrorsDTO doReconciliation(MultipartFile multipartFile) {
    XlsFileProcessor fileProcessor = null;
    try {
      fileProcessor = new XlsFileProcessor(multipartFile.getInputStream());
    } catch (IOException e) {
      log.error("Some error occurred", e);
      throw new InvalidFileException();
    }

    return fileProcessor.doCheck();
  }
}
