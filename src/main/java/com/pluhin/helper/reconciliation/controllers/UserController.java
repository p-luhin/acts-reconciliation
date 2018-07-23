package com.pluhin.helper.reconciliation.controllers;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import com.pluhin.helper.reconciliation.common.dto.UserDTO;
import com.pluhin.helper.reconciliation.entity.User;
import com.pluhin.helper.reconciliation.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping
  public ResponseEntity createNewUser(@RequestBody User user) {
    userService.createUser(user);
    return noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ok(userService.getAll());
  }

  @PostMapping("/delete")
  public ResponseEntity deleteAll(@RequestBody List<Integer> ids) {
    userService.deleteAll(ids);
    return noContent().build();
  }

  @GetMapping("/current")
  public ResponseEntity<UserDTO> getCurrentUser() {
    return ok(userService.getCurrentUser());
  }
}
