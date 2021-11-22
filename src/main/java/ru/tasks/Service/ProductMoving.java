package ru.tasks.Service;


import ru.tasks.Entity.Product;
import ru.tasks.Entity.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ProductMoving {

    private List<Product> findListToMove(Store store1, Store store2){
        if(store1.avgCost().compareTo(store2.avgCost()) < 0){
            Store tempStore = store1;
            store1 = store2;
            store2 = tempStore;
        }
        BigDecimal lowerLimit = store2.avgCost();
        BigDecimal upperLimit = store1.avgCost();
        return store1.getProducts().values().stream().filter(x -> (x.getCost().compareTo(lowerLimit) < x.getCost().compareTo(upperLimit))).collect(Collectors.toList());
    }

    public void findAllCombinations(Store store1, Store store2){
        /* always move from 1 to 2 */

        if(store1.avgCost().compareTo(store2.avgCost()) < 0){
            Store tempStore = store1;
            store1 = store2;
            store2 = tempStore;
        }
        Stack<Store> items = new Stack<>();
        List<Product> productToMove = findListToMove(store1, store2);
        items.add(store1);
        //productToMove.forEach(x -> items.add(x));
        StringBuilder combinations = new StringBuilder("from " + store1.getName() + " move products to " + store1.getName() + ":%n");
        combinations.append(String.format("previous average cost of the %1$s is %2$.2f and average cost of the %3$s is %4$7.2f", store1.getName(), store1.avgCost(), store2.getName(), store2.avgCost()));
         int index = 0;
         while (!items.isEmpty()){
             while (!productToMove.isEmpty()){
                 Store tempStore1 = new Store(store1.getName(), store1.getProducts());
                 tempStore1.addProduct(productToMove.get(0));
                 items.add(tempStore1);
                 combinations.append(String.format("%1$d. %2$s average cost after moving is %3$.2f", index, productToMove.get(0).getName(), tempStore1.avgCost()));
                 productToMove = findListToMove(tempStore1, store2);
             }

         }
    }

    private class Tree{
        private class Node{
            Product data;
            Node parent;
            List<Node> children;

            public Node(Product data) {
                this.data = data;
                this.children = new LinkedList<>();
            }

            public void addChild(Node node){
                children.add(node);
            }
        }
        private Node root;

        Tree(Node root){
            this.root = root;
        }

        public void add(Product product){
            Node child = new Node(product);
        }
    }
}
