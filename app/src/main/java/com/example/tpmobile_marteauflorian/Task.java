package com.example.tpmobile_marteauflorian;

import com.example.tpmobile_marteauflorian.Exceptions.ExceptionPriority;
import com.example.tpmobile_marteauflorian.Exceptions.ExceptionTitleVide;

public class Task  {

    private String title;
    private String description;
    private int priority;
    private boolean completed = false;


    /**
     * Permet de savoir si la tâche est complétée
     * @return est ce que la tâche est completée
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Permet de modifier si une tâche est complétée
     * @param completed est ce que la tâche est complétée
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Permet d'obtenir la priorité de la tâche
     * @return la priorité
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Permet de modifier la priorité d'une tâche
     * @param priority nouvelle priorité de la tâche
     */
    public void setPriority(int priority) throws Exception {
        if (priority > 4 || priority < 0){
            throw new ExceptionPriority();
        }
        this.priority = priority;
    }

    /**
     * Permet d'obtenir la description de la tâche
     * @return la description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de modifier la description de la tâche
     * @param description nouvelle description
     */
    public void setDescription(String description ) {
        this.description = description;
    }

    /**
     * Permet d'obtenir le titre de la tâche
     * @return le titre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Permet de modifier le titre de la tâche
     * @param title nouveau titre de la tâche
     */
    public void setTitle(String title) throws Exception {
        if (title == null ||title == "" ){
            throw new ExceptionTitleVide();
        }
        this.title = title;
    }


    @Override
    public String toString() {
        return this.title + " " + this.priority;
    }
}
