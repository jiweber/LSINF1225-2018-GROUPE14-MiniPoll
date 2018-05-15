package be.lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import be.lsinf1225.minipoll.BitmapUtil;
import be.lsinf1225.minipoll.DemandeAmisAdapter;
import be.lsinf1225.minipoll.ListeAmisAdapter;
import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class ListeAmisActivity extends AppCompatActivity{

    ArrayList<User> amis;
    ListeAmisAdapter recyclerAmisAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_amis);
        Log.i("DEBUG_J", "listamisactivity");

        amis = listeAmis();

        Log.i("DEBUG_J", "listamisactivity" + amis.size());



        recyclerView = (RecyclerView) findViewById(R.id.recycler_liste_amis);
        recyclerAmisAdapter = new ListeAmisAdapter(this, amis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAmisAdapter);
    }

    public ArrayList<User> listeAmis(){
        ArrayList<User> pas_amis = new ArrayList<>();
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "select * " +
        "from Utilisateur U, Relation R " +
        "where  R.Statut=\"Ami\" and( (U.Mail=R.Utilisateur1 and R.Utilisateur2=?) or  (U.Mail=R.Utilisateur2 and R.Utilisateur1= ? )) ";
        Cursor cursorRel = db.rawQuery(sql,new String[]{mail, mail }) ;
        cursorRel.moveToFirst();
        while(!cursorRel.isAfterLast()){
            Bitmap bm = null;
            if(cursorRel.getBlob(4)!=null) {
                byte[] byteArray = cursorRel.getBlob(4);
                bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            pas_amis.add(new User(cursorRel.getString(0), cursorRel.getString(1),cursorRel.getString(2),cursorRel.getString(3), bm ));
            cursorRel.moveToNext();
        }
        cursorRel.close();
        return pas_amis;

    }
}
