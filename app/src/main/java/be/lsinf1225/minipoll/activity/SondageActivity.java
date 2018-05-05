package be.lsinf1225.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.Sondage;

public class SondageActivity extends AppCompatActivity {

    private Sondage sondage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);
        sondage = (Sondage) getIntent().getSerializableExtra("serializabled_sondage");
    }
}
