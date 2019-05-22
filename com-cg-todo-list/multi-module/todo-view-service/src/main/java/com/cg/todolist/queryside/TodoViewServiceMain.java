package com.cg.todolist.queryside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cg.todolist.commonswagger.CommonSwaggerConfiguration;
import com.cg.todolist.queryside.web.TodoViewWebConfiguration;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;


@Configuration
@Import({TodoViewWebConfiguration.class,
        EventuateDriverConfiguration.class,
        CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
public class TodoViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(TodoViewServiceMain.class, args);
  }

}