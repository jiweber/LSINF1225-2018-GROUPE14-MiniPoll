package be.lsinf1225.minipoll.model;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Question {

    private int QstnrID;
    private int QstID;
    private String QstTitle;
    private int QstNum;

    //constructor
    public Question(int qstnrID, int qstID, String qstTitle, int qstNum) {
        QstnrID = qstnrID;
        QstID = qstID;
        QstTitle = qstTitle;
        QstNum = qstNum;
    }

    //getters
    public int getQstnrID() {
        return QstnrID;
    }

    public int getQstID() {
        return QstID;
    }

    public String getQstTitle() {
        return QstTitle;
    }

    public int getQstNum() {
        return QstNum;
    }

    public static ArrayList<Question> getSQLQuestion(){

        ArrayList<Question> qsts = new ArrayList<>();
        String mail= MiniPoll.getConnected_user().getMail();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sqlMain = "SELECT PQ.IDquestionnaire, Q.IDquestion, Q.Enonce,Q.number" +
                " FROM Participation_questionnaire PQ,Question Q" +
                " WHERE PQ.IDquestionnaire=Q.IDquestionnaire and PQ.mail='"+mail+"';";

        Cursor curs=db.rawQuery(sqlMain,null);
        curs.moveToFirst();

        while (!curs.isAfterLast()){
            int idQ = curs.getInt(0);
            int idq = curs.getInt(1);
            String qTitle = curs.getString(2);
            int qNbr = curs.getInt(3);

            Question qst = new Question(idQ,idq,qTitle,qNbr);
            qsts.add(qst);
            curs.moveToNext();
        }

        curs.close();
        db.close();

        return qsts;
    }

    public static List<String> getTitles(ArrayList<Question> questions){

        List<String> titles = new ArrayList<String>();
        for (int i=0;i < questions.size(); i++){
            titles.add(questions.get(i).getQstTitle());
        }

        return titles;
    }

    public static ArrayList<Question> getSmallQst(ArrayList<Question> questions,int IDqstnr){

        ArrayList<Question> SmallQstnr = new ArrayList<Question>();

        int j=0;

        for (int i=0;i<questions.size();i++){
            Log.i("Qst_list: ", questions.get(i).toString());

            if(questions.get(i).getQstnrID()==IDqstnr){
                SmallQstnr.add(questions.get(i));
                j++;
            }
        }

        return SmallQstnr;

    }

    public String toString(){
        String resume = "ID qstnr: " + QstnrID
                      + " ID question: " + QstID
                      + " Enoncé: " + QstTitle
                      + " Numéro de la question: " + QstNum;

        return resume;
    }
}
