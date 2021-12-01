package ru.tasks.service;


import ru.tasks.entity.Product;
import ru.tasks.entity.Store;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class FindProductTransfer {

    public void findAndSaveAllTransfers(List<Store> stores, String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            stores.sort(Comparator.comparing(Store::avgCost));
            for (int i = 0; i < stores.size(); ++i) {
                for (int j = i + 1; j < stores.size(); ++j) {
                    recursionCombination(new ArrayList<>(), stores.get(i), stores.get(j), writer);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File " + path + " not found");
        }catch(IOException e) {
            System.out.println("Cannot continue write to file! Something went wrong");
        }
    }

    private String formatTransfer(Store store1, Store store2, List<Product> products){
        StringBuilder s = new StringBuilder(String.format("%s -> %s : (%s; %s) -> (%s %s)%n",  store1.getName(),
                store2.getName(),store1.avgCost(), store2.avgCost(), store1.avgCostWithout(products), store2.avgCostWith(products)));
        products.forEach(x -> s.append(x.formatString()));
        s.append("\n");
        return s.toString();
    }

    private BigDecimal countAvg(List<Product> products){
        if(products.isEmpty()){
            return BigDecimal.ZERO;
        }
        BigDecimal sum = BigDecimal.ZERO;
        for(Product product : products){
            sum = sum.add(product.getCost());
        }
        return sum.divide(new BigDecimal(products.size()), 2, RoundingMode.HALF_UP);
    }

       private void recursionCombination(List<Product> movedProducts, Store store1, Store store2, FileWriter writer) throws IOException {
        List<Product> toTransfer  = store1.getProducts().values().stream().sorted(Comparator.comparing(Product::getCost)).collect(Collectors.toList());
        for (int i = 0; i < toTransfer.size(); i++) {
            if (movedProducts.contains(toTransfer.get(i))) break;
            List<Product> tryToMove = new ArrayList<>(movedProducts);
            tryToMove.add(toTransfer.get(i));
            if(countAvg(tryToMove).compareTo(store1.avgCost()) >= 0 && countAvg(tryToMove).compareTo(store2.avgCost()) <= 0) {
                if(store1.avgCost().compareTo(store1.avgCostWithout(tryToMove)) > 0 && store2.avgCost().compareTo(store2.avgCostWith(tryToMove)) > 0) {
                    writer.write(formatTransfer(store1, store2, tryToMove));
                }
            }
            if (!toTransfer.isEmpty()) {
                recursionCombination(tryToMove, store1, store2, writer);
            }
        }
    }
}
