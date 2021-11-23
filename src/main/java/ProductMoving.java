import ru.tasks.entity.Store;
import ru.tasks.fileLoader.ProductFileLoader;
import ru.tasks.fileLoader.TxtProductFileLoader;
import ru.tasks.fileSaver.MovementsFileSaver;
import ru.tasks.fileSaver.TxtMovementsFileSaver;
import ru.tasks.service.Move;

import java.util.*;

public class ProductMoving {




    public static void main(String[] args) {
        ProductFileLoader txtProductFileLoader = new TxtProductFileLoader();
        try{
            Map<String, Store> storeList = txtProductFileLoader.readProductList(args[0]);
            storeList.forEach((k, v) -> System.out.println(v.info()));
            Move movement = new Move();
            System.out.println(movement.findAllCombinations(storeList));
            MovementsFileSaver txtMovementsFileSaver = new TxtMovementsFileSaver();
            txtMovementsFileSaver.saveMovements(movement.findAllCombinations(storeList), args[1], false, false);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
