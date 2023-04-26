package com.example.tpmobile_marteauflorian;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

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
     * Représente le DAO pour manipuler les tâches
     */
    private IStorageTasks storageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des tâches
        storageDao = new StorageDao(getPreferences(MODE_PRIVATE));
        TaskList tasklist = storageDao.ReadTasks();

        // Création de l'adapter
        adapter = new TaskAdapter(this,android.R.layout.simple_list_item_1, tasklist,storageDao);

        // Récupération des elements graphiques
        this.listView = (ListView) findViewById(R.id.tacheView);
        FloatingActionButton buttonCreate = (FloatingActionButton) findViewById(R.id.buttonCreate);

        // Set de l'adaptateur
        this.listView.setAdapter(adapter);

        // Ouvrir fenêtre edition quand on clique sur un item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Task t = (Task)listView.getItemAtPosition(position);
                if (t.isCompleted() == false){
                    Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                    intent.putExtra("tache", (Task)listView.getItemAtPosition(position));
                    intent.putExtra("position", position);
                    startActivityForResult(intent,1);
                }
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

        // Trier
        ((RadioButton)findViewById (R.id.noTri)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    storageDao.ReadTasks().taches = tasklist.getTaches();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ((RadioButton)findViewById (R.id.triTitre)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    storageDao.ReadTasks().taches = tasklist.getTaskOrdered("titre");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ((RadioButton)findViewById (R.id.triPriority)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    storageDao.ReadTasks().taches = tasklist.getTaskOrdered("rate");
                    adapter.notifyDataSetChanged();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // CREATE
        if(resultCode==1)
        {
            Task t = (Task)data.getExtras().getSerializable("tache");

            this.storageDao.AddTask(t);
            this.adapter.notifyDataSetChanged();
        }
        // UPDATE
        else if (resultCode==2){
            Task t = (Task)data.getExtras().getSerializable("tache");
            int position = (int)data.getExtras().getSerializable("position");


            Task previousTask =this.adapter.getItem(position);
            this.storageDao.UpdateTask(position,t);
            this.adapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Valider la suppression");
        dlgAlert.setMessage("Supprimer la tâche "+adapter.getItem(i).getTitle()+ " ?");
        dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int position) {
                adapter.remove(adapter.getItem(i));
            }
        } );
        dlgAlert.setCancelable(true);
        dlgAlert.setNegativeButton("Annuler", null);
        dlgAlert.create().show();
        return true;
    }

}