import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final double weight;
    private final BigDecimal cost;

    public Product(String name, double weight, BigDecimal cost) {
        this.name = name;
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
        return name + " " + weight + " " + cost;
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
