package com.todo.demo.service.impl;

import com.todo.demo.entity.Todo;
import com.todo.demo.service.ITodoService;
import com.todo.demo.utils.ServiceHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TodoServiceImpl implements ITodoService {

    @Autowired
    private ServiceHibernate serviceHibernate;
    private Session session;

    public TodoServiceImpl(ServiceHibernate serviceHibernate){
        this.serviceHibernate = serviceHibernate;
        session = this.serviceHibernate.getSession();
    }

    @Override
    public boolean create(Todo t) {
        try{
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
//            session.close();


        } catch (Exception e){
            throw e;
        }
      return true;
    }

    @Override
    public boolean update(Todo t) {
        try{
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
//            session.close();


        } catch (Exception e){
            throw e;
        }
        return true;
    }

    @Override
    public boolean changeState(Todo t) {
        try{
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
//            session.close();


        } catch (Exception e){
            throw e;
        }
        return true;

    }

    @Override
    public boolean delete(Todo t) {
        try{
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
//            session.close();


        } catch (Exception e){
            throw e;
        }
        return true;
    }

    @Override
    public Todo findById(int id) {

        try {
        Todo todo = null;
        todo = (Todo) session.get(Todo.class, id);
        return todo;

        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Todo> findAll() {

    try{
        SelectionQuery<Todo> todoQuery = session.createSelectionQuery("from Todo",Todo.class);
        return todoQuery.list();

    } catch(Exception e){
        throw e;
    }
    }


}
