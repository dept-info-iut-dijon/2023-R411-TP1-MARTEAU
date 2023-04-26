package com.example.tpmobile_marteauflorian;

import android.app.Notification;

import java.util.ArrayList;

public class FakeStorageDAO implements IStorageTasks {

    /**
     * Liste de tâches temporaires
     */
    private ArrayList<Task> fakeTaches = new ArrayList<Task>();

    /**
     * Constructeur de la classe fakestorageDao
     */
    public FakeStorageDAO(){
        try{
            Task t1 = new Task();
            t1.setTitle("Manger");
            t1.setPriority(4);
            t1.setDescription("Il faut bien manger tous les jours");

            Task t2 = new Task();
            t2.setTitle("Dormir");
            t2.setPriority(0);
            t2.setDescription("Il faut un peu dormir");

            fakeTaches.add(t1);
            fakeTaches.add(t2);
        }
        catch(Exception e){

        }

    }

    @Override
    public TaskList ReadTasks() {
        return new TaskList(this.fakeTaches);
    }

    @Override
    public void AddTask(Task task) {

    }

    @Override
    public void UpdateTask(Task task) {

    }

    @Override
    public void DeleteTask(Task task) {

    }
}
