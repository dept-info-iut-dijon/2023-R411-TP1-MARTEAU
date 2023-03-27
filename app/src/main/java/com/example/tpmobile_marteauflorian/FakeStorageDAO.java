package com.example.tpmobile_marteauflorian;

import android.app.Notification;

public class FakeStorageDAO implements IStorageTasks {


    @Override
    public TaskList ReadTasks() {
        TaskList liste = null;
        try{
            liste = new TaskList();
            Task t1 = new Task();
            t1.setTitle("Manger");
            t1.setPriority(4);
            t1.setDescription("Il faut bien manger tous les jours");

            Task t2 = new Task();
            t2.setTitle("Dormir");
            t2.setPriority(0);
            t2.setDescription("Il faut un peu dormir");

            liste.AddTask(t1);
            liste.AddTask(t2);
        }
        catch(Exception e){

        }

        return liste;
    }
}
