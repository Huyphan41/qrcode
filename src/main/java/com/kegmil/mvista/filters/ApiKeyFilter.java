package com.kegmil.mvista.filters;

import com.kegmil.mvista.config.ApiKeyFilterConfig;
import com.kegmil.mvista.helpers.ClientAuthenticationHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

  private final ClientAuthenticationHelper authServiceHelper;
  private final ApiKeyFilterConfig config;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    var path = request.getRequestURI();
    return !path.startsWith(config.getPathPrefix());
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String reqApiKey = request.getHeader("Api-Key");
    boolean isApiKeyValid = authServiceHelper.validateApiKey(reqApiKey);

    if (!isApiKeyValid) {
      // return 401 Unauthorized
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
      return;
    }

    // apiKey is valid. Signal to Spring Security, this is an authenticated request
    var authenticationToken = new UsernamePasswordAuthenticationToken(reqApiKey, reqApiKey, Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    // continue process the request
    filterChain.doFilter(request, response);
  }
}
