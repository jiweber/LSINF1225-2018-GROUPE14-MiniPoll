package be.lsinf1225.minipoll.activity;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import be.lsinf1225.minipoll.R;

public class MyQstActivity extends AppCompatActivity {

    private ListView qsts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myqst);

        qsts = findViewById(R.id.listQst);

    }
}
