package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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

public class Profil extends AppCompatActivity {

    TextView tv_mod_title;
    TextView tv_mod_pic;
    TextView tv_mod_name;
    TextView tv_mod_prename;
    TextView tv_mod_mail;
    TextView tv_mod_pass;
    EditText et_mod_name;
    EditText et_mod_prename;
    EditText et_mod_mail;
    EditText et_mod_pass;
    ImageView mod_pic;
    Button mod_validate;
    Button mod_logout;
    String imagepath;
    Boolean modif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        tv_mod_title  =findViewById(R.id.title_mod_pro);
        tv_mod_pic  =findViewById(R.id.tv_mod_pic);
        tv_mod_name  =findViewById(R.id.tv_mod_name);
        tv_mod_prename  =findViewById(R.id.tv_mod_prename);
        tv_mod_mail  =findViewById(R.id.tv_mod_mail);
        tv_mod_pass  =findViewById(R.id.tv_mod_pass);
        et_mod_name  =findViewById(R.id.et_mod_name);
        et_mod_prename  =findViewById(R.id.et_mod_prename);
        et_mod_mail  =findViewById(R.id.et_mod_mail);
        et_mod_pass  =findViewById(R.id.et_mod_pass);
        mod_pic  =findViewById(R.id.pic);
        mod_validate  =findViewById(R.id.mod_validate);
        mod_logout  =findViewById(R.id.mod_logout);
        imagepath = MiniPoll.getConnected_user().getImagePath();
        modif = false;

        setFontTxt(tv_mod_title);
        setFontTxt(tv_mod_pic);
        setFontTxt(tv_mod_name);
        setFontTxt(tv_mod_prename);
        setFontTxt(tv_mod_mail);
        setFontTxt(tv_mod_pass);
        setFontEdTxt(et_mod_name);
        setFontEdTxt(et_mod_prename);
        setFontEdTxt(et_mod_mail);
        setFontEdTxt(et_mod_pass);
        setFontBut(mod_validate);
        setFontBut(mod_logout);

        et_mod_name.setText(MiniPoll.getConnected_user().getNom());
        et_mod_prename.setText(MiniPoll.getConnected_user().getPrenom());
        et_mod_mail.setText(MiniPoll.getConnected_user().getMail());
        try{
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(MiniPoll.getConnected_user().getImagePath()));
        mod_pic.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mod_logout.setOnClickListener(logout);
        mod_validate.setOnClickListener(vali);
        mod_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MiniPoll.setConnected_user(null);
            MenuPrincipalActivity.menu_activity.finish();
            Intent log = new Intent(getApplicationContext(),ConnexionActivity.class);
            startActivity(log);
            finish();
        }
    };

    View.OnClickListener vali = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newMail = et_mod_mail.getText().toString();
            String newName = et_mod_name.getText().toString();
            String newPrename = et_mod_prename.getText().toString();
            String newPassword = et_mod_pass.getText().toString();

            if(newMail.equals(""))
            {
                MiniPoll.notifyShort(R.string.mailentrevide);
            }
            else if (newName.equals(""))
            {
                MiniPoll.notifyShort(R.string.nomentrevide);
            }
            else if (newPrename.equals(""))
            {
                MiniPoll.notifyShort(R.string.prenomentrevide);
            }
            else if (newMail.equals(MiniPoll.getConnected_user().getMail())&&newName.equals(MiniPoll.getConnected_user().getNom())&&newPrename.equals(MiniPoll.getConnected_user().getPrenom())&&newPassword.equals("")&&!modif)
            {
                Intent menu = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                startActivity(menu);
                finish();
            }
            else
            {
                SQLiteDatabase dbr = MySQLiteHelper.get().getReadableDatabase();
                Boolean find = false;
                String SQLR = "SELECT Mail FROM Utilisateur";
                Cursor c = dbr.rawQuery(SQLR, null);
                c.moveToFirst();
                if(newMail.equals(MiniPoll.getConnected_user().getMail()))
                {
                    find=true;
                }
                while((!c.isAfterLast())&&(!find))
                {
                    if(c.getString(0).equals(newMail))
                    {
                        find = true;
                    }
                    c.moveToNext();
                }
                c.close();
                if(newMail.equals(MiniPoll.getConnected_user().getMail()))
                {
                    find=false;
                }
                if(find)
                    {
                        MiniPoll.notifyShort(R.string.maildejautilise);
                    }
                    else {
                        if(newPassword.equals("")) {
                            //TODO : Lancer la confirmation par le mot de passe
                            String SQLW = "UPDATE Utilisateur SET Mail = ?, Nom = ?, Prénom = ?, Photo = ? WHERE Mail = ?;";
                            MySQLiteHelper.get().getWritableDatabase().execSQL(SQLW,new Object[]{newMail,newName,newPrename,imagepath,MiniPoll.getConnected_user().getMail()});
                            MiniPoll.setConnected_user(new User(newMail,MiniPoll.getConnected_user().getMdp(),newPrename,newName,imagepath));
                        }
                        else
                        {
                            //TODO : Lancer la confirmation par l'ancien mot de passe
                            String SQLW = "UPDATE Utilisateur SET Mail = ?, Nom = ?, Prénom = ?, \"Mot de passe\" = ?, Photo = ? WHERE Mail = ?;";
                            MySQLiteHelper.get().getWritableDatabase().execSQL(SQLW,new Object[]{newMail,newName,newPrename,newPassword,imagepath,MiniPoll.getConnected_user().getMail()});
                            MiniPoll.setConnected_user(new User(newMail,newPassword,newPrename,newName,imagepath));
                        }
                    Intent menu = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
                    startActivity(menu);
                    MiniPoll.notifyLong(R.string.modireussie);
                    finish();
                    }
                }

            }




        };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri uri = data.getData();
        imagepath = String.valueOf(uri);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            mod_pic.setImageBitmap(bitmap);
            modif = true;
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
