package be.lsinf1225.minipoll.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;


import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Dilemme implements Serializable {
    private static String u_mail = MiniPoll.getConnected_user().getMail();
    private static SparseArray<Dilemme> dilemmeSparseArray = new SparseArray<>();

    private int id;
    private String title;
    private String creator;
    private String participant;

    public Dilemme(int id, String title, String creator, String participant){
        this.id=id;
        this.creator=creator;
        this.title=title;
        this.participant=participant;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getCreator(){
        return creator;
    }
    public String getParticipant(){
        return participant;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ArrayList<Dilemme> getSQLDilemmeCree() {

        ArrayList<Dilemme> dilemmes = new ArrayList<>();

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sql = "SELECT IDdileme, Titre, Auteur" +
                " FROM Dilemme" +
                " WHERE Auteur = ? ;";

        Cursor c = db.rawQuery(sql, new String[]{"u_mail"});
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String titre = c.getString(2);
            String auteur = c.getString(1);
            String participant = c.getString(3);
            if(dilemmeSparseArray.get(id)!=null){
                dilemmes.add(new Dilemme(id, titre,auteur,participant));
            }
        }
        c.close();
        db.close();
        return dilemmes;

    }

    public static ArrayList<Dilemme> getSQLDilemmeParticipe() {

        ArrayList<Dilemme> dilemmes = new ArrayList<>();

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String sql = "SELECT IDdileme, Titre, Auteur" +
                " FROM Dilemme" +
                " WHERE Participant= ? ;";

        Cursor c = db.rawQuery(sql, new String[]{"u_mail"});
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String titre = c.getString(2);
            String auteur = c.getString(1);
            String participant = c.getString(3);
            if(dilemmeSparseArray.get(id)!=null){
                dilemmes.add(new Dilemme(id, titre,auteur,participant));
            }
        }
        c.close();
        db.close();
        return dilemmes;

    }


    public static ArrayList<Dilemme> getCreatorDilemmes(){
        ArrayList<Dilemme> dilemmes = new ArrayList<>();

        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String sqlID = "SELECT IDdilemme, Titre,Participant  FROM Dilemme WHERE Auteur = ?;";

        Cursor c = db.rawQuery(sqlID, new String[]{u_mail});

        c.moveToFirst();
        while (!c.isAfterLast()){

            int idDilemme = c.getInt(0);
            String titre = c.getString(1);
            String participant=c.getString(2);
            dilemmes.add(new Dilemme(idDilemme,titre, u_mail, participant)) ;
            c.moveToNext();
        }
        c.close();
        db.close();
        return dilemmes;
    }

    public int getStatut(){
        //TODO, regarder si participant a répondu
        return 1;
    }

}
