package com.cg.todolist.queryside.backend;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.cg.todolist.TodoRepository;

import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@EntityScan("net.chrisrichardson.eventstore.examples.todolist")
@EnableJpaRepositories("net.chrisrichardson.eventstore.examples.todolist")
@EnableEventHandlers
public class TodoViewBackendConfiguration {

    @Bean
    public TodoViewEventSubscriber todoViewEventSubscriber(TodoUpdateServiceImpl queryService) {
        return new TodoViewEventSubscriber(queryService);
    }

    @Bean
    public TodoUpdateServiceImpl queryService(TodoRepository repository) {
        return new TodoUpdateServiceImpl(repository);
    }

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
}


