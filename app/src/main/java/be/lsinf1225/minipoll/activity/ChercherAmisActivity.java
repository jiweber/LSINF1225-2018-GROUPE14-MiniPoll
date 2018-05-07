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

public class ChercherAmisActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher_amis);
        ListView list = (ListView) findViewById(R.id.List_View_Chercher);
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
                (ChercherAmisActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public List<String> ListeAmis(){

        List<String> liste_des_relations = new ArrayList<>();
        final String user = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String SQLRel = "select Ultilisateur1, Utilisateur2, Statut FROM Relation WHERE Utilisateur1 = " + user + "or Utilisateur2 = " + user;
        Cursor cursorRel = db.rawQuery(SQLRel,new String[]{"Statut"});
        cursorRel.moveToFirst();

        while( cursorRel.isAfterLast()) {
            if (cursorRel.getString(0) == user) {
                String PrenomNom = PrenomNomFromMail(cursorRel.getString(1));
                liste_des_relations.add(PrenomNom);
            } else if (cursorRel.getString(1) == user) {
                String PrenomNom = PrenomNomFromMail(cursorRel.getString(0));
                liste_des_relations.add(PrenomNom);
            }
            cursorRel.moveToNext();
        }

        List<String> liste_All = new ArrayList<>();
        String SQLAll = "select Mail FROM Utilisateur";
        Cursor cursorAll = db.rawQuery(SQLAll,new String[]{"Mail"});
        cursorAll.moveToFirst();

        while( cursorAll.isAfterLast()) {
            if (cursorAll.getString(0) == user) {
                String PrenomNom = PrenomNomFromMail(cursorAll.getString(1));
                liste_All.add(PrenomNom);
            }
            else if (cursorAll.getString(1) == user) {
                String PrenomNom = PrenomNomFromMail(cursorAll.getString(0));
                liste_All.add(PrenomNom);
            }
            cursorAll.moveToNext();
        }
        cursorAll.close();

        liste_All.removeAll(liste_des_relations);
        ArrayList<String> User= new ArrayList<String>();
        User.add(user);
        liste_All.removeAll(User);

        return liste_All;
    }

    public String PrenomNomFromMail(String mail){
        Log.d("DEBUG : CHERCHE AMIS", " Construction des amis ");
        String SQL = "select Prénom, Nom FROM Utilisateur WHERE Mail = "+ mail;
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL,null);
        return cursor.getString(1)+" "+cursor.getString(2);
    }

}
