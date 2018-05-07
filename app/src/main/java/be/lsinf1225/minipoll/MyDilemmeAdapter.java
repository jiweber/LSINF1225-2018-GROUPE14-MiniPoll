package be.lsinf1225.minipoll;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.lsinf1225.minipoll.model.Dilemme;
import be.lsinf1225.minipoll.model.Sondage;

public class MyDilemmeAdapter extends ArrayAdapter<Dilemme> {

    //sondages est la liste des modeles à afficher
    public MyDilemmeAdapter(Context context, List<Dilemme> dilemmes) {
        super(context, 0, dilemmes);
    }
    //test
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_sondage,parent, false);
        }

        DilemmeViewHolder viewHolder = (DilemmeViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new DilemmeViewHolder();
            viewHolder.creator = (TextView) convertView.findViewById(R.id.creator);
            viewHolder.enonce = (TextView) convertView.findViewById(R.id.title);
            viewHolder.status = (ImageView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Dilemme> dilemme
        Dilemme dilemme= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.creator.setText(dilemme.getCreator());
        viewHolder.enonce.setText(dilemme.getTitle());
        Resources res = getContext().getResources();

        //TODO inserer image en fonction de statut
            viewHolder.status.setImageDrawable(res.getDrawable(R.drawable.a_remplir));

        return convertView;
    }


    private class DilemmeViewHolder{
        public TextView creator;
        public TextView enonce;
        public ImageView status;
    }
}
