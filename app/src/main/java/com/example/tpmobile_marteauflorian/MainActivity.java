package com.example.tpmobile_marteauflorian;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    /**
     * Adapter pour lier la listview aux tâches
     */
    private ArrayAdapter<Task> adapter;

    /**
     * listview de l'ihm
     */
    private ListView listView;

    /**
     * Représente les tâches
     */
    private ArrayList<Task> taches;

    /**
     * Représente le DAO pour manipuler les tâches
     */
    private IStorageTasks storageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des tâches
        storageDao = new FakeStorageDAO();
        TaskList tasklist = storageDao.ReadTasks();
         taches = tasklist.getTaches();

        // Création de l'adapter
        adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1,android.R.id.text1, taches);

        // Récupération des elements graphiques
        this.listView = (ListView) findViewById(R.id.tacheView);
        FloatingActionButton buttonCreate = (FloatingActionButton) findViewById(R.id.buttonCreate);

        // Set de l'adaptateur
        this.listView.setAdapter(adapter);

        // Ouvrir fenêtre edition quand on clique sur un item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                intent.putExtra("tache", taches.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent,1);
            }
        });

        // Ouvrir fenêtre edition quand on veut créer un item
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Change de page
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                startActivityForResult(intent,1);
            }
        });

        listView.setOnItemLongClickListener(this::onItemLongClick);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // CREATE
        if(resultCode==1)
        {
            Task t = (Task)data.getExtras().getSerializable("tache");
            this.storageDao.AddTask(t);
            adapter.add(t);

        }
        // UPDATE
        else if (resultCode==2){
            Task t = (Task)data.getExtras().getSerializable("tache");
            this.storageDao.UpdateTask(t);

            int position = (int)data.getExtras().getSerializable("position");
            Task previousTask =this.adapter.getItem(position);
            this.adapter.remove(previousTask);
            this.adapter.insert(t,position);
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Valider la suppression");
        dlgAlert.setMessage("Supprimer la tâche "+adapter.getItem(i).getTitle()+ " ?");
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int position) {
                adapter.remove(taches.get(i));
            }
        } );
        dlgAlert.setCancelable(true);
        dlgAlert.setNegativeButton("Annuler", null);
        dlgAlert.create().show();
        return true;
    }

}