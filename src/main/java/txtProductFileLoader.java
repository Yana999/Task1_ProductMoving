import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class txtProductFileLoader {

    public Map<String, Store> readProductList(String path){
        HashMap<String, Store> stores = new HashMap();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                String name = fileReader.readLine();
                double weight = Double.parseDouble(fileReader.readLine());
                BigDecimal cost = new BigDecimal(fileReader.readLine());
                String storeName = fileReader.readLine();
                 Store store = new Store(storeName);
                 store.addProduct(new Product(name, weight, cost));
                 stores.putIfAbsent(store.getName(), store);

            }
        }catch (FileNotFoundException e) {
            System.out.println("File " + path + " not found");
        }catch (NumberFormatException e){
        System.out.println("Cannot read file " + path + "! File contains a number " + "in a wrong format in line");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return stores;
    }
}
