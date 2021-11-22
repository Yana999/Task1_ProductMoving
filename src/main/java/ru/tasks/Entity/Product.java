package ru.tasks.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final double weight;
    private final BigDecimal cost;

    public Product(String name, double weight, BigDecimal cost) throws IllegalArgumentException{
        if(cost.compareTo(new BigDecimal(0)) < 0 || cost.ulp().equals(new BigDecimal(0.01))) {
            throw new IllegalArgumentException(String.format("The illegal value of cost %f, the price cannot be negative", cost));
        }
        if(name.matches("[a-zA-Z0-9]+")){
            this.name = name;
        }else{
            throw new IllegalArgumentException(String.format("The illegal value of product's name %s, it is contains a specific symbols", name));
        }
        if (weight < 0){
            throw new IllegalArgumentException(String.format("The illegal value of weight %f, the weight cannot be negative", weight));
        }
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%1$15s %2$,10.3f %3$,7.2f%n", name, weight, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.weight, weight) == 0 && Objects.equals(name, product.name) && Objects.equals(cost, product.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, cost);
    }
}
