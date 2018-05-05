package be.lsinf1225.minipoll.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.lsinf1225.minipoll.R;

public class Inscription extends AppCompatActivity {

    TextView title;
    TextView mail_2p;
    TextView mdp_2p;
    TextView remdp_2p;
    EditText mail_insc;
    EditText mdp_insc;
    EditText re_mdp_insc;
    Button insc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        title = findViewById(R.id.Creation);
        setFontTxt(title);
        mail_2p = findViewById(R.id.maildeuxpoints);
        setFontTxt(mail_2p);
        mdp_2p = findViewById(R.id.mdpdeuxpoints);
        setFontTxt(mdp_2p);
        remdp_2p = findViewById(R.id.remdp);
        setFontTxt(remdp_2p);
        mail_insc = findViewById(R.id.mail_insc);
        mdp_insc = findViewById(R.id.mdp_insc);
        re_mdp_insc = findViewById(R.id.mdp_re_insc);
        insc = findViewById(R.id.sinscrire);
        setFontBut(insc);


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
}
