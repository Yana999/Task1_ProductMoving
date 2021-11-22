import ru.tasks.entity.Store;
import ru.tasks.productLoader.ProductFileLoader;
import ru.tasks.productLoader.TxtProductFileLoader;
import ru.tasks.service.Move;

import java.util.*;
import java.util.stream.Collectors;

public class ProductMoving {




    public static void main(String[] args) {
        ProductFileLoader txtProductFileLoader = new TxtProductFileLoader();
        try{
        Map<String, Store> productList = txtProductFileLoader.readProductList(args[0]);
        productList.forEach((k, v) -> System.out.println(v.info()));
        Move movement = new Move();
            for (Store store : productList.values()) {
                for(Store store2 : productList.values()){
                    System.out.println(movement.findAllCombinations(store,store2));
                }
            }

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }



       /* System.out.println((double)(12 + 54 + 2 + 16 + 22 + 24) / 6);
        System.out.println((double)(54 + 23 + 11 + 22) / 4);
        System.out.println();
        System.out.println((double)(12 + 54 + 2 + 16 + 22) / 5);
        System.out.println((double)(54 + 23 + 11 + 22 + 24) / 5);
        System.out.println();
        System.out.println((double)(12 + 54 + 2 + 16) / 4);
        System.out.println((double)(54 + 23 + 11 + 22 + 24 + 22) / 6);*/




    }
}
