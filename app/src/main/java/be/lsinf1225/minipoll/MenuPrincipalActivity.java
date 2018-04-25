package be.lsinf1225.minipoll;

//import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
//import android.widget.TextView;

public class MenuPrincipalActivity extends AppCompatActivity {
    Button btn_new;
    Button btn_sond;
    Button btn_quest;
    Button btn_aide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_new = findViewById(R.id.button4);
        btn_quest = findViewById(R.id.button2);
        btn_sond = findViewById(R.id.button5);
        btn_aide = findViewById(R.id.button3);
    }
}
