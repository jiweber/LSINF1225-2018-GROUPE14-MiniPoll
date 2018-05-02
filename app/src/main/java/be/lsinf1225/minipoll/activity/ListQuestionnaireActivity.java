package be.lsinf1225.minipoll.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;


public class ListQuestionnaireActivity extends AppCompatActivity {

    TextView title;
    ListView list_questnr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        title=findViewById(R.id.title_questnr);
        list_questnr=findViewById(R.id.list_questnr);
    }
}
