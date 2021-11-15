import java.util.Collections;
import java.util.List;

public class ProductMoving {
    public static void main(String[] args) {
        FileAction fileAction = new FileAction();
        List<Product> productList = fileAction.read("C:\\Users\\yabdramanova\\IdeaProjects\\Task1_ProductMoving\\src\\main\\resources\\products.txt");
        System.out.println(productList.toString());
    }
}
