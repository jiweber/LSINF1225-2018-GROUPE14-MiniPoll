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

        setFontTxt(nouveau, "GeosansLight.ttf");

        retour.setOnClickListener(retouract);


    }

    View.OnClickListener retouract = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent retourmen = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
            startActivity(retourmen);
            finish();
        }
    };
    public void setFontTxt(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }
}
