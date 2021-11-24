package ru.tasks.fileSaver;

public interface MovementsFileSaver {
 void saveMovements(String movements, String path, boolean append, boolean toNewFile);
}
