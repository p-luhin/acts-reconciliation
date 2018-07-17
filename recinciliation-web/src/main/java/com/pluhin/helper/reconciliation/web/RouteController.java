package com.pluhin.helper.reconciliation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/check-status")
  public String checkStatus() {
    return "check-status";
  }
}
