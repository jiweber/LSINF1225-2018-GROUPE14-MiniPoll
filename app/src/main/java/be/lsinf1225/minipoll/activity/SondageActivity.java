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
    private TextView tv_amis;
    private TextView tv_titre;
    private Sondage sondage;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);
        int id = getIntent().getIntExtra("sondage_id", 0);
        sondage = Sondage.getSondage(id);

        tv_titre = (TextView) findViewById(R.id.tv_sondage_titre);
        tv_titre.setText(sondage.getTitle());

        tv_amis = findViewById(R.id.tv_sondage_amis);
        String[] remFriends = sondage.getRemainingParticipants();
        String message = "Personnes n'ayant pas encore répondu : \n";
        message += remFriends[0];
        for(int i=1; i<remFriends.length; i++){
            message += ", " + remFriends[i];
        }
        tv_amis.setText(message);

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
                    String selected = (String) parent.getItemAtPosition(position);
                    sondage.answerSondage(MiniPoll.getConnected_user().getMail(), selected);
                }
            }
        });
    }
    View.OnClickListener envoyer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Intent pannel = new Intent(getApplicationContext(), SondagePannelActiviy.class);
        }
    };
}
