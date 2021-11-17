import java.math.BigDecimal;
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
        products.entrySet().forEach(x->allCost.add(x.getValue().getCost()));
        return allCost.divide(new BigDecimal(products.size()));
    }

    public String info(){
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s средняя цена: %.2f", name, avgCost()));
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
