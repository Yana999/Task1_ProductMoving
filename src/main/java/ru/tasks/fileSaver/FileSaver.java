package ru.tasks.fileSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public FileWriter saveTransfer(String path, boolean append, boolean toNewFile) {
        try{
            File file = new File(path);
            if (toNewFile){
                if(!file.createNewFile()){
                    System.out.println("Cannot create a new file " + path);
                }
            }
            try(FileWriter writer = new FileWriter(file, append))
            {
                return writer;
            }catch (FileNotFoundException e) {
                System.out.printf("File %s not found", path);
            }
        }catch(IOException ex){
            System.out.println("Something went wrong! Cannot write to file " + path);
        }
        return null;
    }
}
