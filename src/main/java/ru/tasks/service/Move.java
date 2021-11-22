package ru.tasks.service;


import ru.tasks.entity.Product;
import ru.tasks.entity.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Move {

    private List<Product> findListToMove(Store store1, Store store2){
        /* always move from 1 to 2
        * have to move from lowerAvg to UpperAvg*/
        BigDecimal lowerLimit = store2.avgCost();
        BigDecimal upperLimit = store1.avgCost();
        return store1.getProducts().values().stream()
                .filter(x -> (x.getCost().compareTo(lowerLimit) > 0))
                .filter(x -> (x.getCost().compareTo(upperLimit) < 0))
                .collect(Collectors.toList());
    }

    public String findAllCombinations(Store store1, Store store2){
        if(store1.avgCost().compareTo(store2.avgCost()) < 0){
            Store tempStore = store1;
            store1 = store2;
            store2 = tempStore;
        }

        StringBuilder combinations = new StringBuilder("from " + store1.getName() + " move products to " + store1.getName() + ":\n");
        combinations.append(String.format("previous average cost of the %1$s " +
                        "is %2$.2f and average cost of the %3$s is %4$7.2f",
                store1.getName(), store1.avgCost(), store2.getName(), store2.avgCost()));
        return recursionCombination("", 0, findListToMove(store1, store2), new ArrayList<>(), combinations);
    }

    public static String recursionCombination(String accumulator, int count, List<Product> set, List<Product> visited, StringBuilder a) {
        for (int i = 0; i < set.size(); i++) {
            if (visited.contains(set.get(i))) break;
            a.append(accumulator + set.get(i) + "\n");
            if (count < set.size() - 1) {
                List<Product> visited1 = new ArrayList<>(visited);
                visited1.add(set.get(i));
                recursionCombination(accumulator + set.get(i), count+1, set, visited1, a);
            }
        }
        return a.toString();
    }


}
