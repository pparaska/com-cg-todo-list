package com.cg.todolist;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.todolist.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, String> {

}
