package be.lsinf1225.minipoll.activity;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

public class ReponseOverActivity extends AppCompatActivity {

    private TextView IsDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rep_over);

        IsDone=findViewById(R.id.RepOver);

    }
}
