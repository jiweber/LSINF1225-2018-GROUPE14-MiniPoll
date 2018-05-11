package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.adapter.ItemButtonAdapter;
import be.lsinf1225.minipoll.model.ItemButton;
import be.lsinf1225.minipoll.model.Question;

public class SuiteNouveauQuestionnaire extends AppCompatActivity {

    String titre;
    int nquest;
    TextView title;
    Button but;
    List<Uri> uriList;
    boolean passe[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suite_nouveau_questionnaire);
        Intent prof = getIntent();
        titre = prof.getStringExtra("Titre");
        nquest = Integer.parseInt(prof.getStringExtra("Nquest"));


        List<ItemButton> listeBut = new ArrayList<>();
        for(int i=0; i < nquest ;i++)
        {
            listeBut.add(new ItemButton(i));
        }
        but=findViewById(R.id.snq_validate);
        but.setOnClickListener(validate);
        title = findViewById(R.id.snq_title);
        uriList = new ArrayList<>();
        passe = new boolean[nquest];
        for (int i=0;i<nquest;i++)
        {
            passe[i]=false;
        }

        ListView liste = findViewById(R.id.listquest);
        liste.setAdapter(new ItemButtonAdapter(this,listeBut));
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent creaques = new Intent(getApplicationContext(),CreaQuestion.class);
                startActivityForResult(creaques,1); //TODO
                passe[i]=true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                uriList.add(data.getData());
                //TODO
            }
        }
    }

    View.OnClickListener validate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean mauvais = false;
            for (int i = 0; i<nquest; i++)
            {
                if(!passe[i]) {
                    i = nquest;
                    mauvais = true;
                }
            }
            if (mauvais)
            {
                MiniPoll.notifyShort(R.string.choisirquestions);
            }
            else
            {
                //TODO
            }
        }
    };
}
