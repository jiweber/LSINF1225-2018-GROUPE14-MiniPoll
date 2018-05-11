package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;

public class SondageActivity extends AppCompatActivity {

    private ListView listview;
    private TextView tv_titre;
    private Sondage sondage;
    private Button button;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);
        int id = getIntent().getIntExtra("sondage_id", 0);
        sondage = Sondage.getSondage(id);
        selected = null;

        tv_titre = (TextView) findViewById(R.id.tv_sondage_titre);
        tv_titre.setText(sondage.getTitle());

        button = (Button) findViewById(R.id.btn_envoyer);
        button.setOnClickListener(envoyer);

        listview = (ListView) findViewById(R.id.lv_sondage);
        String[] propositions = sondage.getEnonces();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SondageActivity.this, android.R.layout.simple_list_item_1, propositions);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                if(!sondage.isCreator(MiniPoll.getConnected_user().getMail())) {
                    listview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    listview.setSelector(android.R.color.holo_blue_light);
                    selected = (String) parent.getItemAtPosition(position);
                }
            }
        });
    }
    View.OnClickListener envoyer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(selected == null){
                MiniPoll.notifyShort(R.string.entrez_une_reponse);
            }
            else {
                sondage.answerSondage(MiniPoll.getConnected_user().getMail(), selected);
                Intent intent = new Intent(getApplicationContext(), SondagePannelActivity.class);
                intent.putExtra("sondage_pannel_id", sondage.getId());
                startActivity(intent);
            }
        }
    };
}
