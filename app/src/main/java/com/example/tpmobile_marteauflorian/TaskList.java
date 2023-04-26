package com.example.tpmobile_marteauflorian;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
    public TaskList(ArrayList<Task> taches )
    {
        this.taches = taches;
    }

    /**
     * Permet d'ajouter une tâche dans la liste
     * @param task tâche à ajouter
     */
    public void AddTask(Task task){
        this.taches.add(task);
    }




    /**
     * Trier par ordre de priorité
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<Task> getTaskOrdered(String order){
        ArrayList<Task> taches = this.taches;

        switch(order){
            case "titre":{
                taches.sort(Comparator.comparing(Task::getTitle));
            }
            case "rate":{
                taches.sort(Comparator.comparing(Task::getPriority));
            }
        }

        return taches;
    }



}
