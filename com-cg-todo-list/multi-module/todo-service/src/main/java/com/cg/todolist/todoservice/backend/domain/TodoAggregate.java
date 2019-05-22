package com.cg.todolist.todoservice.backend.domain;


import java.util.Collections;
import java.util.List;

import com.cg.todolist.common.event.TodoCreatedEvent;
import com.cg.todolist.common.event.TodoDeletedEvent;
import com.cg.todolist.common.event.TodoUpdatedEvent;
import com.cg.todolist.model.TodoInfo;
import com.cg.todolist.todoservice.backend.command.CreateTodoCommand;
import com.cg.todolist.todoservice.backend.command.DeleteTodoCommand;
import com.cg.todolist.todoservice.backend.command.TodoCommand;
import com.cg.todolist.todoservice.backend.command.UpdateTodoCommand;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

public class TodoAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoAggregate, TodoCommand>
//A mutable aggregate that uses reflection to process commands and apply events
//Type Parameters:
//<T> the aggregate class
//<CT> the aggregate's command class

{

    private TodoInfo todo;
    private boolean deleted;

    public List<Event> process(CreateTodoCommand cmd) //process() handles a command and returns a list of Events
    {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoCreatedEvent(cmd.getTodo()));//returns array list of events
    }

    public List<Event> process(UpdateTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoUpdatedEvent(cmd.getTodo()));
    }

    public List<Event> process(DeleteTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoDeletedEvent());
    }


    public void apply(TodoCreatedEvent event) //apply() method consumes an event and changes the state of the aggregate
    {
    this.todo = event.getTodo();
    }

    public void apply(TodoUpdatedEvent event) {
        this.todo = event.getTodo();
    }

    public void apply(TodoDeletedEvent event) {
        this.deleted = true;
    }

    public TodoInfo getTodo() {
        return todo;
    }

}


