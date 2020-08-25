package jle.codetest.mvp.models;

import jle.codetest.mvp.exception.MostValuablePlayerException;

public class HandballPlayer extends Player{

    private int goalsMade;
    private int goalsReceived;
    private int scoredPoints;

    public HandballPlayer(String name, String nickname, int number, String teamName,
                          String position, int goalsMade, int goalsReceived) {
        super(name, nickname, number, teamName, position);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(int goalsMade) {
        this.goalsMade = goalsMade;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    @Override
    /**
     * This function return the rating points in a handball match depending on player position.
     */
    public int calculateMatchPoints() throws MostValuablePlayerException {
        if (this.getPosition().equals("G")) {
            return (50 + this.goalsMade*5 - this.goalsReceived*2);
        } else if (this.getPosition().equals("F")) {
            return (20 + this.goalsMade - this.goalsReceived);
        } else {
            throw new MostValuablePlayerException(DATA_NOT_VALID_MSG);
        }
    }
}
