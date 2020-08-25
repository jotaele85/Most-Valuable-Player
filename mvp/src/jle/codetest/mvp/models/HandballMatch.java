package jle.codetest.mvp.models;

import jle.codetest.mvp.exception.MostValuablePlayerException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class HandballMatch implements Match {

    Set<HandballPlayer> playerSet;

    public HandballMatch() {
        this.playerSet = new HashSet<HandballPlayer>();
    }

    @Override
    /**
     * This method receives a list of strings and map the information into a handballPlayer.
     * It stores the player in the set playerSet (instance variable).
     * @param fileContent the {@Link List<String>} players information for the match.
     *                    First line contains the type of sport and it is ignored.
     */
    public void processPlayerStats(List<String> fileContent) {
        String name, nickname, teamName, position, number, goalsMade, goalsReceived;
        HandballPlayer hPlayer;

        for (int i=1; i<fileContent.size(); i++) {
            StringTokenizer tokenizer = new StringTokenizer(fileContent.get(i), ";");
            while (tokenizer.hasMoreElements()) {
                name = tokenizer.nextToken();
                nickname = tokenizer.nextToken();
                number = tokenizer.nextToken();
                teamName = tokenizer.nextToken();
                position = tokenizer.nextToken();
                goalsMade = tokenizer.nextToken();
                goalsReceived = tokenizer.nextToken();
                hPlayer = new HandballPlayer(name, nickname, Integer.parseInt(number), teamName,
                        position, Integer.parseInt(goalsMade), Integer.parseInt(goalsReceived));
                playerSet.add(hPlayer);
            }
        }
    }

    @Override
    /**
     * This function calculates the winner team of the match. First split the playerSet by teamName calculating the
     * total goals made of the team and return the name of the team with the highest value.
     */
    public String winnerTeam() {
        Map<String, Integer> pointsPerTeam = playerSet.stream()
                .collect(groupingBy(HandballPlayer::getTeamName, summingInt(HandballPlayer::getGoalsMade)));

        List<String> teamsNames = pointsPerTeam.keySet().stream().collect(Collectors.toList());

        if (pointsPerTeam.get(teamsNames.get(0)) > pointsPerTeam.get(teamsNames.get(1))) {
            return teamsNames.get(0);
        } else {
            return teamsNames.get(1);
        }
    }

    @Override
    /**
     * This function calculates the points for each player in the match and returns a map with the nickname as key and
     * the total amount of points as value.
     */
    public Map<String, Integer> getPlayerStats() {

        Map<String, Integer> result = new HashMap<String, Integer>();
        int playerPoints;

        try {
            for (HandballPlayer player : playerSet) {
                playerPoints = player.calculateMatchPoints();
                if (player.getTeamName().equals(this.winnerTeam())) {
                    result.put(player.getNickname(), playerPoints+WIN_POINTS);
                } else {
                    result.put(player.getNickname(), playerPoints);
                }
            }
        } catch (MostValuablePlayerException e) {
            e.printStackTrace();
        }
        return result;
    }
}
