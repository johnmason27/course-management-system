package com.editors;

import java.util.UUID;

public interface IEditor<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    void delete(UUID id);
}
