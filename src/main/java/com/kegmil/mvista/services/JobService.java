package com.kegmil.mvista.services;

import com.github.javafaker.Faker;
import com.kegmil.mvista.reponse.Response;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobService {
  public Response<Map<String, Object>> jobFacts() {
    var job = Faker.instance().job().title();
    return Response.successfulResponse("Operation Successful", Map.of("job", job));
  }
}
