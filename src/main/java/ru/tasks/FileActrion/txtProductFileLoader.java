package ru.tasks.FileActrion;

import ru.tasks.Entity.Product;
import ru.tasks.Entity.Store;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class txtProductFileLoader implements ProductFileLoader {

    public Map<String, Store> readProductList(String path){
        HashMap<String, Store> stores = new HashMap<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                String[] line = fileReader.readLine().split(";");
                stores.putIfAbsent(line[3], new Store(line[3]));
                stores.get(line[3]).addProduct(new Product(line[0], Double.parseDouble(line[1]), new BigDecimal(line[2])));
            }
        }catch (FileNotFoundException e) {
            System.out.printf("File %s not found", path);
        }catch (NumberFormatException e){
        System.out.printf("Cannot read file %s!%n File contains a number %s in a wrong format in line", path, e.getMessage());
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return stores;
    }

}
