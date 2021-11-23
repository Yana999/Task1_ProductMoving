package ru.tasks.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Store {
    private final String name;
    private final HashMap <String, Product> products;

    public Store(String name) throws IllegalArgumentException{
        this.name = name;
        this.products = new HashMap<>();
    }

    public static Store parseStore(String store){
        store = store.trim();
        if(!store.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException(String.format("The illegal value of store's name %s, it is contains a specific symbols", store));
        }else{
            return new Store(store);
        }
    }

    public BigDecimal sumCost(){
        BigDecimal allCost= new BigDecimal(0);
        for(Product product : products.values()){
            allCost = allCost.add(product.getCost());
        }
        return allCost;
    }

    public Product addProduct(Product product){
        return  products.putIfAbsent(product.getName(), product);
    }

    public BigDecimal avgCost(){
        return products.size() == 0 ? BigDecimal.ZERO : sumCost().divide(new BigDecimal(products.size()), 2, RoundingMode.HALF_UP);
    }

    public String info(){
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s average cost: %,7.2f:%n", name, avgCost().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        products.forEach((k,v) -> info.append(v.formatString()));
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
