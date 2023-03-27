package com.example.tpmobile_marteauflorian;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TaskList {

    /**
     * Liste des tâches à réaliser
     */
    public ArrayList<Task> taches;

    /**
     * Permet d'obtenir toutes les tâches
     * @return les tâches
     */
    public ArrayList<Task> getTaches() {
        return taches;
    }

    /**
     * Constructeur de la classe TaskList
     */
    public TaskList(){
        this.taches = new ArrayList<>();
    }

    /**
     * Permet d'ajouter une tâche dans la liste
     * @param task tâche à ajouter
     */
    public void AddTask(Task task){
        this.taches.add(task);
    }





}
