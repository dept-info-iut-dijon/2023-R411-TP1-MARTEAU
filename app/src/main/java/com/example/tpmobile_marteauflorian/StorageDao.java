package com.example.tpmobile_marteauflorian;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class StorageDao implements IStorageTasks {

    /**
     * Représente les options pour sauvegarder les objets
     */
    private SharedPreferences sharedPreferences;

    /**
     * Représente l'objet pour créer le json
     */
    private Gson gson;

    /**
     * Représente les tâches
     */
    private TaskList tasks;

    /**
     * Constructeur de la classe StorageDao
     */
    public StorageDao(SharedPreferences sp)
    {
        sharedPreferences = sp;
        gson = new Gson();
        this.tasks = new TaskList(new ArrayList<Task>());
    }


    @Override
    public TaskList ReadTasks()
    {
        String task = this.sharedPreferences.getString("tasks","");
        if (!task.isEmpty()){
            this.tasks = this.gson.fromJson(task,TaskList.class);
        }
        return this.tasks;
    }

    @Override
    public void AddTask(Task task)
    {
        this.tasks.AddTask(task);
        Save();
    }

    @Override
    public void UpdateTask(int position,Task task)
    {
        this.tasks.getTaches().set(position,task);
        Save();
    }

    @Override
    public void DeleteTask(Task task)
    {
        this.tasks.getTaches().remove(task);
        Save();
    }

    /**
     * Permet de save les changements
     */
    private void Save(){
        SharedPreferences.Editor ed = this.sharedPreferences.edit();
        ed.putString("tasks",gson.toJson(tasks));
        ed.apply();
    }

    public void setTasks(ArrayList<Task>  taches){
        this.tasks.taches = taches;
    }
}
