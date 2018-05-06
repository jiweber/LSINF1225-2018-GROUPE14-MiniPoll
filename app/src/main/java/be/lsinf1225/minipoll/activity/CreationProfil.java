package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

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
    String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_profil);
        Intent prof = getIntent();
        Mdp = prof.getStringExtra("Mdp");
        Mail = prof.getStringExtra("Mail");
        PP = findViewById(R.id.pp_cre_pro);
        PP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, 1);
            }
        });
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


    View.OnClickListener crea;

    {
        crea = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = et_name.getText().toString();
                String Prename = et_prename.getText().toString();
                if (Name.equals("") | Prename.equals("") | PP == null) {
                    MiniPoll.notifyShort(R.string.choisirdonnees);
                } else {

                    String SQL = "INSERT INTO Utilisateur (Mail, Nom, Prénom, \"Mot de passe\", Photo) VALUES (?,?,?,?,?);";
                    MySQLiteHelper.get().getWritableDatabase().execSQL(SQL, new Object[]{Mail, Name, Prename, Mdp, imagepath});
                    MiniPoll.notifyLong(R.string.Profilcree);
                    Intent menu = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                    MiniPoll.setConnected_user(new User(Mail,Mdp,Prename,Name, imagepath));
                    startActivity(menu);
                    ConnexionActivity.connexion.finish();
                    Inscription.inscription.finish();
                    finish();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri uri = data.getData();
        imagepath = String.valueOf(uri);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            PP.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
