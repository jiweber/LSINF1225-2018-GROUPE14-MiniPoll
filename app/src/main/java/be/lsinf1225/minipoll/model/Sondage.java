package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Sondage implements Serializable{

    public static final String DB_TABLE_USER = "Utilisateur";
    public static final String DB_TABLE_SONDAGE = "Sondage";

    private int id;
    private String title;
    private String creator;
    private ArrayList<String> participants;
    private int propositionNumber;
    private int[][] rangs;
    private Proposition[] propositions;
    private int[] status;

    public Sondage(int id, String title, String creator, ArrayList<String> participants, ArrayList<String> enonces) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.participants = participants;
        this.propositionNumber = enonces.size();
        this.rangs = new int[this.propositionNumber][participants.size()];
        this.propositions = new Proposition[propositionNumber];
        for(int i=0; i<propositionNumber; i++){
            this.propositions[i] = new Proposition(enonces.get(i));
        }
        this.status = new int[participants.size()];
    }


    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public int getStatus(String mail){
        double res = Math.random()*3;
        if(0<=res && res<1){
            return 0;
        }
        if(1<=res && res<2){
            return 1;
        }
        else{
            return 2;
        }
    }

    public static ArrayList<Sondage> getCreatorSondages(){
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        String mail = MiniPoll.getUserMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sqlID = "select IDsondage, Intitulé  from Sondage where Mail_Auteur =" + mail +";";
        Cursor c = db.rawQuery(sqlID, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int idSondage = c.getInt(0);
            String titre = c.getString(1);
            sondages.add(new Sondage(idSondage,titre, mail, getSQLParticipants(idSondage, db), getSQLPropositions(idSondage,db))) ;
            c.moveToNext();
        }
        c.close();
        db.close();
        return sondages;
    }

    /*
    public static Sondage getSondage(int id){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "select Intitulé, Mail_Auteur from Sondage S where S.IDsondage =" + id + ";";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        Sondage s = new Sondage(id, c.getString(0), c.getString(1), getSQLParticipants(id, db), getSQLPropositions(id, db))) ;
        c.close();
        db.close();
        return s;
    }
    */

    private static ArrayList<String> getSQLParticipants(int id, SQLiteDatabase db){
        String sqlPart = "select A.Mail_participant from Participation_sondage A, Sondage S where A.IDsondage =" + id +";";
        Cursor cPart = db.rawQuery(sqlPart, null);
        ArrayList<String> participants = new ArrayList<String>();
        cPart.moveToFirst();
        while(!cPart.isAfterLast()){
            participants.add(cPart.getString(0));
            cPart.moveToNext();
        }
        cPart.close();
        return participants;
    }

    private static ArrayList<String> getSQLPropositions(int id, SQLiteDatabase db){
        String sqlProp = "select O.Ennoncé_de_la_proposition from Proposition_sondage O, Sondage S where O.IDsondage =" + id +";";
        Cursor cProp = db.rawQuery(sqlProp, null);
        ArrayList<String> propositions = new ArrayList<String>();
        cProp.moveToFirst();
        while(!cProp.isAfterLast()){
            propositions.add(cProp.getString(0));
            cProp.moveToNext();
        }
        cProp.close();
        return propositions;
    }

    public static String[] getRemainingFriends()
    {
        return null;                            //TODO
    }
    private class Proposition {
        private String enonce;
        private int generalRank;

        public Proposition(String enonce) {
            this.enonce = enonce;
            generalRank = 0;
        }

        public void setGeneralRank(int generalRank) {
            this.generalRank = generalRank;
        }
    }
}
