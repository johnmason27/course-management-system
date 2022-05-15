package com.savers;

import java.util.ArrayList;

public interface ISaver<T> {
    void saveAll(ArrayList<T> entities);
}
