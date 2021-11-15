import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAction {
    public List<Product> read(String path){
        List<Product> productList = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(path))){
            while (fileReader.ready()) {
                String name = fileReader.readLine();
                double weight = Double.parseDouble(fileReader.readLine());
                double cost = Double.parseDouble(fileReader.readLine());
                String store = fileReader.readLine();
                productList.add(new Product(name, weight, cost, store));
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (NumberFormatException e){
        System.out.println("Cannot read file! File contains a number in a wrong format");
        }catch (IOException e){
            System.out.println("Something went wrong");
        }
        return productList;
    }
}
