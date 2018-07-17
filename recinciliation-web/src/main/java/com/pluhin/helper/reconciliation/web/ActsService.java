package com.pluhin.helper.reconciliation.web;

import com.pluhin.helper.reconciliation.common.CheckErrorsDTO;
import com.pluhin.helper.reconciliation.common.XlsFileProcessor;
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
      throw new RuntimeException("Неверный файл или формат файла");
    }

    return fileProcessor.doCheck();
  }
}
