package be.lsinf1225.minipoll.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.ChoixAmis;

public class NewSondageActivity extends AppCompatActivity {

    List<ChoixAmis> liste;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sondage);
        liste = new ArrayList<>();
        count = 0;
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String SQL = "SELECT Utilisateur2 FROM Relation WHERE Utilisateur1 = '"+mail+"', Statut = 'Ami';";
        Cursor c = db.rawQuery(SQL,null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            count++;
            c.moveToNext();
        }
        c.close();
        for (int i=0; i<count;i++)
        {
            liste.add(new ChoixAmis(i));
        }

    }

}
