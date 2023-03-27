package com.example.tpmobile_marteauflorian;

public interface IStorageTasks {

    /**
     * Permet d'obtenir la liste des tâches
     * @return la liste des tâches
     */
    public abstract TaskList ReadTasks();
}
