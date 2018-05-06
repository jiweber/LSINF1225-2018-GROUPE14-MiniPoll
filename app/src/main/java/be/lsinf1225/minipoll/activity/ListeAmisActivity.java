package be.lsinf1225.minipoll.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import be.lsinf1225.minipoll.DemandeAmisAdapter;
import be.lsinf1225.minipoll.ListeAmisAdapter;
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

        amis = new ArrayList<>();
        amis.add(new User("test", "test", "test", "test", "test"));
        amis.add(new User("test", "test", "test", "test", "test"));
        amis.add(new User("test", "test", "test", "test", "test"));
        amis.add(new User("test", "test", "test", "test", "test"));

        Log.i("DEBUG_J", "listamisactivity" + amis.size());



        recyclerView = (RecyclerView) findViewById(R.id.recycler_liste_amis);
        recyclerAmisAdapter = new ListeAmisAdapter(this, amis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAmisAdapter);
    }
}
