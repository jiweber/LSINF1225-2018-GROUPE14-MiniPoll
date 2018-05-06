package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Sondage implements Serializable{

    private int id;
    private String title;
    private String creator;
    private String[] participants;
    private int[][] rangs;
    private Proposition[] propositions;
    private int[] status;

    public Sondage(int id, String title, String creator, String[] participants, String[] enonces) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.participants = participants;
        int propositionNumber = enonces.length;
        this.rangs = new int[propositionNumber][participants.length];
        this.propositions = new Proposition[propositionNumber];
        for(int i=0; i<propositionNumber; i++){
            this.propositions[i] = new Proposition(enonces[i]);
        }
        this.status = new int[participants.length];
    }

    public Proposition[] getPropositions() {
        return propositions;
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

    public String[] getEnonces(){
        String[] enonces = new String[propositions.length];
        for(int i=0; i<propositions.length; i++){
            enonces[i] = propositions[i].getEnonce();
        }
        return enonces;
    }

    public static ArrayList<Sondage> getCreatorSondages(){
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        String mail = MiniPoll.getUserMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sqlID = "SELECT IDsondage, Intitulé  FROM Sondage WHERE Mail_Auteur = ?;";
        Cursor c = db.rawQuery(sqlID, new String[]{mail});
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
        String sql = "SELECT Intitulé, Mail_Auteur FROM Sondage S WHERE S.IDsondage ='" + id + "';";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        Sondage s = new Sondage(id, c.getString(0), c.getString(1), getSQLParticipants(id, db), getSQLPropositions(id, db))) ;
        c.close();
        db.close();
        return s;
    }
    */

    private static String[] getSQLParticipants(int id, SQLiteDatabase db){
        String sqlPart = "SELECT A.Mail_participant FROM Participation_sondage A, Sondage S WHERE A.IDsondage = ?;";
        Cursor cPart = db.rawQuery(sqlPart, new String[]{""+id});
        ArrayList<String> participants = new ArrayList<String>();
        cPart.moveToFirst();
        while(!cPart.isAfterLast()){
            participants.add(cPart.getString(0));
            cPart.moveToNext();
        }
        cPart.close();
        String[] participantsTab = new String[participants.size()];
        participantsTab = participants.toArray(participantsTab);
        return participantsTab;
    }

    private static String[] getSQLPropositions(int id, SQLiteDatabase db){
        String sqlProp = "SELECT O.Ennoncé_de_la_proposition FROM Proposition_sondage O, Sondage S WHERE O.IDsondage = ?;";
        Cursor cProp = db.rawQuery(sqlProp, new String[]{""+id});
        ArrayList<String> propositions = new ArrayList<String>();
        cProp.moveToFirst();
        while(!cProp.isAfterLast()){
            propositions.add(cProp.getString(0));
            cProp.moveToNext();
        }
        cProp.close();
        String[] propositionsTab = new String[propositions.size()];
        propositionsTab = propositions.toArray(propositionsTab);
        return propositionsTab;
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

        public String getEnonce() {
            return enonce;
        }

        public void setGeneralRank(int generalRank) {
            this.generalRank = generalRank;
        }
    }
}
