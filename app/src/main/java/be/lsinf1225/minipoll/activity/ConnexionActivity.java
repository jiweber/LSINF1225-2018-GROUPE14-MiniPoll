package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

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
        et_password = findViewById(R.id.et_password);
        et_mail= findViewById(R.id.et_mail);
        btn_connexion = findViewById(R.id.btn_connexion);
        btn_inscription = findViewById(R.id.btn_inscription);



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
            Log.i("TEST", "Debut");
            String mail = et_mail.getText().toString();
            String pass = et_password.getText().toString();
            Log.i("TEST", "Milieu");
            SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
            String SQL = "select \"Mot de passe\" FROM Utilisateur WHERE Mail = ?";
            Log.i("TEST", "Fin");
            Cursor c = db.rawQuery(SQL,new String[]{mail});
            c.moveToFirst();
            if(c.isAfterLast())
            {
                MiniPoll.notifyShort(R.string.mailoupassincorr);
                et_password.setText("");
                c.close();
            }
            else if((c.getString(0)).equals(pass))
            {
                c.close();
                MiniPoll.setUserMail(mail);
                Intent connected = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                startActivity(connected);
                finish();
            }
            else
            {
                MiniPoll.notifyShort(R.string.mailoupassincorr);
                et_password.setText("");
                c.close();
            }



        }
    };

}
