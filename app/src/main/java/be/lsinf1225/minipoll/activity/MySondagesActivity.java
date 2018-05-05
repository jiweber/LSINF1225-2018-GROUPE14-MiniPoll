package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;
import be.lsinf1225.minipoll.MySondagesAdapter;

public class MySondagesActivity extends AppCompatActivity {

    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sondages);
        listview = (ListView) findViewById(R.id.lv_my_sondages);
        MySondagesAdapter adapter = new MySondagesAdapter(MySondagesActivity.this, Sondage.getCreatorSondages());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
                Sondage item = (Sondage) adapter.getItemAtPosition(position);
                Intent intent = new Intent(MySondagesActivity.this,SondageActivity.class);
                intent.putExtra("serialize_sondage", item);
                startActivity(intent);
            }
        });
    }

    /*
    private ArrayList<Sondage> getSondages(String user){
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        sondages.add(new Sondage("Vacances été", "Louis", new String[]{"Kim","Mens"}, new String[]{"Barcelone","Bamako","LLN le sang"}));
        sondages.add(new Sondage("Cinéma entre coupains", "Esteban", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Grosse teuf chez qui ?", "Patrick", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Top 1000 meilleurs films", "Sebastien", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("C'est quand même bo les sondages", "Dieu", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        sondages.add(new Sondage("Et les ListView surtout", "User0000", new String[]{"Patrick","Sebastien"}, new String[]{"Retour vers le futur","LSINF1225 le film","Osef du ciné on casse ta maison"}));
        return sondages;
    }
    */
}
