package com.editors;

import java.util.UUID;

/**
 * Definition of an editor.
 * @param <T> The type of the model being edited
 */
public interface IEditor<T> {
    /**
     * Add a given entity to the main store of that model.
     * @param entity The entity to add
     */
    void add(T entity);

    /**
     * Update a given entity from the existing model in the main store.
     * @param entity The entity to update
     */
    void update(T entity);

    /**
     * Delete a given entity using the existing model from the main store.
     * @param entity The entity to delete
     */
    void delete(T entity);

    /**
     * Delete a given entity using its id to search by from the main store.
     * @param id The id to search by
     */
    void delete(UUID id);
}
