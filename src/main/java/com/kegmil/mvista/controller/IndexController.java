package com.kegmil.mvista.controller;

import com.kegmil.mvista.config.Routes;
import com.kegmil.mvista.reponse.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

  @GetMapping(Routes.WEB_INDEX)
  public ResponseEntity<Response<Map<String, Long>>> index() {
    var response = Response.successfulResponse("Welcome to KEGMIL",
        Map.of("timestamp", System.currentTimeMillis()));
    return ResponseEntity.status(response.getCode())
        .body(response);
  }
}
