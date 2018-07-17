package com.pluhin.helper.reconciliation.web;

import com.pluhin.helper.reconciliation.common.CheckErrorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/acts")
public class ActsController {

  private ActsService actsService;

  @Autowired
  public ActsController(ActsService actsService) {
    this.actsService = actsService;
  }

  @PostMapping("/show")
  public String sendErrorsResponse(
      @RequestParam MultipartFile file,
      RedirectAttributes redirectAttributes) {
    CheckErrorsDTO checkErrors = actsService.doReconciliation(file);
    redirectAttributes.addFlashAttribute("errors", checkErrors);
    return "redirect:/check-status";
  }
}
