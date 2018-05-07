package be.lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.MyDilemmeAdapter;
import be.lsinf1225.minipoll.MySondagesAdapter;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Dilemme;

public class MyDilemmeActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<Dilemme> dilemmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dilemme);
        listview = (ListView) findViewById(R.id.lv_my_dilemmes);
        dilemmes = Dilemme.getCreatorDilemmes();
        MyDilemmeAdapter adapter = new MyDilemmeAdapter(MyDilemmeActivity.this, dilemmes);
        listview.setAdapter(adapter);
        //TODO
    }
}
