package be.lsinf1225.minipoll.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.lsinf1225.minipoll.MySondagesAdapter;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.User;

import static be.lsinf1225.minipoll.MiniPoll.getContext;

public class SwipeDeckAdapter extends BaseAdapter {

    private List<User> data;
    private Context context;

    public SwipeDeckAdapter(List<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tinder_card,parent, false);
        }
        SwipeDeckAdapter.DeckViewHolder viewHolder = (SwipeDeckAdapter.DeckViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SwipeDeckAdapter.DeckViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.tinder_name);
            viewHolder.profile_image = (ImageView) convertView.findViewById(R.id.tinder_picture);
            convertView.setTag(viewHolder);
        }

        viewHolder.name.setText(data.get(position).getPrenom() + " " + data.get(position).getNom());
        Bitmap bitmap = data.get(position).getBitmap();
        if (bitmap != null)viewHolder.profile_image.setImageBitmap(bitmap);


        return convertView;
    }

    private class DeckViewHolder{
        public TextView name;
        public ImageView profile_image;
    }
}