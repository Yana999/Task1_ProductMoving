package ru.tasks.service;


import ru.tasks.entity.Product;
import ru.tasks.entity.Store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Move {

    private List<Product> findListToMove(Store store1, Store store2){
        /* always move from 1 to 2
        * have to move from lowerAvg to UpperAvg*/
        BigDecimal lowerLimit = store1.avgCost();
        BigDecimal upperLimit = store2.avgCost();
        return store1.getProducts().values().stream()
                .filter(x -> (x.getCost().compareTo(lowerLimit) > 0))
                .filter(x -> (x.getCost().compareTo(upperLimit) < 0))
                .collect(Collectors.toList());
    }

    private BigDecimal tempAvgCostTo(Store store, List<Product> alreadyMoved){
        BigDecimal newSum = store.sumCost();
        for(Product product : alreadyMoved){
            newSum = newSum.add(product.getCost());
        }
        return newSum.divide(new BigDecimal((store.getProducts().size() + alreadyMoved.size())), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal tempAvgCostFrom(Store store, List<Product> alreadyMoved){
        BigDecimal newSum = store.sumCost();
        for(Product product : alreadyMoved){
            newSum = newSum.subtract(product.getCost());
        }
        return newSum.divide(new BigDecimal((store.getProducts().size() - alreadyMoved.size())), 2, RoundingMode.HALF_UP);
    }

    private List<Product> findListToMove(Store store1, Store store2, List<Product> alreadyMoved){
        /* always move from 1 to 2
         * have to move from lowerAvg to UpperAvg*/
        if(store1.getProducts().size() == alreadyMoved.size()) {
            return new LinkedList<>();
        }
        BigDecimal lowerLimit = tempAvgCostFrom(store1, alreadyMoved);
        BigDecimal upperLimit = tempAvgCostTo(store2, alreadyMoved);
        return store1.getProducts().values().stream()
                .filter(x -> (x.getCost().compareTo(lowerLimit) > 0))
                .filter(x -> (x.getCost().compareTo(upperLimit) < 0))
                .collect(Collectors.toList());
    }


    public String findAllCombinations(Map<String, Store> stores){
        StringBuilder combinations = new StringBuilder();
        combinations.append(String.format("%-10s %12s %12s %-10s %12s %12s ProductsToMove \n", "From", "previousCost", "NowCost", "To", "previousCost", "NowCost"));
        for (Store store1 : stores.values()) {
            for(Store store2 : stores.values()){
                if(!store1.equals(store2)) {
                    combinations.append(recursionCombination("", 0, findListToMove(store1, store2), new ArrayList<>(), store1, store2));
                }
            }
        }
        return combinations.toString();
    }


    private String recursionCombination(String accumulator, int count, List<Product> set, List<Product> visited, Store store1, Store store2) {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            if (visited.contains(set.get(i))) break;
            List<Product> visited1 = new ArrayList<>(visited);
            visited1.add(set.get(i));
             a.append(String.format("%-10s %12s %12s %-10s %12s %12s %s%n", store1.getName(), store1.avgCost(), tempAvgCostFrom(store1, visited1),
                   store2.getName(), store2.avgCost(), tempAvgCostTo(store2, visited1), accumulator + set.get(i)));

            if (count < set.size() - 1) {
                String s = recursionCombination(accumulator + set.get(i), count+1, findListToMove(store1, store2, visited1), visited1, store1, store2);
                a.append(s);
                if(count + 2 != set.size()){
                    a.append("\n");
                }
            }
        }
        return a.toString();
    }


}
