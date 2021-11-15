public class Product {
    private String name;
    private double weight;
    private double cost;
    private String store;

    public Product(String name, double weight, double cost, String store) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.store = store;
    }

    @Override
    public String toString() {
        return name + " " + weight + " " + cost + " " + store;
    }
}
