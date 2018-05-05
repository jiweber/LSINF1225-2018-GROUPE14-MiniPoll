package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Questnr;


public class MyQuestnrActivity extends AppCompatActivity {

    private ListView qstnr;

    List<String> u_qstnr= Questnr.getTtitles();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myquestnr);

        qstnr=findViewById(R.id.listQstrn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);

        qstnr.setAdapter(adapter);

    }
}
