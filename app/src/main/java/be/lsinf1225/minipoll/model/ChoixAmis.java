package be.lsinf1225.minipoll.model;

public class ChoixAmis {

    private int n_ami;
    private boolean ajoute;

    public ChoixAmis(int n_ami){
        this.n_ami = n_ami;
        this.ajoute = false;
    }

    public int getN_ami() {
        return n_ami;
    }

    public boolean isAjoute() {
        return ajoute;
    }

    public void setAjoute(boolean ajoute) {
        this.ajoute = ajoute;
    }
}
