package com.pluhin.helper.reconciliation.controllers;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import com.pluhin.helper.reconciliation.entity.ActsConfig;
import com.pluhin.helper.reconciliation.service.ActsConfigService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/acts/config")
public class ActsConfigController {

  private ActsConfigService actsConfigService;

  @Autowired
  public ActsConfigController(
      ActsConfigService actsConfigService) {
    this.actsConfigService = actsConfigService;
  }

  @PostMapping("/")
  public ResponseEntity createConfig(@RequestBody ActsConfig config) {
    actsConfigService.createConfig(config);
    return noContent().build();
  }

  @GetMapping("/")
  public ResponseEntity<List<ActsConfig>> getConfigs() {
    return ok(actsConfigService.getAll());
  }

  @DeleteMapping("/")
  public ResponseEntity removeConfig(@RequestBody List<Integer> ids) {
    actsConfigService.removeConfigs(ids);
    return noContent().build();
  }
}
