package be.lsinf1225.minipoll.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import be.lsinf1225.minipoll.BitmapUtil;
import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.R;
import be.lsinf1225.minipoll.model.ChoixAmis;

public class ChoixAmiAdapter extends BaseAdapter {
    private List<ChoixAmis> liste;
    private Context context;
    private LayoutInflater inflater;

    public ChoixAmiAdapter(Context context, List<ChoixAmis> liste) {
        this.context = context;
        this.liste = liste;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int i) {
        return liste.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.popup_choisir_amis, null);
        ChoixAmis current = (ChoixAmis) getItem(i);
        Button coch = view.findViewById(R.id.radioButton);
        ImageView pp = view.findViewById(R.id.pop_ami_pp);
        TextView name = view.findViewById(R.id.pop_ami_nom);
        Bitmap pppic;
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String SQL = "SELECT Utilisateur2 FROM Relation WHERE Utilisateur1 = '"+mail+"', Statut = 'Ami';";
        Cursor c = db.rawQuery(SQL,null);
        c.moveToPosition(i);
        String mailami = c.getString(0);
        c.close();
        SQLiteDatabase db2 = MySQLiteHelper.get().getReadableDatabase();
        String SQL2 = "SELECT Nom,Prénom,Photo FROM Utilisateur WHERE Mail = '"+mailami+"';";
        Cursor c2 = db2.rawQuery(SQL2,null);
        c2.moveToFirst();
        String Nom = ""+c.getString(0)+" "+c.getString(1);
        pppic = BitmapUtil.getBitmap(c2.getBlob(2));
        c2.close();
        name.setText(Nom);
        pp.setImageBitmap(pppic);
        return view;
    }
}
