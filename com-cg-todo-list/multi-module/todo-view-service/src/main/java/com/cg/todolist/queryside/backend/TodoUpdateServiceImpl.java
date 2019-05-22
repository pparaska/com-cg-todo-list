package com.cg.todolist.queryside.backend;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import com.cg.todolist.TodoRepository;
import com.cg.todolist.model.Todo;

import io.eventuate.CompletableFutureUtil;
import net.chrisrichardson.eventstore.examples.todolist.hateoas.TodoUpdateService;


public class TodoUpdateServiceImpl implements TodoUpdateService {

    private TodoRepository repository;

    public TodoUpdateServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public void remove(String id) {
        repository.delete(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public CompletableFuture<Todo> findById(String todoId) {
        Todo res = repository.findOne(todoId);
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found"));
    }

}
