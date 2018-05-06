package be.lsinf1225.minipoll.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

public class ChercherAmisActivity extends AppCompatActivity {

    Button btn_cancel;
    Button btn_accept;
    SwipeDeck cardStack;

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

        final ArrayList<User> data = new ArrayList<>();
        data.add(new User("test", "test", "test", "test", "test"));
        data.add(new User("test", "test", "test", "test", "test"));
        data.add(new User("test", "test", "test", "test", "test"));
        data.add(new User("test", "test", "test", "test", "test"));


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


    }
}
