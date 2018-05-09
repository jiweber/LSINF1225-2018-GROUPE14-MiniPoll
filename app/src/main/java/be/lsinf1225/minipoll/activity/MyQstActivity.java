package be.lsinf1225.minipoll.activity;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Question;

public class MyQstActivity extends AppCompatActivity {

    private ListView qsts;

    private ArrayList<Question> BigList = Question.getSQLQuestion();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id_qstnr = getIntent().getIntExtra("id_qstnr",0);

        setContentView(R.layout.activity_myqst);

        qsts = findViewById(R.id.listQst);

        // parcourir BigList pour ne garder que les questions relatives à l'item
        // choisi par le user

        ArrayList<Question> SmallList = new ArrayList<Question>();
        int j = 0;

        for (int i=0; i < BigList.size(); i++){

            Log.i("Debug on question list",BigList.get(i).toString());

            if(BigList.get(i).getQstnrID()==id_qstnr){
                SmallList.add(BigList.get(i));
                j++;
            }
        }

        List<String> listTitles=Question.getTitles(SmallList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listTitles);

        qsts.setAdapter(adapter);

    }
}
