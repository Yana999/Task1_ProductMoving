import ru.tasks.Entity.Store;
import ru.tasks.FileActrion.txtProductFileLoader;

import java.math.BigDecimal;
import java.util.*;

public class ProductMoving {
    public static void main(String[] args) {
        txtProductFileLoader txtProductFileLoader = new txtProductFileLoader();
        Scanner consoleScanner = new Scanner(System.in);
        String txtFilePath = consoleScanner.nextLine();
        Map<String, Store> productList = txtProductFileLoader.readProductList(txtFilePath);
        productList.forEach((k, v) -> System.out.println(v.info()));
        System.out.println((double)(12 + 54 + 2 + 16 + 22 + 24) / 6);
        System.out.println((double)(54 + 23 + 11 + 22) / 4);
        System.out.println();
        System.out.println((double)(12 + 54 + 2 + 16 + 22) / 5);
        System.out.println((double)(54 + 23 + 11 + 22 + 24) / 5);
        System.out.println();
        System.out.println((double)(12 + 54 + 2 + 16) / 4);
        System.out.println((double)(54 + 23 + 11 + 22 + 24 + 22) / 6);




    }
}
