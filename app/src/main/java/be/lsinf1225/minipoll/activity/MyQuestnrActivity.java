package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Questnr;


public class MyQuestnrActivity extends AppCompatActivity {

    private ListView qstnr;

    List<String> u_qstnr= Questnr.getTitles();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myquestnr);

        qstnr=findViewById(R.id.listQstrn);

        /*DEBUG*/
        if (Questnr.getSQLQuestnr().get(0)!=null){
            Log.i("test1",Questnr.getSQLQuestnr().get(0).toString());
        } else {Log.i("test1","missing");}

        if (Questnr.getTitles().get(0)!=null){
            Log.i("test2",Questnr.getTitles().get(0));
        } else {Log.i("test2","missing");}


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,u_qstnr);

        qstnr.setAdapter(adapter);

        qstnr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                String itemValue = (String) qstnr.getItemAtPosition(i);
                Log.i("Click on item: ", itemValue);
                int itemPosition = (int) qstnr.getItemIdAtPosition(i);

                Intent intent = new Intent(MyQuestnrActivity.this, MyQstActivity.class);
                intent.putExtra("id_qstnr",Questnr.getSQLQuestnr().get(itemPosition).getIDQstnr());
                startActivity(intent);
            }
        });

    }

}
