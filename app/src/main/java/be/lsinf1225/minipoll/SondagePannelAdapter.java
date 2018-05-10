package be.lsinf1225.minipoll;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.lsinf1225.minipoll.model.Sondage;


public class SondagePannelAdapter extends ArrayAdapter<Sondage.Proposition> {

    //propositions est la liste des modeles à afficher
    public SondagePannelAdapter(Context context, List<Sondage.Proposition> propositions) {
        super(context, 0, propositions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_result,parent, false);
        }

        SondageViewHolder viewHolder = (SondageViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SondageViewHolder();
            viewHolder.score = (TextView) convertView.findViewById(R.id.tv_score);
            viewHolder.enonce = (TextView) convertView.findViewById(R.id.tv_enonce);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<String> propositions
        Sondage.Proposition proposition = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.score.setText(Integer.toString(proposition.getScore()));
        viewHolder.enonce.setText(proposition.getEnonce());
        Resources res = getContext().getResources();
        return convertView;
    }


    private class SondageViewHolder{
        public TextView score;
        public TextView enonce;
    }
}
