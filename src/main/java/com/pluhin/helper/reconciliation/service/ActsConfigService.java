package com.pluhin.helper.reconciliation.service;

import com.pluhin.helper.reconciliation.entity.ActsConfig;
import com.pluhin.helper.reconciliation.repository.ActsConfigRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActsConfigService {

  private ActsConfigRepository repository;

  @Autowired
  public ActsConfigService(
      ActsConfigRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public ActsConfig getConfig(String actName) {
    return repository.findByActName(actName);
  }

  @Transactional
  public void createConfig(ActsConfig config) {
    repository.save(config);
  }

  @Transactional(readOnly = true)
  public List<ActsConfig> getAll() {
    return repository.findAll();
  }

  @Transactional
  public void removeConfigs(List<Integer> ids) {
    ids.forEach(repository::delete);
  }
}
