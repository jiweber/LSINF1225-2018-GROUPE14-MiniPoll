package be.lsinf1225.minipoll;


public class Sondage {
    private String title;
    private String creator;
    private String[] participants;
    private int propositionNumber;
    private int[][] rangs;
    private Proposition[] propositions;
    private int[] status;

    public Sondage(String title, String creator, String[] participants, String[] enonces) {
        this.title = title;
        this.creator = creator;
        this.participants = participants;
        this.propositionNumber = enonces.length;
        this.rangs = new int[this.propositionNumber][participants.length];
        this.propositions = new Proposition[propositionNumber];
        for(int i=0; i<propositionNumber; i++){
            this.propositions[i] = new Proposition(enonces[i]);
        }
        this.status = new int[participants.length];
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public int getStatus(String user){
        double res = Math.random()*3;
        if(0<=res && res<1){
            return 0;
        }
        if(1<=res && res<2){
            return 1;
        }
        else{
            return 2;
        }
    }

    private class Proposition {
        private String enonce;
        private int generalRank;

        public Proposition(String enonce) {
            this.enonce = enonce;
            generalRank = 0;
        }

        public void setGeneralRank(int generalRank) {
            this.generalRank = generalRank;
        }
    }
}
