package com.example.demo.service;

import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService implements ITodoListService{
    private final TodoRepository repository;

    public TodoListService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findAll() {
        List<Todo> todoList = repository.findAll();
        return todoList;
    }

    public Todo save(Todo todo) {
        Todo resultTodo = repository.save(todo);
        return resultTodo;
    }

    public Todo update(Todo todo){
        verifyTodoItemId(todo.getId());
        Todo resultTodo = repository.save(todo);
        return resultTodo;
    }

    @SneakyThrows
    public void delete(Long id) {
        verifyTodoItemId(id);
        repository.deleteById(id);
    }

    @SneakyThrows
    private void verifyTodoItemId(Long id) {
        repository.findById(id).orElseThrow(() -> new NoDataFoundException("Can not found this id"));
    }
}
