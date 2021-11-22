package ru.tasks.productLoader;

import java.util.Map;
import ru.tasks.entity.Store;

public interface ProductFileLoader {
    Map<String, Store> readProductList(String path);
}
