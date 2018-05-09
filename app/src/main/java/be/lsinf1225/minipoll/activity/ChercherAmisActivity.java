package be.lsinf1225.minipoll.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.BitmapUtil;
import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class ChercherAmisActivity extends AppCompatActivity {

    Button btn_cancel;
    Button btn_accept;
    SwipeDeck cardStack;
    ArrayList<User> data;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher_amis);

        btn_cancel = (Button) findViewById(R.id.btn_tinder_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardLeft(500);
            }
        });
        btn_accept = (Button) findViewById(R.id.btn_tinder_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardRight(500);
            }
        });

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        data = listePasAmis();


        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(data, this);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
                demanderAmi(MiniPoll.getConnected_user(), data.get(position));
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
    };

    public ArrayList<User> listePasAmis(){
        ArrayList<User> pas_amis = new ArrayList<>();
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = " SELECT Mail, Nom, Prenom, Mot_de_passe, Photo "+
        "FROM Utilisateur "+
        "WHERE Mail is not ? "+
        "EXCEPT " +
        "SELECT U.Mail, U.Nom,U.Prenom, U.Mot_de_passe, U.Photo "+
        "FROM Utilisateur U, Relation R "+
        "WHERE  (U.Mail=R.Utilisateur1 and R.Utilisateur2=?) or  (U.Mail=R.Utilisateur2 and R.Utilisateur1=?)";
        Cursor cursorRel = db.rawQuery(sql,new String[]{mail, mail,mail });
        cursorRel.moveToFirst();
        Log.i("DEBUG_J", "pasamis_nombre" + String.valueOf(cursorRel.getCount()));
        while(!cursorRel.isAfterLast()){
            pas_amis.add(new User(cursorRel.getString(0), cursorRel.getString(1),cursorRel.getString(2),cursorRel.getString(3), BitmapUtil.getBitmap(cursorRel.getBlob(4)) ));
            cursorRel.moveToNext();
        }
        cursorRel.close();
        return pas_amis;

    }



    public void demanderAmi(User user1, User user2){ //User1 demande
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "INSERT INTO Relation (Utilisateur1,Utilisateur2,Statut) "+
                "VALUES(?, ?, \"En_attente\")";
        db.execSQL(sql, new Object[]{user1.getMail(), user2.getMail()} );
    }


}
