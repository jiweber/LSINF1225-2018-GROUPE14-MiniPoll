package be.lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.BitmapUtil;
import be.lsinf1225.minipoll.DemandeAmisAdapter;
import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class DemandesAmisActivity extends AppCompatActivity {

    ArrayList<User> amis;
    DemandeAmisAdapter demandeAmisAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_amis);

        amis = demandeAmis();



        recyclerView = (RecyclerView) findViewById(R.id.recycler_liste_amis);
        demandeAmisAdapter = new DemandeAmisAdapter(this, amis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(demandeAmisAdapter);
    }


    public ArrayList<User> demandeAmis(){
        ArrayList<User> pas_amis = new ArrayList<>();
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "select * " +
                "from Utilisateur U, Relation R " +
                "where  R.Statut=\"En_attente\" and( (U.Mail=R.Utilisateur1 and R.Utilisateur2=?) or  (U.Mail=R.Utilisateur2 and R.Utilisateur1= ? )) ";
        Cursor cursorRel = db.rawQuery(sql,new String[]{mail, mail }) ;
        cursorRel.moveToFirst();
        while(!cursorRel.isAfterLast()){
            pas_amis.add(new User(cursorRel.getString(0), cursorRel.getString(1),cursorRel.getString(2),cursorRel.getString(3), BitmapUtil.getBitmap(cursorRel.getBlob(4)) ));
            cursorRel.moveToNext();
        }
        cursorRel.close();
        return pas_amis;
    }

    public static void accepterAmi(User user1, User user2){                     //User1 est le demandeur
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "update Relation " +
                        "set Statut=\"Ami\" " +
                        "where Utilisateur1=? and Utilisateur2=?;";
        db.execSQL(sql, new Object[]{user1.getMail(), user2.getMail()} );
    }


}
