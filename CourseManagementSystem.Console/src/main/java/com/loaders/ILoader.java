package com.loaders;

import java.util.ArrayList;
import java.util.UUID;

public interface ILoader<T> {
    ArrayList<T> loadAll();
    T find(String name);
    T find(UUID id);
}
