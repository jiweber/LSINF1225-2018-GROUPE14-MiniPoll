package be.lsinf1225.minipoll.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import be.lsinf1225.minipoll.MiniPoll;
import be.lsinf1225.minipoll.MySQLiteHelper;

public class Dilemme implements Serializable{
    public static final String DB_TABLE_USER = "Utilisateur";
    public static final String DB_TABLE_DILEMME = "Dilemme";

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
}
