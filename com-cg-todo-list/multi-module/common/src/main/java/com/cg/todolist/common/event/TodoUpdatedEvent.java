package com.cg.todolist.common.event;

import com.cg.todolist.model.TodoInfo;

public class TodoUpdatedEvent implements TodoEvent {

    private TodoInfo todo;

	
	  private TodoUpdatedEvent() { }
	 

    public TodoUpdatedEvent(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }

    public void setTodo(TodoInfo todo) {
        this.todo = todo;
    }
}