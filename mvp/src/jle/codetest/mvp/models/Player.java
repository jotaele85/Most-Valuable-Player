package jle.codetest.mvp.models;

import jle.codetest.mvp.exception.MostValuablePlayerException;

import java.util.Objects;

public abstract class Player {

    protected static final String DATA_NOT_VALID_MSG = "Data not valid";

    private String name;
    private String nickname;
    private int number;
    private String teamName;
    private String position;

    public Player(String name, String nickname, int number, String teamName, String position) {
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.teamName = teamName;
        this.position = position;
    }

    public abstract int calculateMatchPoints() throws MostValuablePlayerException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    /**
     * Two players are considered equals when they have the same nickname.
     */
    public boolean equals(Object o) {
        return (o instanceof Player) && (this.nickname.equals(((Player) o).getNickname()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
