package be.lsinf1225.minipoll.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Question;
import be.lsinf1225.minipoll.model.Questnr;

public class MyQstActivity extends AppCompatActivity {

    private ListView qsts;

    private ArrayList<Question> BigList = Question.getSQLQuestion();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int id_qstnr = getIntent().getIntExtra("id_qstnr",0);
        Log.i("QUESTIONR_ID: ",String.valueOf(id_qstnr));

        setContentView(R.layout.activity_myqst);

        qsts = findViewById(R.id.listQst);

        List<String> listTitles=Question.getTitles(Question.getSmallQst(BigList,id_qstnr));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listTitles);

        qsts.setAdapter(adapter);

        //retrieving Statut and num_qst for the questionnaire

        //final int Statut = Questnr.getSmallQstnr(Questnr.getSQLQuestnr(),id_qstnr).get(0).getStatutQstnr();
        //final int NbrQst = Questnr.getSmallQstnr(Questnr.getSQLQuestnr(),id_qstnr).get(0).getNbrQuestions();

        qsts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String itemValue = (String) qsts.getItemAtPosition(i);
                Log.i("Click on item: ",itemValue);
                int itemPosition = (int) qsts.getItemIdAtPosition(i);
                Log.i("QST_ID: ", String.valueOf(itemPosition));
                int valueToPass = Question.getSmallQst(BigList,id_qstnr).get(itemPosition).getQstID();
                Log.i("Value: ", String.valueOf(valueToPass));

                //retrieving Statut and num_qst for the questionnaire

                int Statut = Questnr.getSmallQstnr(Questnr.getSQLQuestnr(),id_qstnr).get(0).getStatutQstnr();
                int Numquestion = Question.getSmallQst(BigList,id_qstnr).get(itemPosition).getQstNum();

                if(Numquestion <= Statut){

                    Intent intent = new Intent(MyQstActivity.this,ReponseOverActivity.class);
                    startActivity(intent);

                } else{

                    Intent intent = new Intent(MyQstActivity.this,MyReponseActivity.class);

                    intent.putExtra("id_qst",Question.getSmallQst(BigList,id_qstnr).get(itemPosition).getQstID());
                    intent.putExtra("title_qst",Question.getSmallQst(BigList,id_qstnr).get(itemPosition).getQstTitle());
                    //intent.putExtra("id_qstnr",id_qstnr);

                    startActivity(intent);
                }

            }
        });

    }
}
