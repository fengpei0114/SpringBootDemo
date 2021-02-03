package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="status")
    private boolean status;

    @NotNull
    @Column(name= "content")
    private String content;

    public Todo() {}

    public Todo(boolean status, String content) {
        this.status = status;
        this.content = content;
    }

    public Todo(long id, boolean status, String content) {
        this.id = id;
        this.status = status;
        this.content = content;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
