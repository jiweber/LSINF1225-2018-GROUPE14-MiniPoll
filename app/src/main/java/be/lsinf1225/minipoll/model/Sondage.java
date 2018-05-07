package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;
import be.lsinf1225.minipoll.activity.ConnexionActivity;

public class Sondage{

    private int id;
    private String title;
    private String creator;
    private String[] participants;
    private int participantsNumber;
    private int[] ranks;
    private int[] answers;
    private int remainingAnswers;
    private Proposition[] propositions;

    public Sondage(int id, String title, String creator, String[] participants, String[] enonces) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.participants = participants;
        int propositionNumber = enonces.length;
        this.ranks = new int[propositionNumber];
        this.propositions = new Proposition[propositionNumber];
        for(int i=0; i<propositionNumber; i++){
            this.propositions[i] = new Proposition(enonces[i]);
        }
        this.participantsNumber = participants.length;
        this.answers = new int[participantsNumber];
        for(int i=0; i<participantsNumber; i++){
            answers[i] = -1;
        }
        this.remainingAnswers = participantsNumber;
    }

    public Proposition[] getPropositions() {
        return propositions;
    }

    public void answerSondage(String participant, String proposition){
        answers[getParticipantIndex(participant)] = getPropositionIndex(proposition);
        remainingAnswers --;
    }

    public int getParticipantIndex(String participant){
        for(int i=0; i<participantsNumber; i++){
            if(participant.compareTo(participants[i]) == 0){
                return i;
            }
            Log.i("test2", participant + " != " + participants[i]);
        }
        return -1;
    }

    public int getPropositionIndex(String proposition){
        for(int i=0; i<participantsNumber; i++){
            if(propositions[i].getEnonce().compareTo(participants[i]) == 0){
                return i;
            }
        }
        return -1;
    }

    public String[] getRemainingParticipants(){
        if(remainingAnswers == 0) return null;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<participantsNumber; i++){
            if(answers[i] == -1){
                list.add(participants[i]);
            }
        }
        String[] tab = new String[list.size()];
        tab = list.toArray(tab);
        return tab;
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

    public int getStatus(){
        String mail = MiniPoll.getConnected_user().getMail();
//        double rand = Math.random()*3;
//        if(rand>=0 && rand<1) return 0;
//        if(rand>=1 && rand<2) return 1;
//        return 2;
        if(mail.compareTo(creator) == 0) return 3;
        if(remainingAnswers == 0) return 2;
        if(answers[getParticipantIndex(mail)] == -1){
            return 0;
        }
        return 1;
    }

    public String[] getEnonces(){
        String[] enonces = new String[propositions.length];
        for(int i=0; i<propositions.length; i++){
            enonces[i] = propositions[i].getEnonce();
        }
        return enonces;
    }

    public static ArrayList<Sondage> getCreatorSondages(){
        Log.i("test1","entrée getCreatorSondages()");
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        String mail = MiniPoll.getConnected_user().getMail();
        Log.i("test1",mail);
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sqlID = "SELECT IDsondage, Intitule  FROM Sondage WHERE Mail_auteur = ?;";
        Log.i("test1","sqlID :"+sqlID);
        Cursor c = db.rawQuery(sqlID, new String[]{mail});
        Log.i("test1", String.valueOf(c.getCount()));
        c.moveToFirst();
        while (!c.isAfterLast()){
            Log.i("test1","entrée boucle getCreatorSondages()");
            int idSondage = c.getInt(0);
            String titre = c.getString(1);
            sondages.add(new Sondage(idSondage,titre, mail, getSQLParticipants(idSondage, db), getSQLPropositions(idSondage,db))) ;
            c.moveToNext();
        }
        Log.i("test1","sondages ajoutés : "+sondages.size());
        c.close();
        db.close();
        return sondages;
    }

    public static Sondage getSondage(int id){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "SELECT Intitule, Mail_auteur FROM Sondage S WHERE S.IDsondage = ?;";
        Cursor c = db.rawQuery(sql, new String[]{Integer.toString(id)});
        c.moveToFirst();
        Sondage s = new Sondage(id, c.getString(0), c.getString(1), getSQLParticipants(id, db), getSQLPropositions(id, db));
        c.close();
        db.close();
        return s;
    }

    private static String[] getSQLParticipants(int id, SQLiteDatabase db){
        Log.i("test1","id in part : "+id);
        String sqlPart = "SELECT distinct A.Mail_participant FROM Participation_sondage A, Sondage S WHERE A.IDsondage = ?;";
        Log.i("test1","sqlPart : "+sqlPart);
        Cursor cPart = db.rawQuery(sqlPart, new String[]{Integer.toString(id)});
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
        Log.i("test1","id in prop : "+id);
        String sqlProp = "SELECT distinct O.Ennonce_de_la_proposition FROM Proposition_sondage O, Sondage S WHERE O.IDsondage = ?";
        Log.i("test1","sqlProp : "+sqlProp);
        Cursor cProp = db.rawQuery(sqlProp, new String[]{Integer.toString(id)});
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
