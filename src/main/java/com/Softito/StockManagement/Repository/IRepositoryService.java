package com.Softito.StockManagement.Repository;

import java.util.List;
import java.util.Optional;

public interface IRepositoryService<T> {
    T add(T entity);
    String delete(Long id);
    String update(Long id,T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
}
