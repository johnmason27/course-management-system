package com.loaders;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Definition of a Loader.
 * @param <T> The type of the model being loaded.
 */
public interface ILoader<T> {
    /**
     * Load all records of the given type.
     * @return All records
     */
    ArrayList<T> loadAll();

    /**
     * Find a record of the given type by its string name.
     * @param name The string name to search by
     * @return The record found or null
     */
    T find(String name);

    /**
     * Find a record of the given type by its id.
     * @param id The id to search by
     * @return The record found or null
     */
    T find(UUID id);
}
