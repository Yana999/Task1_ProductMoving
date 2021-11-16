import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductMoving {
    public static void main(String[] args) {
        FileAction fileAction = new FileAction();
        Map<String, Store> productList = fileAction.readProductList("C:\\Users\\yabdramanova\\IdeaProjects\\Task1_ProductMoving\\src\\main\\resources\\products.txt");
        System.out.println(productList.toString());
    }
}
