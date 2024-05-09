package com.kegmil.mvista.config;

import com.kegmil.mvista.filters.ApiKeyFilter;
import com.kegmil.mvista.helpers.ClientAuthenticationHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  private final ClientAuthenticationHelper authServiceHelper;
  private final ApiKeyFilterConfig config;

  public WebSecurityConfig(ClientAuthenticationHelper authServiceHelper, ApiKeyFilterConfig config) {
    this.authServiceHelper = authServiceHelper;
    this.config = config;
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    // add the ApiKeyFilter to the security chain
    http.addFilterBefore(new ApiKeyFilter(authServiceHelper, config), AnonymousAuthenticationFilter.class);

    // configure the security chain to authenticate all endpoints
    // except the /error
    http.authorizeHttpRequests(requests ->
        requests.requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Routes.WEB_INDEX)).permitAll()
            .anyRequest().authenticated()
    );

    // since this is an API app, configure it to be stateless
    http.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
