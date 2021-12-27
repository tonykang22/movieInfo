package com.movienerds.movieinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MovieinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieinfoApplication.class, args);
	}

}
