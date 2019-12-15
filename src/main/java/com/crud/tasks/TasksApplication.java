package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(TasksApplication.class);
//	}

