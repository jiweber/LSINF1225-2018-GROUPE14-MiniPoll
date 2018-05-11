package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

public class NouveauActivity extends AppCompatActivity {

    Button sondage;
    Button questionnaire;
    Button aide;
    TextView nouveau;
    TextView interrogation;
    ImageView poll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau);
        sondage = findViewById(R.id.button8);
        questionnaire = findViewById(R.id.button7);
        aide = findViewById(R.id.button9);
        nouveau = findViewById(R.id.textView);
        poll = findViewById(R.id.imageView);
        interrogation = findViewById(R.id.textView4);

        setFontTxt(nouveau);
        setFontTxt(interrogation);
        setFontBut(sondage);
        setFontBut(questionnaire);
        setFontBut(aide);

        sondage.setOnClickListener(nouvsond);
        aide.setOnClickListener(nouvdil);
        questionnaire.setOnClickListener(nouvquest);


    }


    View.OnClickListener nouvsond = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent sond = new Intent(getApplicationContext(), NewSondageActivity.class);
            startActivity(sond);
        }
    };

    View.OnClickListener nouvdil = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent dil = new Intent(getApplicationContext(), NewDilemmeActivity.class);
            startActivity(dil);
        }
    };

    View.OnClickListener nouvquest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent quest = new Intent(getApplicationContext(), NewQuestionnaireActivity.class);
            startActivity(quest);
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
