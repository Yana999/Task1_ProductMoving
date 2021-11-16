import java.util.Objects;

public class Product {
    private String name;
    private double weight;
    private double cost;

    public Product(String name, double weight, double cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + weight + " " + cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.weight, weight) == 0 && Double.compare(product.cost, cost) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + (int)(weight % cost);
    }
}
