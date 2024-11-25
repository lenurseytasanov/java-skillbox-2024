package com.app.skillbox_laba5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SkillboxLaba5Application {

	public static void main(String[] args) {
		SpringApplication.run(SkillboxLaba5Application.class, args);
	}

}