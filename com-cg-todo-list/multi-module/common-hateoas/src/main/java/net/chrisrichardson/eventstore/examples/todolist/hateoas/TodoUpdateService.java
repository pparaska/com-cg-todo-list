package net.chrisrichardson.eventstore.examples.todolist.hateoas;

import com.cg.todolist.model.Todo;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface TodoUpdateService {

    List<Todo> getAll();

    CompletableFuture<Todo> findById(String todoId);
}
