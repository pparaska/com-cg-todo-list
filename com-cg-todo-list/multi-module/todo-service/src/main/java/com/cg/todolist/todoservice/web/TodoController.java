package com.cg.todolist.todoservice.web;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.todolist.common.controller.BaseController;
import com.cg.todolist.common.model.ResourceWithUrl;
import com.cg.todolist.hateoas.TodoHateoasController;
import com.cg.todolist.model.Todo;
import com.cg.todolist.model.TodoInfo;
import com.cg.todolist.todoservice.backend.TodoViewServiceImpl;
import com.cg.todolist.todoservice.backend.domain.TodoService;


@RestController
@RequestMapping(value = "/todos")
public class TodoController extends BaseController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoViewServiceImpl todoViewService;

    @RequestMapping(method = POST)
    public CompletableFuture<ResourceWithUrl> saveTodo(@RequestBody TodoInfo todo, HttpServletRequest request) {
      // Assert.notNull(todo.getTitle());
        return todoService.save(todo).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getTodo(), e.getEntityId())));
    }
//e : EntityWithIdAndVersion
    @RequestMapping(value = "/{todo-id}", method = DELETE)
    public CompletableFuture<ResourceWithUrl> deleteOneTodo(@PathVariable("todo-id") String id, HttpServletRequest request) {
        return todoService.remove(id)
                .thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getTodo(), e.getEntityId())));
    }

    @RequestMapping(method = DELETE)
    public void deleteAllTodos() throws Exception {
        List<Todo> todosToDelete = todoViewService.getAll();
        if (todosToDelete.size() > 0) {
            todoService.deleteAll(todoViewService.getAll()
                    .stream()
                    .map(Todo::getId)
                    .collect(Collectors.toList()));
        }
    }

    @RequestMapping(value = "/{todo-id}", method = PATCH, headers = {"Content-type=application/json"})
    public CompletableFuture<ResourceWithUrl> updateTodo(@PathVariable("todo-id") String id, @RequestBody TodoInfo newTodo, HttpServletRequest request) {
        return todoService.update(id, newTodo).thenApply(e -> withRequestAttributeContext(request, () -> toResource(e.getAggregate().getTodo(), e.getEntityId()))
        );
    }

    protected ResourceWithUrl toResource(TodoInfo todo, String id) {
        ResourceWithUrl<TodoInfo> result = new ResourceWithUrl<>(todo);
        result.setId(id);
        result.setUrl(ControllerLinkBuilder.linkTo(methodOn(TodoHateoasController.class).getTodo(id)).withSelfRel().getHref());
        return result;
    }
}
