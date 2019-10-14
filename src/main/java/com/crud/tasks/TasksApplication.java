package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TasksApplication {
	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}
}
