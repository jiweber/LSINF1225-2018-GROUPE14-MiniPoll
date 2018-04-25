package be.lsinf1225.minipoll;

//import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
//import android.widget.TextView;

public class MenuPrincipalActivity extends AppCompatActivity {
    Button btn_new;
    Button btn_sond;
    Button btn_quest;
    Button btn_aide;
    QuickContactBadge amis;
    QuickContactBadge profil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btn_new = findViewById(R.id.button4);
        btn_quest = findViewById(R.id.button2);
        btn_sond = findViewById(R.id.button5);
        btn_aide = findViewById(R.id.button3);
        amis = findViewById(R.id.quickContactBadge2);
        profil = findViewById(R.id.quickContactBadge);

        setFontBut(btn_aide, "GeosansLight.ttf");
        setFontBut(btn_new, "GeosansLight.ttf");
        setFontBut(btn_sond, "GeosansLight.ttf");
        setFontBut(btn_quest, "GeosansLight.ttf");

    }
    public void setFontTxt(TextView textView, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                textView.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }
    public void setFontBut(Button button, String fontName) {
        if(fontName != null){
            try {
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                button.setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }
}
