package com.cg.todolist.todoservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.todolist.commonswagger.CommonSwaggerConfiguration;
import com.cg.todolist.todoservice.web.TodoWebConfiguration;

@Configuration
@Import({TodoWebConfiguration.class,
        EventuateDriverConfiguration.class,
        CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
public class TodoServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(TodoServiceMain.class, args);
  }

}