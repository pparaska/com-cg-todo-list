package com.cg.todolist.todoservice.backend.domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.cg.todolist.model.TodoInfo;
import com.cg.todolist.todoservice.backend.command.CreateTodoCommand;
import com.cg.todolist.todoservice.backend.command.DeleteTodoCommand;
import com.cg.todolist.todoservice.backend.command.DeleteTodosCommand;
import com.cg.todolist.todoservice.backend.command.TodoCommand;
import com.cg.todolist.todoservice.backend.command.UpdateTodoCommand;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;


public class TodoService {

    private final AggregateRepository<TodoAggregate, TodoCommand> aggregateRepository;
    private final AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository;


    public TodoService(AggregateRepository<TodoAggregate, TodoCommand> todoRepository, AggregateRepository<TodoBulkDeleteAggregate, TodoCommand> bulkDeleteAggregateRepository) {
        this.aggregateRepository = todoRepository;
        this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
    } //constructor

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> save(TodoInfo todo) {
        return aggregateRepository.save(new CreateTodoCommand(todo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> remove(String id) {
        return aggregateRepository.update(id, new DeleteTodoCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> update(String id, TodoInfo newTodo) {
        return aggregateRepository.update(id, new UpdateTodoCommand(id, newTodo));
    }

    public CompletableFuture<EntityWithIdAndVersion<TodoBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return bulkDeleteAggregateRepository.save(new DeleteTodosCommand(ids));
    }
}
