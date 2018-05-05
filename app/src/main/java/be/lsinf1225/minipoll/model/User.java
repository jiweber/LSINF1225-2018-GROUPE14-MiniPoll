package be.lsinf1225.minipoll.model;

public class User {
    private String mail;
    private String mdp;
    private String prenom;
    private String nom;
    private String bestFriend;

    public User(String mail, String mdp, String prenom, String nom) {
        this.mail = mail;
        this.mdp = mdp;
        this.prenom = prenom;
        this.nom = nom;
        this.bestFriend = null;
    }

    public String getMail() {
        return mail;
    }


}
