package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Reponse {

    private int QstID;
    private String QstTitle;
    private String RepFormat;
    private String RepTexte;
    private String RepSol;

    //constructor
    public Reponse(int qstID,String qstTitle ,String repFormat, String repTexte, String repSol) {
        QstID = qstID;
        QstTitle =qstTitle;
        RepFormat = repFormat;
        RepTexte = repTexte;
        RepSol = repSol;
    }

    //getters
    public int getQstID() {
        return QstID;
    }

    public String getQstTitle() {
        return QstTitle;
    }

    public String getRepFormat() {
        return RepFormat;
    }

    public String getRepTexte() {
        return RepTexte;
    }

    public String isRepSol() {
        return RepSol;
    }

    public static ArrayList<Reponse> getSQLReponse(){

        ArrayList<Reponse> reps = new ArrayList<>();
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sqlMain = "SELECT RQ.IDquestion,Q.Enonce,RQ.Format,RQ.Texte,RQ.Est_solution" +
                " FROM Participation_questionnaire PQ,Question Q,Reponse_questionnaire RQ" +
                " WHERE PQ.IDquestionnaire=Q.IDquestionnaire and Q.IDquestion=RQ.IDquestion and PQ.mail='" +mail+"';";

        Cursor cu = db.rawQuery(sqlMain,null);
        cu.moveToFirst();

        while(!cu.isAfterLast()){
            int idqst = cu.getInt(0);
            String title = cu.getString(1);
            String format = cu.getString(2);
            String texte = cu.getString(3);
            String IsSol = cu.getString(4);

            Reponse rep = new Reponse(idqst,title,format,texte,IsSol);
            reps.add(rep);
            cu.moveToNext();
        }

        cu.close();
        db.close();

        return reps;

    }

    public static List<String> getTextes(ArrayList<Reponse> reponses){

        List<String> textes = new ArrayList<String>();
        for(int i=0;i<reponses.size();i++){
            textes.add(reponses.get(i).getRepTexte());
        }

        return textes;
    }

    public static ArrayList<Reponse> getSmallRep(ArrayList<Reponse> reps,int idQst){

        ArrayList<Reponse> SmallQst = new ArrayList<Reponse>();

        int j=0;

        for (int i=0;i<reps.size();i++){
            Log.i("Rep_list: ",reps.get(i).toString());

            if(reps.get(i).getQstID()==idQst){
                SmallQst.add(reps.get(i));
                j++;
            }
        }
        return SmallQst;
    }

    public String toString(){
        String resume = "Id question: " + QstID
                       + " Texte Réponse: " + RepTexte
                       + " Est réponse ? " + RepSol;
        return resume;
    }

}
