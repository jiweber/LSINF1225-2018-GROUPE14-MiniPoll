package be.lsinf1225.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

public class MenuPrincipalActivity extends AppCompatActivity {
    Button btn_new;
    Button btn_sond;
    Button btn_quest;
    Button btn_aide;
    ImageButton btn_friends;
    ImageButton btn_profil;
    public static Activity menu_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btn_new = findViewById(R.id.button4);
        btn_quest = findViewById(R.id.button2);
        btn_sond = findViewById(R.id.button5);
        btn_aide = findViewById(R.id.button3);
        btn_friends = findViewById(R.id.amis);
        btn_profil = findViewById(R.id.profil);

        setFontBut(btn_aide);
        setFontBut(btn_new);
        setFontBut(btn_sond);
        setFontBut(btn_quest);

        btn_profil.setOnClickListener(btn_profil_ls);
        btn_friends.setOnClickListener(btn_friends_ls);
        btn_aide.setOnClickListener(btn_aide_ls);
        btn_sond.setOnClickListener(btn_sond_ls);
        btn_quest.setOnClickListener(btn_quest_ls);
        btn_new.setOnClickListener(btn_new_ls);

        menu_activity = this;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out: {
                MiniPoll.setConnected_user(null);
                MenuPrincipalActivity.menu_activity.finish();
                Intent log = new Intent(getApplicationContext(),ConnexionActivity.class);
                startActivity(log);
                finish();                break;
            }
            case R.id.action_upgrade_databas: {
                MySQLiteHelper.upgradeDatabase(this);
                break;
            }
            case R.id.action_remove_relationships:{
                SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
                String deleteSql = "DROP TABLE IF EXISTS Relation";
                db.execSQL(deleteSql);
                String createSql = "CREATE TABLE Relation (" +
                        "Utilisateur1 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE MATCH FULL, " +
                        "Utilisateur2 TEXT NOT NULL REFERENCES Utilisateur (Mail) ON DELETE CASCADE ON UPDATE CASCADE, " +
                        "Statut TEXT NOT NULL DEFAULT 'En_attente' CHECK (statut IN ('Ami', 'En_attente', 'Rejet')), " +
                        "PRIMARY KEY (Utilisateur1, Utilisateur2), " +
                        "FOREIGN KEY (Utilisateur2) REFERENCES Utilisateur (Mail), " +
                        "FOREIGN KEY (Utilisateur1) REFERENCES Utilisateur (Mail)); " ;
                db.execSQL(createSql);
                break;
            }
        }
        return false;
    }

    View.OnClickListener btn_profil_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton profil");
            Intent acti_mod_pro = new Intent(getApplicationContext(), Profil.class);
            startActivity(acti_mod_pro);
        }
    };

    View.OnClickListener btn_new_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton nouveau");
            Intent acti_new = new Intent(getApplicationContext(), NouveauActivity.class);
            startActivity(acti_new);
        }
    };

    View.OnClickListener btn_quest_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton questionnaire");
            Intent questnr_view = new Intent(getApplicationContext(),MyQuestnrActivity.class);
            startActivity(questnr_view);
        }
    };

    View.OnClickListener btn_sond_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton sondage");
            Intent acti_new = new Intent(getApplicationContext(), MySondagesActivity.class);
            startActivity(acti_new);
        }
    };
    View.OnClickListener btn_friends_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton amis");
            Intent amis = new Intent(getApplicationContext(),GestionAmis.class);
            Log.i("FLAG", "Passé");
            startActivity(amis);
            Log.i("FLAG", "Présent");
        }
    };

    View.OnClickListener btn_aide_ls = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("DEBUG","Bouton aide");
            Intent dilemmes = new Intent(getApplicationContext(), MyDilemmeActivity.class);
            startActivity(dilemmes);
        }
    };

    public void setFontBut(Button button) {
        try {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf");
            button.setTypeface(typeface);
        } catch (Exception e) {
            Log.e("FONT", button + " not found", e);
        }
    }
}
