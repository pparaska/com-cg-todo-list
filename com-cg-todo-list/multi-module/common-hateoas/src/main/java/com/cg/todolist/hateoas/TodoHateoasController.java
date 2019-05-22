package com.cg.todolist.hateoas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.todolist.common.controller.BaseController;
import com.cg.todolist.common.model.ResourceWithUrl;
import com.cg.todolist.model.Todo;

import net.chrisrichardson.eventstore.examples.todolist.hateoas.TodoUpdateService;

import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/todos")
public class TodoHateoasController extends BaseController {

    @Autowired
    private TodoUpdateService queryService;

    @RequestMapping(value = "/{todo-id}", method = GET)
    public CompletableFuture<ResourceWithUrl> getTodo(@PathVariable("todo-id") String id) {
        return queryService.findById(id).thenApply(this::toResource);
    }

    public ResourceWithUrl toResource(Todo todo) {
        ResourceWithUrl<Todo> result = new ResourceWithUrl<>(todo);
        if (todo != null) {
            result.setUrl(linkTo(methodOn(TodoHateoasController.class).getTodo(todo.getId())).withSelfRel().getHref());
        }
        return result;
    }
}
