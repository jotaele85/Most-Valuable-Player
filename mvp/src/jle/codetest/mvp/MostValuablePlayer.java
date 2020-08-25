package jle.codetest.mvp;

import jle.codetest.mvp.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MostValuablePlayer {

    public static void main(String[] args) {

        Map<String, Integer> playerMap = new HashMap<String, Integer>();
        List<Match> matchList = new ArrayList<>();
        String directory;

        try {
            //First we are asking to the user the directory to search files
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the name of directory which cotains the files: ");
            directory = br.readLine();

            //Processing files
            Files.walk(Paths.get(directory))
                    .filter(Files::isRegularFile)
                    .forEach(file -> processFile(file, matchList));

            //Calculating stats for players
            for(Match match : matchList) {
                updatePlayersStats(match.getPlayerStats(), playerMap);
            }

            //Now we just need to find the player(nickname) with highest value in playerMap
            String nickname = getMVP(playerMap);
            System.out.println("The MVP is "+ nickname + ", with " + playerMap.get(nickname) + " points.");

        } catch (IOException ex) {
            System.out.println("Error occurred when reading the directory.");
        }
    }

    /**
     * This method process the file passed as parameter and include all the player information into the matchList.
     * @param file the {@Link Path} information of the players to process.
     * @param matchList the {@Link List<Match>} list with match information.
     */
    private static void processFile(Path file, List<Match> matchList) {

        try {
            List<String> fileLines = Files.readAllLines(file);

            if (!fileLines.isEmpty() && Sports.BASKETBALL.toString().equals(fileLines.get(0))) {
                BasketballMatch bMatch = new BasketballMatch();
                bMatch.processPlayerStats(fileLines);
                matchList.add(bMatch);
            } else if (!fileLines.isEmpty() && Sports.HANDBALL.toString().equals((fileLines.get(0)))) {
                HandballMatch hMatch = new HandballMatch();
                hMatch.processPlayerStats(fileLines);
                matchList.add(hMatch);
            }
        } catch (IOException ex) {
            System.out.println("Error occurred when reading files.");
        }
    }


    /**
     * This method update the stats for players in playerMap with the points received by parameter in playerStats.
     * @param playerStats the {@Link Map<String, Integer>} with the nickname as key and total points for the match as value.
     * @param playerMap the {@Link Map<String, Integer>} with the total stats of all the players.
     */
    private static void updatePlayersStats(Map<String, Integer> playerStats, Map<String, Integer> playerMap) {

        // using for-each loop for iteration over Map.entrySet()
        for(Map.Entry<String, Integer> entry : playerStats.entrySet()) {
            Integer points = playerMap.get(entry.getKey());
            if(points == null) {
                playerMap.put(entry.getKey(), entry.getValue());
            } else {
                playerMap.put(entry.getKey(), points+entry.getValue());
            }
        }
    }

    /**
     * This function returns the key of the highest value in the map.
     * @param map the {@Link Map<String, Integer> with the players nickname as key and the scores as values.
     * @return the {@Link String} with the nickname of the player with highest value.
     */
    private static <String, Integer extends Comparable<Integer>> String getMVP(Map<String, Integer> map) {
        Optional<Map.Entry<String, Integer>> maxEntry = map.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        return maxEntry.get().getKey();
    }
}
