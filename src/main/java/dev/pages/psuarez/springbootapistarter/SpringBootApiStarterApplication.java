package dev.pages.psuarez.springbootapistarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootApiStarterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiStarterApplication.class, args);
	}
}
