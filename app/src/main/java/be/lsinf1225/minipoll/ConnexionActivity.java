package be.lsinf1225.minipoll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ConnexionActivity extends AppCompatActivity {

    ImageView poll;
    EditText et_password;
    EditText et_mail;
    Button btn_connexion;
    Button btn_inscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        poll = findViewById(R.id.poll);
        et_password = (EditText) findViewById(R.id.et_password);
        et_mail= (EditText) findViewById(R.id.et_mail);
        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_inscription = (Button) findViewById(R.id.btn_inscription);



        btn_connexion.setOnClickListener(co);

        btn_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    View.OnClickListener co = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent menu = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
            startActivity(menu);
            finish();
        }
    };

}
