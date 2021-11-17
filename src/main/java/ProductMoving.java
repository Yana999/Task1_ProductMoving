import java.util.*;

public class ProductMoving {
    public static void main(String[] args) {
        txtProductFileLoader txtProductFileLoader = new txtProductFileLoader();
        Scanner consoleScanner = new Scanner(System.in);
        String txtFilePath = consoleScanner.nextLine();
        Map<String, Store> productList = txtProductFileLoader.readProductList(txtFilePath);
        System.out.println(productList.toString());

    }
}
