package com.pluhin.helper.reconciliation.repository;

import com.pluhin.helper.reconciliation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);
}
