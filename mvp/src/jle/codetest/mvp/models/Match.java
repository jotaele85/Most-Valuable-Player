package jle.codetest.mvp.models;

import java.util.List;
import java.util.Map;

/**
 * This interface describes the behaviour for a match.
 */
public interface Match {

    /**
     * Constant to determine the number of extra points that players receive when the team wins the match.
     */
    int WIN_POINTS = 10;

    /**
     * This method receives the file content and map the information to the object with the proper attributes.
     * @param fileContent the {@Link List<String>} players information for the match.
     */
    void processPlayerStats(List<String> fileContent);

    /**
     * This function returns the name of the winner team.
     */
    String winnerTeam();

    /**
     * Returns a map collection with nicknames as keys and points as values.
     * @return the {@Link Map<String, Integer>}
     */
    Map<String, Integer> getPlayerStats();
}
