package com.cg.todolist.todoservice.backend;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityNotFoundException;

import com.cg.todolist.TodoRepository;
import com.cg.todolist.model.Todo;

import io.eventuate.CompletableFutureUtil;
import net.chrisrichardson.eventstore.examples.todolist.hateoas.TodoUpdateService;


public class TodoViewServiceImpl implements TodoUpdateService {

    private TodoRepository repository;

    public TodoViewServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Todo> findById(String todoId) {
        Todo res = repository.findOne(todoId);
        if (res != null) {
            return CompletableFuture.completedFuture(res);//completed future gives new CompletableFuture that is already completed with the given value
        }
        return CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id"));
    }

}
