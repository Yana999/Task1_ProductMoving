package ru.tasks.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Store {
    private String name;
    private HashMap <String, Product> products;

    public Store(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product){
        products.putIfAbsent(product.getName(), product);
    }

    public BigDecimal avgCost(){
        BigDecimal allCost= new BigDecimal(0);
        for(Map.Entry<String, Product> item: products.entrySet()){
            allCost = allCost.add(item.getValue().getCost());
        }
        return allCost.divide(new BigDecimal(products.size()), 2, RoundingMode.HALF_UP);
    }

    public String info(){
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s average cost: %,7.2f:%n", name, avgCost().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        products.forEach((k,v) -> info.append(v.toString()));
        return info.toString();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Product> getProducts() {
        return new HashMap<>(products);
    }

    @Override
    public String toString() {
        return '{' + "name='" + name + '\'' + ", products=" + products + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(name, store.name) && Objects.equals(products, store.products);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}