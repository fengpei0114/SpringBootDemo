package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Todo;
import com.example.demo.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @GetMapping("/getAll")
    public List<Todo> findAllTodoList() {
         List<Todo> todoList = todoListService.findAll();
         return todoList;
    }

    @PostMapping("/addTodo")
    public Todo addTodoListItem(@RequestParam("content") String content, @RequestParam("status") boolean status) {
        Todo todoItem = new Todo(status, content);
        return todoListService.save(todoItem);
    }

    @PostMapping("/update")
    public Todo updateTodoListItem(@RequestParam("id") Long id, @RequestParam("content") String content, @RequestParam("status") boolean status) throws BadRequestException {
        if(content.equals("")) {
            throw new BadRequestException("The content should not be empty");
        }
        Todo todoItem = new Todo(id, status, content);
        return todoListService.save(todoItem);
    }

    @GetMapping("/delete")
    public void deleteTodoListItem(@RequestParam("id") Long id) {
        todoListService.delete(id);
    }
}


