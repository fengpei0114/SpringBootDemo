package com.example.demo.fixture;

import com.example.demo.model.Todo;

import java.util.List;

public class TodoListFixture {
    public static Todo mockTodoItem(long id){
        return Todo.builder()
                .id(id)
                .status(false)
                .content("this is test content")
                .build();
    }
    public static List<Todo> mockTodoItemList(){
        var aaa = List.of(mockTodoItem(1), mockTodoItem(2), mockTodoItem(3));
        return List.of(mockTodoItem(1), mockTodoItem(2), mockTodoItem(3));
    }
}
