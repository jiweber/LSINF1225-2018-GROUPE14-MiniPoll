package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

public class CreationProfil extends AppCompatActivity {

    String Mdp;
    String Mail;
    ImageView PP;
    TextView title;
    TextView tv_name;
    TextView tv_prename;
    EditText et_name;
    EditText et_prename;
    Button creation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profil);
        Intent prof = getIntent();
        Mdp = prof.getStringExtra("Mdp");
        Mail = prof.getStringExtra("Mail");
        PP = findViewById(R.id.pp_cre_pro);
        title = findViewById(R.id.title_cre_pro);
        setFontTxt(title);
        tv_name = findViewById(R.id.tv_name_cre_pro);
        setFontTxt(tv_name);
        tv_prename = findViewById(R.id.tv_prename_cre_pro);
        setFontTxt(tv_prename);
        et_name = findViewById(R.id.et_name_cre_pro);
        setFontEdTxt(et_name);
        et_prename = findViewById(R.id.et_prename_cre_pro);
        setFontEdTxt(et_prename);
        creation = findViewById(R.id.validate_cre_pro);
        setFontBut(creation);

        creation.setOnClickListener(crea);
    }


    View.OnClickListener crea = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String Name = et_name.getText().toString();
            String Prename = et_prename.getText().toString();
            String PathPP = "Jesaispascommentgereruneimage";            //TODO
            if(Name.equals("")|Prename.equals("")|PathPP.equals(""))
            {
                MiniPoll.notifyShort(R.string.choisirdonnees);
            }
            else
            {
                String SQL = "INSERT INTO Utilisateur (Mail, Nom, Prénom, \"Mot de passe\", Photo) VALUES (?,?,?,?,?);";
                MySQLiteHelper.get().getWritableDatabase().execSQL(SQL,new String[]{Mail, Name, Prename, Mdp, PathPP});
                MiniPoll.notifyLong(R.string.Profilcree);
                Intent menu = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                MiniPoll.setUserMail(Mail);
                startActivity(menu);
                finish();
            }
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
