package com.example.demo.service;

import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Todo;
import com.example.demo.repository.TodoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService{
    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        List<Todo> todoList = repository.findAll();
        return todoList;
    }

    public Todo save(Todo todo) {
        checkDbStatus(todo.getId(), todo.getStatus());
        Todo resultTodo = repository.save(todo);
        return resultTodo;
    }

    @SneakyThrows
    public void delete(Long id) {
        findById(id).orElseThrow(() -> new NoDataFoundException());
        repository.deleteById(id);
    }
    public Optional<Todo> findById(Long id) { return repository.findById(id);}

    @SneakyThrows
    private void checkDbStatus(Long id, Boolean status) {
        Todo todoItem = findById(id).orElseThrow(() -> new NoDataFoundException());
        if(todoItem.getStatus() == status){
            throw new ConflictException();
        }
    }
}
