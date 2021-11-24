package ru.tasks.fileSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class TxtMovementsFileSaver implements MovementsFileSaver {

    @Override
    public void saveMovements(String movements, String path, boolean append, boolean toNewFile) {
        try {
            File file = new File(path);
            if (toNewFile){
                if(!file.createNewFile()){
                    System.out.println("Cannot create a new file");
                    return;
                }
            }
            try(FileWriter writer = new FileWriter(file, append))
            {
                writer.write(movements);
                writer.flush();
            }catch (FileNotFoundException e) {
                System.out.printf("File %s not found", path);
            }
        }catch(IOException ex){
            System.out.println("Something went wrong!");
        }
    }
}
