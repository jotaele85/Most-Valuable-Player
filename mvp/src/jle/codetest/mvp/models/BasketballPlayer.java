package jle.codetest.mvp.models;

import jle.codetest.mvp.exception.MostValuablePlayerException;

public class BasketballPlayer extends Player {

    private int scoredPoints;
    private int rebounds;
    private int assists;

    public BasketballPlayer(String name, String nickname, int number, String teamName,
                            String position, int scoredPoints, int rebounds, int assists) {
        super(name, nickname, number, teamName, position);
        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    public void setScoredPoints(int scoredPoints) {
        this.scoredPoints = scoredPoints;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    /**
     * This function return the rating points in a basketball match depending on player position.
     */
    public int calculateMatchPoints() throws MostValuablePlayerException{
        if (this.getPosition().equals("G")) {
            return (this.scoredPoints*2 + this.rebounds*3 + this.assists*1);
        } else if (this.getPosition().equals("F")) {
            return (this.scoredPoints*2 + this.rebounds*2 + this.assists*2);
        } else if (this.getPosition().equals("C")) {
            return (this.scoredPoints*2 + this.rebounds*1 + this.assists*3);
        } else {
            throw new MostValuablePlayerException(DATA_NOT_VALID_MSG);
        }
    }
}
