package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Questnr {

    private int IDQstnr;
    private String TitreQstnr;
    private String AuteurQstnr;
    private int ScoreQstnr;

    private String u_mail = MiniPoll.getConnected_user().getMail();

    private static SparseArray<Questnr> questnrSparseArray = new SparseArray<>();

    private Questnr(int IDquestnr, String titre, String auteur,int score) {
        this.IDQstnr = IDquestnr;
        this.TitreQstnr = titre;
        this.AuteurQstnr = auteur;
        this.ScoreQstnr = score;
        Questnr.questnrSparseArray.put(IDquestnr,this);
    }

    public int getIDQstnr() {
        return IDQstnr;
    }

    public String getTitreQstnr() {
        return TitreQstnr;
    }

    public String getAuteurQstnr() {
        return AuteurQstnr;
    }

    public int getScoreQstnr() { return ScoreQstnr; }

    public static ArrayList<Questnr> getSQLQuestnr(){

        ArrayList<Questnr> questnrs = new ArrayList<Questnr>();
        //String mail= MiniPoll.getUserMail();
        String mail="LDV@uclouvain.be";
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sql = "SELECT Q.IDquestionnaire, Q.Titre, Q.Auteur, PQ.Score" +
                " FROM Questionnaire Q, Participation_questionnaire PQ" +
                " WHERE Q.IDquestionnaire=PQ.IDquestionnaire and PQ.mail='"+mail+"'"+";";

        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int id = c.getInt(0);
            String titre = c.getString(1);
            String auteur = c.getString(2);
            int score = c.getInt(3);

            Questnr questnr = Questnr.questnrSparseArray.get(id);
            if(questnr==null){
                questnr = new Questnr(id,titre,auteur,score);
            }

            questnrs.add(questnr);
            c.moveToNext();
        }

        c.close();
        db.close();

        return questnrs;
    }

    public static List<String> getTtitles(){
        List<String> titles = new ArrayList<String>();
        ArrayList<Questnr> qst = Questnr.getSQLQuestnr();
        for (int i=0;i < qst.size();i++){
            titles.add(qst.get(i).getTitreQstnr());
        }
        return  titles;
    }

    public String toString(){
        String resume = "Participant: " + u_mail + ";" + Integer.toString(IDQstnr) + " " + TitreQstnr + " " + AuteurQstnr;
        return resume;
    }

}
