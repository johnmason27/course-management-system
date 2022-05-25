package com.savers;

import java.util.ArrayList;

/**
 * Definition of a Saver.
 * @param <T> The type of model being saved.
 */
public interface ISaver<T> {
    /**
     * Save all of a given type.
     * @param entities The entities to save of a given type
     */
    void saveAll(ArrayList<T> entities);
}
