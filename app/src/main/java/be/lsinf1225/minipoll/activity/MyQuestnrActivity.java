package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;



public class MyQuestnrActivity extends AppCompatActivity {

    private ImageButton back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_myquestnr);

        back=findViewById(R.id.back_to_main);
        back.setOnClickListener(back1);

    }

    View.OnClickListener back1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton back");
            Intent acti_new = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
            startActivity(acti_new);
            finish();
        }
    };
}
