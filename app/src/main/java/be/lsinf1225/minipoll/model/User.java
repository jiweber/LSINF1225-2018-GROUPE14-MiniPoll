package be.lsinf1225.minipoll.model;

import android.net.Uri;

public class User {
    private static String userMail;
    private String mail;
    private String mdp;
    private String prenom;
    private String nom;
    private String bestFriend;
    private String imagePath;

    public User(String mail, String mdp, String prenom, String nom, String imagePath) {
        this.mail = mail;
        this.mdp = mdp;
        this.prenom = prenom;
        this.nom = nom;
        this.bestFriend = null;
        this.imagePath = imagePath;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(String bestFriend) {
        this.bestFriend = bestFriend;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public static String getUserMail() {
        return userMail;
    }

    public static void setUserMail(String mail) {
        userMail = mail;
    }

}
