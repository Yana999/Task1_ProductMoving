package ru.tasks.service;


import ru.tasks.entity.Product;
import ru.tasks.entity.Store;
import ru.tasks.fileSaver.FileSaver;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FindProductTransfer {

    private List<Product> findListToMove(Store store1, Store store2, List<Product> alreadyMoved){
        /* always move from 1 to 2
         * have to move from lowerAvg to UpperAvg*/
        if(store1.getProducts().size() == alreadyMoved.size()) {
            return new LinkedList<>();
        }
        BigDecimal lowerLimit = store1.avgCostWithout(alreadyMoved);
        BigDecimal upperLimit = store2.avgCostWith(alreadyMoved);
        return store1.getProducts().values().stream()
                .filter(x -> (x.getCost().compareTo(lowerLimit) > 0))
                .filter(x -> (x.getCost().compareTo(upperLimit) < 0))
                .collect(Collectors.toList());
    }

    public void findAndSaveAllTransfers(List<Store> stores) {
        for (Store store1 : stores) {
            for (Store store2 : stores) {
                if (!store1.equals(store2)) {
                    recursionCombination(0, new ArrayList<>(), store1, store2);
                }
            }
        }
    }

    private void formatTransfer(Store store1, Store store2, List<Product> products){
        StringBuilder s = new StringBuilder(String.format("%s -> %s : (%s; %s) -> (%s %s)%n",  store1.getName(),
                store2.getName(),store1.avgCost(), store2.avgCost(), store1.avgCostWithout(products), store2.avgCostWith(products)));
        products.forEach(x -> s.append(x.formatString()));
        System.out.println(s);
    }

    private void recursionCombination(int count, List<Product> visited, Store store1, Store store2) {
        List<Product> set  = findListToMove(store1, store2, visited);
        for (int i = 0; i < set.size(); i++) {
            if (visited.contains(set.get(i))) break;
            List<Product> visited1 = new ArrayList<>(visited);
            visited1.add(set.get(i));
            formatTransfer(store1, store2, visited1);
            if (count < set.size() - 1) {
                recursionCombination(count+1, visited1, store1, store2);
            }
        }
    }


}
