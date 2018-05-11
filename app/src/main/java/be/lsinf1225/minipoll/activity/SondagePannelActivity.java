package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySondagesAdapter;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.SondagePannelAdapter;
import be.lsinf1225.minipoll.model.Sondage;


public class SondagePannelActivity extends AppCompatActivity {

    private ListView listview;
    private TextView tv_amis;
    private TextView tv_titre;
    private Sondage sondage;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage_pannel);
        int id = getIntent().getIntExtra("sondage_pannel_id", 0);
        Log.i("test1", "id reçu : "+ id);
        sondage = Sondage.getSondage(id);

        tv_titre = (TextView) findViewById(R.id.tv_sondage_pannel_titre);
        tv_titre.setText(sondage.getTitle());

        tv_amis = findViewById(R.id.tv_sondage_pannel_amis);
        String[] remFriends = sondage.getRemainingParticipants();
        String message;
        if(remFriends.length == 0){
            message = "Tous les participants ont répondu.";
        }
        else {
            message = "Personnes n'ayant pas encore répondu : \n";
            message += remFriends[0];
            for (int i = 1; i < remFriends.length; i++) {
                message += ", " + remFriends[i];
            }
        }
        tv_amis.setText(message);

        button = (Button) findViewById(R.id.btn_cloturer);
        button.setOnClickListener(cloturer);


        listview = findViewById(R.id.lv_sondage_pannel);
        ArrayList<Sondage.Proposition> propositions = sondage.getPropositions();

        SondagePannelAdapter adapter = new SondagePannelAdapter(SondagePannelActivity.this, propositions);
        listview.setAdapter(adapter);
    }
    View.OnClickListener cloturer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sondage.cloture();
            MiniPoll.notifyShort(R.string.sondage_cloture);
            Intent mySondages = new Intent(getApplicationContext(), MySondagesActivity.class);
            startActivity(mySondages);
        }
    };
}
