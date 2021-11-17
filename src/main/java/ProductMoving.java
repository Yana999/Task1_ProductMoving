import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProductMoving {
    public static void main(String[] args) {
        FileAction fileAction = new FileAction();
        Scanner consoleScanner = new Scanner(System.in);
        String txtFilePath = consoleScanner.nextLine();
        Map<String, Store> productList = fileAction.readProductList(txtFilePath);
        System.out.println(productList.toString());

    }
}
