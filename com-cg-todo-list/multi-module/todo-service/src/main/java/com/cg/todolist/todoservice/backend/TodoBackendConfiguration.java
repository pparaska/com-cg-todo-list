package com.cg.todolist.todoservice.backend;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.cg.todolist.TodoRepository;
import com.cg.todolist.todoservice.backend.command.TodoCommand;
import com.cg.todolist.todoservice.backend.domain.TodoAggregate;
import com.cg.todolist.todoservice.backend.domain.TodoBulkDeleteAggregate;
import com.cg.todolist.todoservice.backend.domain.TodoEventSubscriber;
import com.cg.todolist.todoservice.backend.domain.TodoService;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;



@Configuration
@EntityScan("com.cg.todolist")
@EnableJpaRepositories("com.cg.todolist")
@EnableEventHandlers
public class TodoBackendConfiguration {

  @Bean
  public TodoEventSubscriber todoEventSubscriber() {
    return new TodoEventSubscriber();
  }

  @Bean
  public AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(TodoAggregate.class, eventStore);
  }

  @Bean
  public AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<>(TodoBulkDeleteAggregate.class, eventStore);
  }

  @Bean
  public TodoService updateService(AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository, AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository) {
    return new TodoService(aggregateRepository, bulkDeleteAggregateRepository);
  }

  @Bean
  public TodoViewServiceImpl commandService(TodoRepository repository) {
    return new TodoViewServiceImpl(repository);
  }

  @Bean
  public HttpMessageConverters customConverters() {
    HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
    return new HttpMessageConverters(additional);
  }
}


