package com.cg.todolist.todoservice.backend.domain;

import java.util.concurrent.CompletableFuture;

import com.cg.todolist.common.event.TodoDeletionRequestedEvent;
import com.cg.todolist.todoservice.backend.command.DeleteTodoCommand;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;


@EventSubscriber(id = "todoCommandSideEventHandlers")// an annotation that identifies a Spring bean as an event handler
public class TodoEventSubscriber {

    @EventHandlerMethod// an annotation that identifies a method as an event handler method
    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> deleteTodo(EventHandlerContext<TodoDeletionRequestedEvent> ctx)
   
    //EventHandlerContext: Wraps the event that is passed to a command-side event handler
    {
        String todoId = ctx.getEvent().getTodoId();

        return ctx.update(TodoAggregate.class, todoId, new DeleteTodoCommand());
        //returns the updated and persisted aggregate
    }
}

//framework subscribes to the events and dispatches each event to the appropriate event handler method