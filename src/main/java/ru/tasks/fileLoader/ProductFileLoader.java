package ru.tasks.fileLoader;

import ru.tasks.entity.Store;

import java.util.Map;
import java.util.Optional;


public interface ProductFileLoader {
    Optional<Map<String, Store>> readProductList(String path);
}
