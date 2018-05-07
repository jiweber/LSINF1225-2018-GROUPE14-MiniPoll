package be.lsinf1225.minipoll.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import be.lsinf1225.minipoll.DemandeAmisAdapter;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class DemandeAmisActivity extends AppCompatActivity {

    ArrayList<User> amis;
    DemandeAmisAdapter demandeAmisAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_amis);

        amis = new ArrayList<>();
        amis.add(new User("test", "test", "test", "test", null));
        amis.add(new User("test", "test", "test", "test", null));
        amis.add(new User("test", "test", "test", "test", null));
        amis.add(new User("test", "test", "test", "test", null));



        recyclerView = (RecyclerView) findViewById(R.id.recycler_liste_amis);
        demandeAmisAdapter = new DemandeAmisAdapter(this, amis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(demandeAmisAdapter);
    }
}
