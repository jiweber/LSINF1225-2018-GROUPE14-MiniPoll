package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;
import be.lsinf1225.minipoll.MySondagesAdapter;
import be.lsinf1225.minipoll.model.User;

public class MySondagesActivity extends AppCompatActivity {

    private ListView listview;
    private ArrayList<Sondage> sondages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sondages);
        listview = (ListView) findViewById(R.id.lv_my_sondages);
        sondages = Sondage.getSondages();
        MySondagesAdapter adapter = new MySondagesAdapter(MySondagesActivity.this, sondages);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id){
                Sondage item = (Sondage) adapter.getItemAtPosition(position);
                int status = item.getStatus();
                if(status == 0 || status == 1) {
                    Intent intent = new Intent(MySondagesActivity.this, SondageActivity.class);
                    intent.putExtra("sondage_id", item.getId());
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MySondagesActivity.this, SondagePannelActivity.class);
                    intent.putExtra("sondage_id", item.getId());
                    startActivity(intent);
                }
            }
        });
    }
}
