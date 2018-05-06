package be.lsinf1225.minipoll.activity;

import android.content.Intent;
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
import be.lsinf1225.minipoll.R;

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

        mod_logout.setOnClickListener(logout);
    }

    View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MiniPoll.setUserMail(null);
            MenuPrincipalActivity.menu_activity.finish();
            Intent log = new Intent(getApplicationContext(),ConnexionActivity.class);
            startActivity(log);
            finish();
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
