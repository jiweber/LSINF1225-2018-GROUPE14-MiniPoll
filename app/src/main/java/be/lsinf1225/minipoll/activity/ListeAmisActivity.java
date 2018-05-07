package be.lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;


public class ListeAmisActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG : LIST AMIS", " Création de la liste d'amis");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_amis);
        ListView list = (ListView) findViewById(R.id.List_View_Liste_Amis);
        List<String> listeDesAmis = ListeAmis();
        EditText theFilter = (EditText) findViewById(R.id.et_Search_Liste_Amis);

        adapter = new ArrayAdapter(this, R.layout.activity_liste_amis, listeDesAmis);
        list.setAdapter(adapter);

        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ListeAmisActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    public List<String> ListeAmis(){


        List<String> liste_de_String = new ArrayList<>();
        String user = MiniPoll.getConnected_user().getMail();
        Log.d("DEBUG : LIST AMIS", " Recherche d'amis -2 ");
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Log.d("DEBUG : LIST AMIS", " Recherche d'amis -1"+user);
        String SQL = "select Ultilisateur1, Utilisateur2, Statut FROM Relation WHERE Utilisateur1 = " + user + "or Utilisateur2 = " + user;
        Log.d("DEBUG : LIST AMIS", " Recherche d'amis 0");
        Cursor cursor = db.rawQuery(SQL,new String[]{"Statut"});
        Log.d("DEBUG : LIST AMIS", " Recherche d'amis 0.5");
        cursor.moveToFirst();
        Log.d("DEBUG : LIST AMIS", " Recherche d'amis 1");
        while(! cursor.isAfterLast()) {
            if (cursor.getString(2) == "Ami") {
                Log.d("DEBUG : LIST AMIS", " Recherche d'amis 2");
                if (cursor.getString(0) == user) {
                    Log.d("DEBUG : LIST AMIS", " Recherche d'amis 3");
                    String PrenomNom = PrenomNomFromMail(cursor.getString(1));
                    liste_de_String.add(PrenomNom);
                } else if (cursor.getString(1) == user) {
                    Log.d("DEBUG : LIST AMIS", " Recherche d'amis 4");
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
        Log.d("DEBUG : LIST AMIS", " Construction des amis ");
        String SQL = "select Prénom, Nom FROM Utilisateur WHERE Mail = "+ mail;
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL,null);
        return cursor.getString(1)+" "+cursor.getString(2);
    }

}
