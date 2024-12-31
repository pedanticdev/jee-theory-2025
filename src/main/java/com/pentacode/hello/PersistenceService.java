package com.pentacode.hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class PersistenceService {

    @PersistenceContext
    EntityManager em;

    public Todo save(Todo todo) {
        em.persist(todo);
        return todo;
    }

    public List<Todo> findAll() {
        return em
                .createQuery("select t from Todo t order by t.dueDate", Todo.class)
                .getResultList();
    }
}
