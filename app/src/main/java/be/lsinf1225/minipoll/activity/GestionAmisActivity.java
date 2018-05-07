package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

public class GestionAmisActivity extends AppCompatActivity {

    Button liste;
    Button chercher;
    Button demandes;
    TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_amis);
        liste = findViewById(R.id.liste);
        chercher = findViewById(R.id.chercher);
        demandes = findViewById(R.id.demandes);
        titre = findViewById(R.id.gestion);

        setFontBut(liste);
        setFontBut(chercher);
        setFontBut(demandes);
        setFontTxt(titre);

         liste.setOnClickListener(liste_ls);
         chercher.setOnClickListener(chercher_ls);
         demandes.setOnClickListener(demandes_ls);



    }

    View.OnClickListener liste_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG - AMI","Bouton liste");
            Intent liste_amis = new Intent(getApplicationContext(), ListeAmisActivity.class);
            startActivity(liste_amis);
        }
    };


    View.OnClickListener chercher_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG - AMI", "Bouton liste");
            Intent liste_amis = new Intent(getApplicationContext(), ChercherAmisActivity.class);
            startActivity(liste_amis);
        }
    };


    View.OnClickListener demandes_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG - AMI", "Bouton liste");
            Intent liste_amis = new Intent(getApplicationContext(), DemandesAmisActivity.class);
            startActivity(liste_amis);
        }
    };

    
    public void setFontTxt(TextView textView) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            textView.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", textView + " not found", e);
        }
    }

    public void setFontBut(Button button) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            button.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", button + " not found", e);
        }
    }
}
