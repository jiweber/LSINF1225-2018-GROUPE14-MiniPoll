package be.lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.Sondage;
import be.lsinf1225.minipoll.SondageAdapter;

public class MySondagesActivity extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sondages);

        listview = (ListView) findViewById(R.id.listView);
        String user = "user";
        ArrayList<Sondage> sondages = getSondages(user);
        SondageAdapter adapter = new SondageAdapter(MySondagesActivity.this, sondages);
        listview.setAdapter(adapter);
    }

    private ArrayList<Sondage> getSondages(String user){
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        sondages.add(new Sondage("Vacnaces été", "Louis", new String[]{"Kim","Mens"}, new String[]{"Barcelone","Bamako","LLN le sang"}));
        sondages.add(new Sondage("Cinéma entre coupains", "Esteban", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Grosse teuf chez qui ?", "Patrick", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Top 1000 meilleurs films", "Sebastien", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("C'est quand même bo les sondages", "Dieu", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Et les ListView surtout", "User0000", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        return sondages;
    }
}
