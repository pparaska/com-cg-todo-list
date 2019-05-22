package com.cg.todolist.todoservice.backend.domain;


import java.util.List;
import java.util.stream.Collectors;

import com.cg.todolist.common.event.TodoDeletionRequestedEvent;
import com.cg.todolist.todoservice.backend.command.DeleteTodosCommand;
import com.cg.todolist.todoservice.backend.command.TodoCommand;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;


public class TodoBulkDeleteAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoBulkDeleteAggregate, TodoCommand> {

    public List<Event> process(DeleteTodosCommand cmd) {
        return cmd.getIds()
                .stream()
                .map(TodoDeletionRequestedEvent::new)
                .collect(Collectors.toList());
    }

    public void apply(TodoDeletionRequestedEvent event) {

    }
}
//bulk-delete(map is getting a stream of getIds and put them into the TodoDeletionRequestedEvent which is then collected using
//Collectors.toList())