package com.todo.demo.service;

import com.todo.demo.entity.Todo;

import java.util.List;

public interface ITodoService {

    boolean create(Todo t);
    boolean update(Todo t);
    boolean changeState(Todo t);

    boolean delete(Todo t);

    Todo findById(int id);

    List<Todo> findAll();


}
