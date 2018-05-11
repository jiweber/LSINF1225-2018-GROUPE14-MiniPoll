package be.lsinf1225.minipoll.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;
import java.util.zip.Inflater;

import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.ItemButton;

/**
 * Created by guill on 10-05-18.
 */

public class ItemButtonAdapter extends BaseAdapter {
    private Context context;
    private List<ItemButton> listBut;
    private LayoutInflater inflater;

    public ItemButtonAdapter(Context context, List<ItemButton> listBut) {
        this.context = context;
        this.listBut = listBut;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listBut.size();
    }

    @Override
    public Object getItem(int i) {
        return listBut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.button_new_quest,null);
        ItemButton currentItem = (ItemButton)getItem(i);
        int num = (currentItem.getNumero())+1;

        Button but = view.findViewById(R.id.item_but);
        String nquest = "Question n°" + num;
        but.setText(nquest);

        return view;
    }

}
