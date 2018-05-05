package be.lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;

public class SondageActivity extends AppCompatActivity {

    private Sondage sondage;
    private ListView listview;
    private TextView tv_amis;
    private TextView tv_titre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);
        sondage = (Sondage) getIntent().getSerializableExtra("serializabled_sondage");

        tv_titre = (TextView) findViewById(R.id.tv_sondage_titre);
        tv_titre.setText(sondage.getTitle());

        tv_amis = findViewById(R.id.tv_sondage_amis);
        String[] remFriends = new String[]{"User1","User2"};//sondage.getRemainingFriends(); //TODO
        String message = "Personnes n'ayant pas encore répondu : \n";
        message += remFriends[0];
        for(int i=1; i<remFriends.length; i++){
            message += ", " + remFriends[i];
        }
        tv_amis.setText(message);

        listview = (ListView) findViewById(R.id.lv_sondage);
        String[] propositions = sondage.getEnonces();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SondageActivity.this, android.R.layout.simple_list_item_1, propositions);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);
                String selected = (String) parent.getItemAtPosition(position);
                //sondage.answer(MiniPoll.getUserMail(), selected);
            }
        });
    }
}
