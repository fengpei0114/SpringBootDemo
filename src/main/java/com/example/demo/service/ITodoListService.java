package com.example.demo.service;

import com.example.demo.model.Todo;

import java.util.List;

public interface ITodoListService {
    List<Todo> findAll();
    Todo save(Todo todo);
    void delete(Long id);
}
