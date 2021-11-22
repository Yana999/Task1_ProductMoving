package ru.tasks.entity;

import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final double weight;
    private final BigDecimal cost;

    public Product(String name, double weight, BigDecimal cost){
       this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public static Product parseProduct(String name, String weight, String cost) throws IllegalArgumentException{
        name = name.trim();
        weight = weight.trim();
        cost = cost.trim();

        if(!name.matches("[a-zA-Z0-9-]+")){
            throw new IllegalArgumentException(String.format("Inappropriate value of product's name %s, it is contains a specific symbols", name));
        }

        if (weight.contains(",")) {
            weight = weight.replace(",", ".");
            if(weight.matches("\\D") || !weight.matches("\\d+.\\d{1,3}")){
                throw new IllegalArgumentException(String.format("Inappropriate value of the weight %s", weight));
            }
        }

        if(cost.contains(",")){
            cost = cost.replace(",", ".");
            if (cost.matches("\\D")|| (cost.contains(".") && !cost.matches("\\d+.\\d{1,2}"))) {
                throw new IllegalArgumentException(String.format("Inappropriate value of the cost %s", cost));
            }
        }

        return new Product(name, Double.parseDouble(weight), new BigDecimal(cost));
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
    public String toString(){
        return String.format("%1$s %2$,.3f %3$,.2f%n", name, weight, cost);
    }

    public String formatString() {
        return String.format("%1$-15s %2$,10.3f %3$,7.2f%n", name, weight, cost);
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
