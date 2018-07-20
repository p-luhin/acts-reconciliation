package com.pluhin.helper.reconciliation.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.pluhin.helper.reconciliation.common.dto.CheckErrorsDTO;
import com.pluhin.helper.reconciliation.service.ActsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/acts")
public class ActsController {

  private ActsService actsService;

  @Autowired
  public ActsController(ActsService actsService) {
    this.actsService = actsService;
  }

  @PostMapping("/show")
  public ResponseEntity<CheckErrorsDTO> sendErrorsResponse(
      @RequestParam MultipartFile firstFile,
      @RequestParam MultipartFile secondFile) {

    return ok(actsService.doReconciliation(firstFile, secondFile));
  }
}
