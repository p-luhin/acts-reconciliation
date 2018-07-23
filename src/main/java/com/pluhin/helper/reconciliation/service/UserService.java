package com.pluhin.helper.reconciliation.service;

import static java.util.stream.Collectors.toList;

import com.pluhin.helper.reconciliation.common.dto.UserDTO;
import com.pluhin.helper.reconciliation.entity.User;
import com.pluhin.helper.reconciliation.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public void createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public List<UserDTO> getAll() {
    return userRepository.findAll()
        .stream()
        .map(user -> new UserDTO(user.getId(), user.getUsername()))
        .collect(toList());
  }

  @Transactional
  public void deleteAll(List<Integer> ids) {
    ids.forEach(userRepository::delete);
  }

  @Transactional(readOnly = true)
  public UserDTO getCurrentUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    return Optional.ofNullable(userRepository.findByUsername(username))
        .map(user -> new UserDTO(user.getId(), username))
        .orElse(null);
  }
}
