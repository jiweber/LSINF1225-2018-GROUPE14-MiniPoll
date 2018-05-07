package be.lsinf1225.minipoll;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import be.lsinf1225.minipoll.model.User;


/**
 * Created by jerome on 09/02/17.
 */
public class ListeAmisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<User> amis;


    public ListeAmisAdapter(Context c, ArrayList<User> amis) {
        this.context = c;
        this.amis = amis;
        this.context =c;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nom_ami;
        public ImageView photo_ami;


        public MyViewHolder(View itemView) {
            super(itemView);
            nom_ami = (TextView) itemView.findViewById(R.id.nom_ami);
            photo_ami = (ImageView) itemView.findViewById(R.id.photo_ami);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_amis, parent, false);
            return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.nom_ami.setText(amis.get(position).getPrenom() + " " +amis.get(position).getNom() );
        Bitmap bitmap = MiniPoll.getConnected_user().getBitmap();
        if (bitmap == null){
            myViewHolder.photo_ami.setImageResource(R.drawable.friends);

        }else{
            myViewHolder.photo_ami.setImageBitmap(bitmap);

        }
    }

    @Override
    public int getItemCount() {
        Log.i("DEBUG_J", "adapter"+String.valueOf(amis.size()));
        return amis.size();
    }

}
