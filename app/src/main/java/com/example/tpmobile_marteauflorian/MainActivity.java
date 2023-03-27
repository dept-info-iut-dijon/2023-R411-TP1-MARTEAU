package com.example.tpmobile_marteauflorian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Adapter pour lier la listview aux tâches
     */
    private ArrayAdapter<TaskList> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FakeStorageDAO dao = new FakeStorageDAO();
        TaskList tasklist = dao.ReadTasks();

        // Création de l'adapter
        adapter = new ArrayAdapter<TaskList>(tasklist, android.R.layout.simple_list_item_1,android.R.id.text1, tasklist.taches);

        ListView listView = (ListView) findViewById(R.id.tacheView);
        listView.setAdapter(adapter);

    }
}