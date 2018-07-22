package com.pluhin.helper.reconciliation.repository;

import com.pluhin.helper.reconciliation.entity.ActsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActsConfigRepository extends JpaRepository<ActsConfig, Integer> {

  ActsConfig findByActName(String actName);
}
