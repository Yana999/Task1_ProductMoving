package ru.tasks.fileLoader;

import ru.tasks.entity.Product;
import ru.tasks.entity.Store;
import ru.tasks.exseption.InputValueException;

import java.io.*;
import java.util.*;

public class TxtProductFileLoader implements ProductFileLoader {

    public Optional<Map<String,Store>> readProductList(String path) throws RuntimeException{
        HashMap<String, Store> stores = new LinkedHashMap<>();
        long lineNumber = 0;

        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                ++lineNumber;
                String line = fileReader.readLine();
                if(!line.isEmpty()){
                    String[] splitLine = line.split(";");
                    if(splitLine.length == 4) {
                        try {
                            String storeName = splitLine[3].trim();
                            stores.putIfAbsent(storeName, Store.parseStore(storeName));
                            Product newProduct = Product.parseProduct(splitLine[0], splitLine[1], splitLine[2]);
                            Product duplicate = stores.get(storeName).addProduct(newProduct);
                            if (duplicate != null) {
                                System.out.printf("Found the duplicate %1$s of product %2$s in line %3$d. Duplicate was not saved %n", newProduct, duplicate, lineNumber);
                            }
                        } catch (InputValueException e) {
                            System.out.println(e.getMessage() + " in line " + lineNumber);
                        }
                    }else{
                        System.out.println("Impossible to read product in wrong format");
                    }
                }else{
                    System.out.println("Faced an empty line number " + lineNumber);
                }
            }
        }catch (FileNotFoundException e) {
            System.out.printf("File %s not found %n", path);
            return Optional.empty();
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return Optional.of(stores);
    }

}
