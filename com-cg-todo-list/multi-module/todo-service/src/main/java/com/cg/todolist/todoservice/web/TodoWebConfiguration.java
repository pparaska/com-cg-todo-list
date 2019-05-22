package com.cg.todolist.todoservice.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cg.todolist.todoservice.backend.TodoBackendConfiguration;

@Configuration
@Import({TodoBackendConfiguration.class})
@ComponentScan({ "net.chrisrichardson.eventstore.examples.todolist.common",
        "net.chrisrichardson.eventstore.examples.todolist.todoservice.web",
        "net.chrisrichardson.eventstore.examples.todolist.hateoas"})
public class TodoWebConfiguration extends WebMvcConfigurerAdapter//Helps with configuring HandlerMappings path matching
										//options such as trailing slash match, suffix registration, path matcher and path helper 
{
	
}
