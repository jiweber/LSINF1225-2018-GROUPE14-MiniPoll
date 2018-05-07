package be.lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

public class DemandesAmisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes_amis);

        ListView list = (ListView) findViewById(R.id.List_View_Demandes);
        List<String> listeDesAmis = ListeAmis();

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_demandes_amis, listeDesAmis);
    }



    public List<String> ListeAmis(){


        List<String> liste_de_String = new ArrayList<>();
        String user = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String SQL = "select Ultilisateur1, Utilisateur2, Statut FROM Relation WHERE Utilisateur1 = " + user + "or Utilisateur2 = " + user;
        Cursor cursor = db.rawQuery(SQL,new String[]{"Statut"});
        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            if (cursor.getString(2) == "En attente") {
                if (cursor.getString(0) == user) {
                    String PrenomNom = PrenomNomFromMail(cursor.getString(1));
                    liste_de_String.add(PrenomNom);
                } else if (cursor.getString(1) == user) {
                    String PrenomNom = PrenomNomFromMail(cursor.getString(0));
                    liste_de_String.add(PrenomNom);
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return liste_de_String;

    }

    public String PrenomNomFromMail(String mail){
        String SQL = "select Prénom, Nom FROM Utilisateur WHERE Mail = "+ mail;
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL,null);
        return cursor.getString(1)+" "+cursor.getString(2);
    }

}
