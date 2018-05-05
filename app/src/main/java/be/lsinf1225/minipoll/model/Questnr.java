package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Questnr {

    private int IDQstnr;
    private String TitreQstnr;
    private String AuteurQstnr;

    public Questnr(int IDquestnr, String titre, String auteur) {
        this.IDQstnr = IDquestnr;
        this.TitreQstnr = titre;
        this.AuteurQstnr = auteur;
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

    private static ArrayList<Questnr> getSQLQuestnr(){

        ArrayList<Questnr> questnr = new ArrayList<Questnr>();
        String mail= MiniPoll.getUserMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sql = "SELECT Q.IDquestionnaire, Q.Titre, Q.Auteur" +
                "FROM Questionnaire Q, Participation_questionnaire PQ" +
                "WHERE Q.IDquestionnaire=PQ.IDquestionnaire and PQ.mail='"+mail+"'"+";";

        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int id = c.getInt(0);
            String titre = c.getString(1);
            String auteur = c.getString(2);
            questnr.add(new Questnr(id,titre,auteur));
            c.moveToNext();
        }

        c.close();
        db.close();
        return questnr;
    }

    public static List<String> getTtitles(){
        List<String> titles = new ArrayList<String>();
        ArrayList<Questnr> qst = Questnr.getSQLQuestnr();
        for (int i=0;i<qst.size();i++){
            titles.add(qst.get(i).getTitreQstnr());
        }
        return  titles;
    }

}
