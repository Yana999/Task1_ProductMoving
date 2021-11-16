import java.util.*;

public class Store {
    private String name;
    private HashMap <String, Product> products;

    public Store(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product){
        products.put(product.getName(), product);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Store{" + "name='" + name + '\'' + ", products=" + products + '}';
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
