package be.lsinf1225.minipoll.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

public class QstnrOverActivity extends AppCompatActivity {

    private TextView resultat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_qstnr_over);

        resultat =findViewById(R.id.result);

        final int score = getIntent().getIntExtra("score_qstnr",0);
        final int nbrQst = getIntent().getIntExtra("nbr_qst",0);

        String txt = "Vous obtenu " + score + " reponses correctes pour un total de " + nbrQst + " questions";

        resultat.setText(txt);
    }
}
