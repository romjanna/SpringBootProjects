package com.jeiup.fitnessap;

import com.jeiup.fitnessap.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class FitnessapApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessapApplication.class, args);
	}

}
