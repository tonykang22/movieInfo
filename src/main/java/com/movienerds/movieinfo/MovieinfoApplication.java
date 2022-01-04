package com.movienerds.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieinfoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/movies/**")
						.allowedOrigins("http://54.180.152.93:9000", "http://localhost:9001", "http://localhost:9002")
						.allowedMethods("GET", "POST")
						.maxAge(3000);
			}
		};
	}

}
