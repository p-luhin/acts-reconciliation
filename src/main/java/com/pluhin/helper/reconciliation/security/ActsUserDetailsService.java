package com.pluhin.helper.reconciliation.security;

import com.pluhin.helper.reconciliation.repository.UserRepository;
import com.pluhin.helper.reconciliation.security.domain.UserDetailsImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActsUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public ActsUserDetailsService(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return Optional.ofNullable(userRepository.findByUsername(s))
        .map(user -> new UserDetailsImpl(user.getUsername(), user.getPassword()))
        .orElseThrow(() -> new UsernameNotFoundException("User with " + s + " username not found"));
  }
}
