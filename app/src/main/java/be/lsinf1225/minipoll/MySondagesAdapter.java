package be.lsinf1225.minipoll;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.lsinf1225.minipoll.model.Sondage;

public class MySondagesAdapter extends ArrayAdapter<Sondage> {

    //sondages est la liste des modeles à afficher
    public MySondagesAdapter(Context context, List<Sondage> sondages) {
        super(context, 0, sondages);
    }
//test
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_sondage,parent, false);
        }

        SondageViewHolder viewHolder = (SondageViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SondageViewHolder();
            viewHolder.creator = (TextView) convertView.findViewById(R.id.creator);
            viewHolder.enonce = (TextView) convertView.findViewById(R.id.title);
            viewHolder.status = (ImageView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Sondage> sondages
        Sondage sondage = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.creator.setText(sondage.getCreator());
        viewHolder.enonce.setText(sondage.getTitle());
        int statut = sondage.getStatus();
        Log.i("test2","getStatus() : "+statut);
        Resources res = getContext().getResources();
        if(statut == 0) {
            viewHolder.status.setImageDrawable(res.getDrawable(R.drawable.a_remplir));
        }
        else if(statut == 1){
            viewHolder.status.setImageDrawable(res.getDrawable(R.drawable.en_attente));
        }
        else if(statut == 2){
            viewHolder.status.setImageDrawable(res.getDrawable(R.drawable.remplis));
        }
        else{
            viewHolder.status.setImageDrawable(res.getDrawable(R.drawable.creator));
        }

        return convertView;
    }


    private class SondageViewHolder{
        public TextView creator;
        public TextView enonce;
        public ImageView status;
    }
}
