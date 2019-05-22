package com.cg.todolist.todoservice.backend.command;

import com.cg.todolist.model.TodoInfo;

public class CreateTodoCommand implements TodoCommand {

    private TodoInfo todo;

    public CreateTodoCommand(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
