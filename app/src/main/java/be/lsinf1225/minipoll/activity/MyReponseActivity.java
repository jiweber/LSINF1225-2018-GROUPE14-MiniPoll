package be.lsinf1225.minipoll.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Reponse;

public class MyReponseActivity extends AppCompatActivity {

    private TextView enonce;
    private ListView ListeReponses;

    private ArrayList<Reponse> BigList = Reponse.getSQLReponse();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int idQst = getIntent().getIntExtra("id_qst",0);
        Log.i("QUESTION_ID: ",String.valueOf(idQst));

        setContentView(R.layout.activity_reponse);

        enonce =findViewById(R.id.enonceQst);
        ListeReponses=findViewById(R.id.listeReponses);

        List<String> ListeRep = Reponse.getTextes(Reponse.getSmallRep(BigList,idQst));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                ListeRep);

        ListeReponses.setAdapter(adapter);
    }
}
