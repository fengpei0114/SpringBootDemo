package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAll();
    Todo save(Todo todo);
    void deleteById(Long id);
    Optional<Todo> findById(Long id);
}
