package com.cg.todolist.queryside.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cg.todolist.queryside.backend.TodoViewBackendConfiguration;

@Configuration
@Import({TodoViewBackendConfiguration.class})
@ComponentScan({"com.cg.todolist.common",
        "com.cg.todolist.hateoas",
        "com.cg.todolist.queryside.web"})
public class TodoViewWebConfiguration extends WebMvcConfigurerAdapter {
}
