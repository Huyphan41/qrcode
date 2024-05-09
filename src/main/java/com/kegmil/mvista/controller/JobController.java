package com.kegmil.mvista.controller;

import com.kegmil.mvista.config.Routes;
import com.kegmil.mvista.reponse.Response;
import com.kegmil.mvista.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JobController {

  private final JobService jobService;

  @GetMapping(Routes.JOBS)
  public ResponseEntity<Response<Map<String, Object>>> getJobs() {
    var response = jobService.jobFacts();
    return ResponseEntity.status(response.getCode()).body(response);
  }
}
