package be.lsinf1225.minipoll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnexionActivity extends AppCompatActivity {

    EditText et_password;
    EditText et_mail;
    Button btn_connexion;
    Button btn_inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        et_password = (EditText) findViewById(R.id.et_password);
        et_mail= (EditText) findViewById(R.id.et_mail);
        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_inscription = (Button) findViewById(R.id.btn_inscription);



        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

}
