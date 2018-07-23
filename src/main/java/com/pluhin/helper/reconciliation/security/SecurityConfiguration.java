package com.pluhin.helper.reconciliation.security;

import com.pluhin.helper.reconciliation.security.handler.ErrorAuthenticationHandler;
import com.pluhin.helper.reconciliation.security.handler.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final ErrorAuthenticationHandler errorAuthenticationHandler;
  private final UnauthorizedEntryPoint unauthorizedEntryPoint;

  @Autowired
  public SecurityConfiguration(
      UserDetailsService userDetailsService,
      ErrorAuthenticationHandler errorAuthenticationHandler,
      UnauthorizedEntryPoint unauthorizedEntryPoint) {
    this.userDetailsService = userDetailsService;
    this.errorAuthenticationHandler = errorAuthenticationHandler;
    this.unauthorizedEntryPoint = unauthorizedEntryPoint;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(4);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
        .and()
        .userDetailsService(userDetailsService)
        .formLogin()
        .loginProcessingUrl("/api/users/login")
        .usernameParameter("j_username")
        .passwordParameter("j_password")
        .failureHandler(errorAuthenticationHandler)
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/api/acts/config/**").authenticated()
        .antMatchers("/api/users/current").permitAll()
        .antMatchers("/api/users/**").authenticated()
        .antMatchers("/**").permitAll();
  }
}
