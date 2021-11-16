import java.io.*;
import java.util.*;

public class FileAction {

    public Map<String, Store> readProductList(String path){
        HashMap<String, Store> stores = new HashMap();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                String name = fileReader.readLine();
                double weight = Double.parseDouble(fileReader.readLine());
                double cost = Double.parseDouble(fileReader.readLine());
                String storeName = fileReader.readLine();
                 if (stores.containsKey(storeName)) {
                     stores.get(storeName).addProduct(new Product(name, weight, cost));
                 }else{
                     Store store = new Store(storeName);
                     store.addProduct(new Product(name, weight, cost));
                     stores.put(storeName, store);
                 }

            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (NumberFormatException e){
        System.out.println("Cannot read file! File contains a number in a wrong format");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return stores;
    }
}
