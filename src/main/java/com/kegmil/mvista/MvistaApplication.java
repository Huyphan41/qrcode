package com.kegmil.mvista;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
		value = "com.kegmil.mvista.repositories", repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class MvistaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MvistaApplication.class, args);
	}
}
