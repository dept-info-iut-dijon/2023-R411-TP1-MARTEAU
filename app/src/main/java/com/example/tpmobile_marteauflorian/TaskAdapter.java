package com.example.tpmobile_marteauflorian;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task>
{

    /**
     * Représente le model avec la liste des tâches
     */
    private final TaskList model;

    /**
     * Représente le dao pour la persistence
     */
    private final IStorageTasks storageDao;

    /**
     * Constructeur de la classe TaskAdapter
     * @param context
     * @param resource
     * @param tasks
     */
    public TaskAdapter(@NonNull Context context, int resource, TaskList tasks,IStorageTasks dao) {
        super(context, resource, tasks.getTaches());
        model = tasks;
        this.storageDao = dao;
    }


    /**
     * Permet d'ajouter une tâche
     * @param task tâche à ajouter
     */
    public void AddTask(Task task)
    {
        this.model.AddTask(task);
        notifyDataSetChanged();
    }

    /**
     * Permet de modifier une tâche
     * @param task tâche à modifier
     */
    public void UpdateTask(int position,Task task)
    {
        this.model.getTaches().set(position,task);
        notifyDataSetChanged();
    }

    /**
     * Permet de supprimer une tâche
     * @param task
     */
    public void DeleteTask(Task task)
    {
        this.model.getTaches().remove(task);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.taskview,parent,false);
        }
        final TaskViewHolder holder;
        if (convertView.getTag() == null){
            holder = new TaskViewHolder();
            holder.title = convertView.findViewById(R.id.taskText);
            holder.completed = convertView.findViewById(R.id.checkTask);
            holder.priority = convertView.findViewById(R.id.rateTask);
            convertView.setTag(holder);

        }
        else {
            holder = (TaskViewHolder) convertView.getTag();
        }

        Task task = getItem(position);
        holder.title.setText(task.getTitle());
        holder.completed.setChecked(task.isCompleted());
        holder.priority.setRating(task.getPriority());

        holder.completed.setTag(task);
        holder.completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox c = (CheckBox) compoundButton;
                Task task = (Task) c.getTag();
                if (b) {
                    task.setCompleted(true);
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.completed.setVisibility(View.INVISIBLE);
                } else {
                    task.setCompleted(false);
                }

                // Mise à jour des données
                storageDao.UpdateTask(model.getTaches().indexOf(task),task);
            }
        });

        return convertView;
    }

    class TaskViewHolder{
        private TextView title;
        private CheckBox completed;
        private RatingBar priority;
    }

}


