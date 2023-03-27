package com.example.tpmobile_marteauflorian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class EditTaskActivity extends AppCompatActivity {

    /**
     * Représente la tâche sélectionnée
     */
    private Task tache;

    /**
     * Représente la barre de rating de la priorité de la tâche
     */
    private RatingBar ratingBar;

    /**
     * Représente le titre de la tâche
     */
    private EditText titre;

    /**
     * Représente la description de la tâche
     */
    private EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Récupération des champs
        ratingBar = (RatingBar) findViewById(R.id.rateTache);
        description = (EditText) findViewById(R.id.descTache);
        titre = (EditText) findViewById(R.id.titleTache);

        // Récupération de la tache
        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        // Si on edite une tache
        if (params != null) {
            tache = (Task) params.getSerializable("tache");

            // Mise à jour des champs
            ratingBar.setRating(tache.getPriority());
            description.setText(tache.getDescription());
            titre.setText(tache.getTitle());
        }


        // Ajout du listener
        FloatingActionButton buttonRetour = (FloatingActionButton) findViewById(R.id.retour);
        buttonRetour.setOnClickListener(this::onClick);
    }

    /**
     * Permet de sauvegarder et de quitter l'activité
     * @param view
     */
    private void onClick(View view) {
        Intent intent=new Intent(EditTaskActivity.this,MainActivity.class);
        try{
            // Création
            if (this.tache == null){
                this.tache = new Task();
                setResult(1,intent);
            }
            else{
                setResult(2,intent);
                Intent intentTemp = getIntent();
                Bundle params = intentTemp.getExtras();
                int pos = (int) params.getSerializable("position");
                intent.putExtra("position",pos);
            }

            this.tache.setDescription(this.description.getText().toString());
            this.tache.setPriority((int)this.ratingBar.getRating());
            this.tache.setTitle(this.titre.getText().toString());

            intent.putExtra("tache",this.tache);

            finish();
        }
        catch(Exception e){
            setResult(0,intent);
            finish();
        }
    }

}