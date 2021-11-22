package ru.tasks.FileActrion;

import ru.tasks.Entity.Product;
import ru.tasks.Entity.Store;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class txtProductFileLoader implements ProductFileLoader {

    public Map<String, Store> readProductList(String path){
        HashMap<String, Store> stores = new HashMap<>();
        long lineNumber = 0;

        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                ++lineNumber;
                String line = fileReader.readLine();
                if(!line.isEmpty()){
                    String[] splitLine = line.split(";");
                    for(int i = 0; i < splitLine.length; ++i){
                        splitLine[i] = splitLine[i].trim();
                    }
                    try {
                        if (splitLine[1].matches("\\D")|| (splitLine[1].contains(".") && !splitLine[1].matches("\\d+.\\d{1,3}"))) {
                            System.out.printf("inappropriate value of the weight %s in line %d%n", splitLine[1], lineNumber);
                        } else if (splitLine[2].matches("\\D")|| (splitLine[2].contains(".") && !splitLine[2].matches("\\d+.\\d{1,3}"))) {
                            System.out.printf("inappropriate value of the cost %s in line %d%n", splitLine[2], lineNumber);
                        } else {
                            stores.putIfAbsent(splitLine[3], new Store(splitLine[3]));
                            stores.get(splitLine[3]).addProduct(new Product(splitLine[0], Double.parseDouble(splitLine[1]), new BigDecimal(splitLine[2])));
                        }
                    }catch (NumberFormatException e) {
                        System.out.printf("File %s contains a number %s in a wrong format in line number %d%n", path, e.getMessage().substring(e.getMessage().indexOf("\""), e.getMessage().lastIndexOf("\"")), lineNumber);
                    }catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + " in line " + lineNumber);
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.out.printf("File %s not found", path);
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return stores;
    }

}
