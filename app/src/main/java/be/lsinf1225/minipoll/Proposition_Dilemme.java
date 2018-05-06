package be.lsinf1225.minipoll.model;

import java.io.Serializable;

public class Proposition_Dilemme  implements Serializable{
    public static final String DB_TABLE_DILEMME = "Dilemme";
    private int ID_Dilemme;
    private int ID_Proposition;
    private String Sujet;
    private String Format;

    public Proposition_Dilemme(int ID_Dilemme, int ID_Proposition,String Sujet, String Format){
        this.Format=Format;
        this.ID_Dilemme=ID_Dilemme;
        this.ID_Proposition=ID_Proposition;
        this.Sujet=Sujet;
    }

    public int getID_Dilemme(){
        return ID_Dilemme;
    }

    public int getID_Proposition() {
        return ID_Proposition;
    }

    public String getFormat() {
        return Format;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public void setID_Dilemme(int ID_Dilemme) {
        this.ID_Dilemme = ID_Dilemme;
    }

    public void setID_Proposition(int ID_Proposition) {
        this.ID_Proposition = ID_Proposition;
    }

    public void setSujet(String sujet) {
        Sujet = sujet;
    }


}
