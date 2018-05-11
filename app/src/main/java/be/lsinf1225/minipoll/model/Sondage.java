package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
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
    private int[] answers;
    private int remainingAnswers;
    private Proposition[] propositions;
    private int propositionNumber;
    private boolean clotured;

    public Sondage(int id, String title, String creator, String[] participants, String[] enonces, int[] choix) {
        Log.i("test1",participants.length +" part =? choix "+ choix.length);
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.participants = participants;
        this.propositionNumber = enonces.length;
        this.propositions = new Proposition[propositionNumber];


        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "SELECT IDproposition FROM Proposition_sondage WHERE Ennonce_de_la_proposition = ?";
        Cursor c = db.rawQuery(sql, new String[]{enonces[0]});
        c.moveToFirst();
        int firstIDproposition = c.getInt(0);
        c.close();
        for(int i=0; i<propositionNumber; i++){
            propositions[i] = new Proposition(enonces[i], firstIDproposition+i, getScore(firstIDproposition+i, choix));
        }
        this.participantsNumber = participants.length;
        this.answers = choix;
        this.remainingAnswers = getRemainingParticipants().length;
        if(remainingAnswers == 0)  this.clotured = true;
    }

    public boolean isCreator(String mail){
        return mail.equals(creator);
    }

    public int getScore(int IDproposition, int[] choix){
        int res = 0;
        for(int i=0; i<choix.length; i++){
            if(choix[i] == IDproposition){
                res++;
            }
        }
        return res;
    }


    public void answerSondage(String participant, String proposition){
        int indexProposition = getPropositionIndex(proposition);
        int IDProposition = propositions[indexProposition].getIDproposition();
        answers[getParticipantIndex(participant)] = IDProposition;
        propositions[indexProposition].selected();
        remainingAnswers --;
        if(remainingAnswers == 0){
            cloture();
        }
        String sql = "UPDATE Participation_sondage SET IDchoix = ? WHERE Mail_participant = ?;";
        MySQLiteHelper.get().getWritableDatabase().execSQL(sql, new Object[]{IDProposition, participant});
    }

    public int getParticipantIndex(String participant){
        for(int i=0; i<participantsNumber; i++){
            if(participant.equals(participants[i])){
                return i;
            }
        }
        return -1;
    }

    public int getPropositionIndex(String proposition){
        for(int i=0; i<propositionNumber; i++){
            if(propositions[i].getEnonce().equals(proposition)){
                return i;
            }
        }
        return -1;
    }

    public String[] getRemainingParticipants(){
        //if(remainingAnswers == 0) return null;
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i<participantsNumber; i++){
            if(answers[i] == -1){
                list.add(participants[i]);
            }
            Log.i("test2","answers : " + answers[i]);
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

    public String[] getEnonces(){
        String[] enonces = new String[propositions.length];
        for(int i=0; i<propositions.length; i++){
            enonces[i] = propositions[i].getEnonce();
        }
        return enonces;
    }

    public ArrayList<Proposition> getPropositions(){
        int length = propositions.length;
        ArrayList<Proposition> res = new ArrayList<Proposition>(length);
        for(int i=0; i<length; i++){
            res.add(propositions[i]);
        }
        return res;
    }

    public void cloture(){
        clotured = true;
    }

    public int getStatus(){
        String mail = MiniPoll.getConnected_user().getMail();
        if(clotured) return 2;
        if(mail.equals(creator)) return 3;
        if(answers[getParticipantIndex(mail)] == -1){
            return 0;
        }
        return 1;
    }


    public static ArrayList<Sondage> getSondages(){
        ArrayList<Sondage> sondages = new ArrayList<Sondage>();
        String mail = MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sqlID = "SELECT IDsondage, Intitule, Mail_auteur  FROM Sondage WHERE Mail_auteur = ? UNION" +
                " SELECT S.IDsondage, S.Intitule, S.Mail_auteur FROM Sondage S, Participation_sondage PS WHERE Mail_participant = ? and S.IDSondage=PS.IDsondage;";
        Cursor c = db.rawQuery(sqlID, new String[]{mail, mail});
        c.moveToFirst();
        while (!c.isAfterLast()){
            int idSondage = c.getInt(0);
            Log.i("test1","id trouvé : "+idSondage);
            String titre = c.getString(1);
            String auteur = c.getString(2);
            sondages.add(new Sondage(idSondage,titre, auteur, getSQLParticipants(idSondage, db), getSQLPropositions(idSondage,db), getSQLChoix(idSondage, db))) ;
            c.moveToNext();
        }
        c.close();
        db.close();
        return sondages;
    }

    public static Sondage getSondage(int id){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sql = "SELECT Intitule, Mail_auteur FROM Sondage WHERE IDsondage = ?;";
        Cursor c = db.rawQuery(sql, new String[]{Integer.toString(id)});
        c.moveToFirst();
        Sondage s = new Sondage(id, c.getString(0), c.getString(1), getSQLParticipants(id, db), getSQLPropositions(id, db), getSQLChoix(id, db));
        c.close();
        db.close();
        return s;
    }

    private static String[] getSQLParticipants(int id, SQLiteDatabase db){
        String sqlPart = "SELECT distinct Mail_participant FROM Participation_sondage WHERE IDsondage = ? ORDER BY Mail_participant;";
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

    private static int[] getSQLChoix(int id, SQLiteDatabase db){
        String sqlPart = "SELECT IDchoix FROM Participation_sondage WHERE IDsondage = ? ORDER BY Mail_participant;";
        Cursor cPart = db.rawQuery(sqlPart, new String[]{Integer.toString(id)});
        ArrayList<Integer> choix = new ArrayList<Integer>();
        cPart.moveToFirst();
        while(!cPart.isAfterLast()){
            choix.add(cPart.getInt(0));
            cPart.moveToNext();
        }
        cPart.close();
        int[] choixTab = new int[choix.size()];
        for(int i=0; i<choix.size(); i++){
            choixTab[i] = choix.get(i).intValue();
        }
        return choixTab;
    }



    private static String[] getSQLPropositions(int id, SQLiteDatabase db){
        String sqlProp = "SELECT distinct O.Ennonce_de_la_proposition FROM Proposition_sondage O, Sondage S WHERE O.IDsondage = ? ORDER BY O.IDproposition";
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

    public class Proposition{
        private String enonce;
        private int IDproposition;
        private int score;

        public Proposition(String enonce, int IDproposition, int score) {
            this.enonce = enonce;
            this.score = score;
        }

        public String getEnonce() {
            return enonce;
        }

        public int getIDproposition(){
            return IDproposition;
        }

        public int getScore(){
            return  score;
        }

        public void selected() {
            score ++;
        }
    }
}
