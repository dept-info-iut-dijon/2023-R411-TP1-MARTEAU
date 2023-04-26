package com.example.tpmobile_marteauflorian;

import android.content.SharedPreferences;

public class StorageDao implements IStorageTasks {

    /**
     * Représente les options pour sauvegarder les objets
     */
    private SharedPreferences sharedPreferences;

    /**
     * Constructeur de la classe StorageDao
     */
    public StorageDao(){


    }
    @Override
    public TaskList ReadTasks() {
        return null;
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
