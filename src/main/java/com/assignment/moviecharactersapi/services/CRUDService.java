package com.assignment.moviecharactersapi.services;

import java.util.Collection;

/**
 * Generic CRUD service for basic functionality for
 * all domain specific services
 *
 * @param <T> Type of domain class of the service
 * @param <ID> Primary key type for entity
 */
public interface CRUDService <T, ID>{
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
    T update(T entity);
    void deleteById(ID id);
    void delete(T entity);
}
