package be.lsinf1225.minipoll;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class NouveauActivity extends AppCompatActivity {

    Button sondage;
    Button questionnaire;
    Button aide;
    TextView nouveau;
    TextView interrogation;
    ImageView poll;
    ImageButton retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau);
        sondage = findViewById(R.id.button8);
        questionnaire = findViewById(R.id.button7);
        aide = findViewById(R.id.button9);
        nouveau = findViewById(R.id.textView);
        poll = findViewById(R.id.imageView);
        retour = findViewById(R.id.imageButton);
        interrogation = findViewById(R.id.textView4);

        setFontTxt(nouveau);
        setFontTxt(interrogation);
        setFontBut(sondage);
        setFontBut(questionnaire);
        setFontBut(aide);

        retour.setOnClickListener(retouract);
        sondage.setOnClickListener(nouvsond);


    }

    View.OnClickListener retouract = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent retourmen = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
            startActivity(retourmen);
            finish();
        }
    };

    View.OnClickListener nouvsond = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent sond = new Intent(getApplicationContext(), NewSondageActivity.class);
            startActivity(sond);
            finish();
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
