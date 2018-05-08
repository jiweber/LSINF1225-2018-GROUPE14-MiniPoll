package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Questnr {

    private int IDQstnr;
    private String TitreQstnr;
    private String AuteurQstnr;
    private int ScoreQstnr;
    private int nbrQuestions;

    //private static SparseArray<Questnr> questnrSparseArray = new SparseArray<>();

    private Questnr(int IDquestnr, String titre, String auteur,int score,int nbr_qst) {
        this.IDQstnr = IDquestnr;
        this.TitreQstnr = titre;
        this.AuteurQstnr = auteur;
        this.ScoreQstnr = score;
        this.nbrQuestions = nbr_qst;
        //Questnr.questnrSparseArray.put(IDquestnr,this);
    }


    //getters
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

    public int getNbrQuestions() {
        return nbrQuestions;
    }


    // incrémentation de Statut: à utiliser lorsque le ClinOnItem se produit
    // to do

    // insntanciations des questionnaires pour le user courant dans un Arraylist
    public static ArrayList<Questnr> getSQLQuestnr(){

        ArrayList<Questnr> questnrs = new ArrayList<Questnr>();

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String mail = MiniPoll.getConnected_user().getMail();

        String sqlMain = "SELECT Q.IDquestionnaire, Q.Titre, Q.Auteur, PQ.Score, nbr_qst" +
                " FROM Questionnaire Q, Participation_questionnaire PQ, " +
                "(SELECT Qstnr.IDquestionnaire, count(*) as nbr_qst" +
                " FROM Questionnaire qstnr,Question qst" +
                " WHERE qstnr.IDquestionnaire=qst.IDquestionnaire" +
                " GROUP BY Qstnr.IDQuestionnaire) as cnt" +
                " WHERE Q.IDquestionnaire=PQ.IDquestionnaire and cnt.IDquestionnaire=Q.IDquestionnaire" +
                " and PQ.mail='" +mail+"';";

        // querry utiliser pour vérifier que la table n'est pas vide
        // et que donc le user courant participe actuelement à des questionnaires
        String sqlCount = "SELECT count(*) FROM (SELECT Q.IDquestionnaire, Q.Titre, Q.Auteur, PQ.Score" +
                " FROM Questionnaire Q, Participation_questionnaire PQ" +
                " WHERE Q.IDquestionnaire=PQ.IDquestionnaire and PQ.mail='"+mail+"');";
        Cursor count = db.rawQuery(sqlCount,null);
        count.moveToFirst();

        if(count.getInt(0)==0){
            questnrs.add(new Questnr(0,"Pas de Questionnaire en cours",null,0,0));
        } else {
            // récupération de l'IDquestionnaire, le titre, l'auteur du score et du nbr de questions
            Cursor c = db.rawQuery(sqlMain,null);
            c.moveToFirst();
            while (!c.isAfterLast()){
                int id = c.getInt(0);
                String titre = c.getString(1);
                String auteur = c.getString(2);
                int score = c.getInt(3);
                int nbr = c.getInt(4);

                /*Questnr questnr = Questnr.questnrSparseArray.get(id);
                if(questnr==null){
                    questnr = new Questnr(id,titre,auteur,score);
                }*/

                Questnr questnr = new Questnr(id,titre,auteur,score,nbr);

                questnrs.add(questnr);
                c.moveToNext();

            }

            c.close();


        }

        count.close();
        db.close();

        return questnrs;
    }

    // methode pour simplifier l'affichage des sujest dans l'activité MyQuestnrActivity
    public static List<String> getTtitles(){
        List<String> titles = new ArrayList<String>();
        ArrayList<Questnr> qst = Questnr.getSQLQuestnr();
        for (int i=0;i < qst.size();i++){
            titles.add(qst.get(i).getTitreQstnr());
        }
        return  titles;
    }

    public String toString(){
        String resume = "ID: " + Integer.toString(IDQstnr)
                        + " Titre: " + TitreQstnr
                        + " Auteur: " + AuteurQstnr
                        + " Score: " + ScoreQstnr
                        + " Nbr de questions: " + nbrQuestions;
        return resume;
    }

}
