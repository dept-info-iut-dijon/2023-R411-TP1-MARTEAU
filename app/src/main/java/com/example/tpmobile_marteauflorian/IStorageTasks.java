package com.example.tpmobile_marteauflorian;

public interface IStorageTasks {

    /**
     * Permet d'obtenir la liste des tâches
     * @return la liste des tâches
     */
    public abstract TaskList ReadTasks();


    /**
     * Permet d'ajouter une tâche
     * @param task tâche à ajouter
     */
    public abstract void AddTask(Task task);

    /**
     * Permet de mettre à jour une tâche
     * @param task tâche à modifier
     * @param int position de la tache
     */
    public abstract void UpdateTask(int position,Task task);

    /**
     * Permet de supprimer une tâche
     * @param task tâche à supprimer
     */
    public abstract void DeleteTask(Task task);
}
