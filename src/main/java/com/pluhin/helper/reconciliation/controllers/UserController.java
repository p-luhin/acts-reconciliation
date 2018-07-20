package com.pluhin.helper.reconciliation.controllers;

import static org.springframework.http.ResponseEntity.noContent;

import com.pluhin.helper.reconciliation.entity.User;
import com.pluhin.helper.reconciliation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/")
  public ResponseEntity createNewUser(@RequestBody User user) {
    userService.createUser(user);
    return noContent().build();
  }
}
