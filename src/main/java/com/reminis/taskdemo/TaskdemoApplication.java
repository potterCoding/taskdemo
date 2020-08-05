package com.reminis.taskdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskdemoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(TaskdemoApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
