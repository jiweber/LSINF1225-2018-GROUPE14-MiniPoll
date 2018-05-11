package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

public class NewQuestionnaireActivity extends AppCompatActivity {

    TextView title;
    TextView tv_titrequest;
    TextView tv_nquest;
    EditText et_titrequest;
    EditText et_nquest;
    Button button;
    Activity nq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_questionnaire);

        title = findViewById(R.id.nq_title);
        tv_titrequest = findViewById(R.id.nq_tv_titre);
        tv_nquest = findViewById(R.id.nq_tv_nquest);
        et_nquest = findViewById(R.id.nq_et_nquest);
        et_titrequest = findViewById(R.id.nq_et_titre);
        button = findViewById(R.id.nq_validate);
        nq = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titrequest = et_titrequest.getText().toString();
                String nquest = et_nquest.getText().toString();
                if (titrequest.equals("")||nquest.equals(""))
                {
                    MiniPoll.notifyShort(R.string.veuillezremplir);
                }
                else
                {
                    String SQL = "INSERT INTO Questionnaire (Titre, Auteur) VALUES ('"+titrequest+"', '"+MiniPoll.getConnected_user().getMail()+"');";
                    MySQLiteHelper.get().getWritableDatabase().execSQL(SQL);
                    Intent prof = new Intent(getApplicationContext(),SuiteNouveauQuestionnaire.class);
                    prof.putExtra("Titre",titrequest);
                    prof.putExtra("Nquest",nquest);
                    startActivity(prof);
                }
            }
        });

    }
}
