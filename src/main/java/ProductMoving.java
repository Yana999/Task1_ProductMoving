import ru.tasks.entity.Store;
import ru.tasks.fileLoader.ProductFileLoader;
import ru.tasks.fileLoader.TxtProductFileLoader;
import ru.tasks.fileSaver.FileSaver;
import ru.tasks.service.FindProductTransfer;
import java.util.*;

public class ProductMoving {

    public static void main(String[] args) {
        if(args.length == 2) {
            ProductFileLoader txtProductFileLoader = new TxtProductFileLoader();
            Optional<Map<String, Store>> stores = txtProductFileLoader.readProductList(args[0]);
            if(stores.isPresent()) {
                stores.get().values().forEach(v -> System.out.println(v.info()));
                FindProductTransfer movement = new FindProductTransfer();
                movement.findAndSaveAllTransfers(new ArrayList<>(stores.get().values()));
                FileSaver fileSaver = new FileSaver();
                fileSaver.saveTransfer(args[1], false, false);
            }
        }else {
            System.out.println("Please, check input and output paths");
        }
    }
}
