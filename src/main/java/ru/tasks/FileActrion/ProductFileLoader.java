package ru.tasks.FileActrion;

import java.util.Map;
import ru.tasks.Entity.Store;

public interface ProductFileLoader {
    Map<String, Store> readProductList(String path);
}
