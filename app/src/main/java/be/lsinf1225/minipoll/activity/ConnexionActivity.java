package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class ConnexionActivity extends AppCompatActivity {

    ImageView poll;
    TextView tv_mail;
    TextView tv_mdp;
    EditText et_password;
    EditText et_mail;
    Button btn_connexion;
    Button btn_inscription;
    public static Activity connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        poll = findViewById(R.id.poll);
        tv_mail = findViewById(R.id.tv_text_login);
        setFontTxt(tv_mail);
        tv_mdp = findViewById(R.id.tv_text_password);
        setFontTxt(tv_mdp);
        et_password = findViewById(R.id.et_password);
        setFontEdTxt(et_password);
        et_mail= findViewById(R.id.et_mail);
        setFontEdTxt(et_mail);
        btn_connexion = findViewById(R.id.btn_connexion);
        setFontBut(btn_connexion);
        btn_inscription = findViewById(R.id.btn_inscription);
        setFontBut(btn_inscription);


        btn_connexion.setOnClickListener(co);

        btn_inscription.setOnClickListener(insc);

        connexion = this;
    }

    View.OnClickListener co = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String mail = et_mail.getText().toString();
            String pass = et_password.getText().toString();
            SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
            String SQL = "select \"Mot de passe\", Nom, Prénom, Photo FROM Utilisateur WHERE Mail = ?";
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
                String name = c.getString(1);
                String prename = c.getString(2);
                String path = c.getString(3);
                c.close();
                MiniPoll.setConnected_user(new User(mail,pass,prename,name,path));
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


    View.OnClickListener insc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ins = new Intent(getApplicationContext(),Inscription.class);
            startActivity(ins);
        }
    };

    public void setFontTxt(TextView textView) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            textView.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", textView + " not found", e);
        }
    }

    public void setFontBut(Button button) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            button.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", button + " not found", e);
        }
    }

    public void setFontEdTxt(EditText editText) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            editText.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", editText + " not found", e);
        }
    }
}
