package be.lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;

public class SondageActivity extends AppCompatActivity {

    private Sondage sondage;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);
        sondage = (Sondage) getIntent().getSerializableExtra("serializabled_sondage");

        TextView textView = findViewById(R.id.lv_sondage);
        String[] remFriends = sondage.getRemainingFriends(); //TODO
        String message = "Personnes n'ayant pas encore répondu : \n";
        message += remFriends[0];
        for(int i=1; i<remFriends.length; i++){
            message += ", " + remFriends[i];
        }
        textView.setText(message);

        listview = (ListView) findViewById(R.id.lv_sondage);
    }
}
