/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package baseballdriver;

/**
 * A class to represent non-pitcher position players. You must
 * <ol>
 * <li> define private data members for the additional attributes that are
 * specific to non-Pitcher players, essentially additional hitting
 * statistics</li>
 * <li> define a public constructor to take values for all the inherited and
 * local data members, which sets their data members' values to the parameter
 * values</li>
 * <li> define public getters for the data members to be used in calculating
 * aggregate statistics for the the team's hitting stats</li>
 * <li> define a public toString method that produces a String in the format
 * needed for the lookup method of Team <br>
 * <pre>n is a position named name and has statistics
 *    PA: k  BB: m  SO: p  H: q  AB: r  BI: s  HR: t HBP: u BA: x  OBP: y </pre> which is
 * discussed in the program assignment. The first 4 items come from the super
 * class toString()</li>
 * </ol>
 * If you do extra credits, you may need to define more methods, but all data
 * members must be private, so only object instances can directly access them.
 *
 * @author dbriggs
 */
public class PositionPlayer extends Player {

    // Class properties
    int atBats;
    int rbi;
    int homeRuns;
    int hitByPitch;
    double battingAverage;
    double onBasePercentage;

    /**
     * Parameterize constructor for the PositionPlayerClass
     *
     * @param playerNumber
     * @param playerName
     * @param playerPosition
     * @param throwsRightHanded
     * @param plateAppearances
     * @param walks
     * @param strikeouts
     * @param hits
     * @param atBats
     * @param rbi
     * @param homeRuns
     * @param hitByPitch
     */
    public PositionPlayer(int playerNumber, String playerName, int playerPosition,
            boolean throwsRightHanded, int plateAppearances, int walks,
            int strikeouts, int hits, int atBats, int rbi, int homeRuns,
            int hitByPitch) {
        super(playerNumber, playerName, playerPosition, throwsRightHanded,
                plateAppearances, walks, strikeouts, hits);
        this.atBats = atBats;
        this.rbi = rbi;
        this.homeRuns = homeRuns;
        this.hitByPitch = hitByPitch;
        //Method calls
        calcBattingAverage();
        calcOnBasePercentage();
    }

    /**
     * Calculate a players batting average
     */
    public void calcBattingAverage() {
        if (atBats > 0) {
            battingAverage = (double) hits / atBats;
        } else {
            battingAverage = 0;
        }
    }

    /**
     * Calculate a players on base percentage
     */
    public void calcOnBasePercentage() {
        if (plateAppearances > 0) {
            onBasePercentage = (double) (hits + walks + hitByPitch)
                    / plateAppearances;
        } else {
            onBasePercentage = 0;
        }
    }

    /**
     * Access for a players at bats
     *
     * @return number of at bats
     */
    public int getAtBats() {
        return atBats;
    }

    /**
     * Access for a players runs batted in
     *
     * @return number of player runs batted in
     */
    public int getRbi() {
        return rbi;
    }

    /**
     * Access for a players home runs
     *
     * @return number of player home runs
     */
    public int getHomeRuns() {
        return homeRuns;
    }

    /**
     * Access to a players hit by pitch count
     *
     * @return number of times player has been hit by pitch
     */
    public int getHitByPitch() {
        return hitByPitch;
    }

    /**
     * Access for a players batting average
     *
     * @return players batting average
     */
    public double getBattingAverage() {
        return battingAverage;
    }

    /**
     * Access to a players on base percentage
     *
     * @return players on base percentage
     */
    public double getOnBasePercentage() {
        return onBasePercentage;
    }

    /**
     * Method formats our method into an easy to read string PA: k BB: m SO: p
     * H: q AB: r BI: s HR: t HBP: u BA: x OBP: y format
     *
     * @return a formatted string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append(String.format("  AB: %d  RBI: %d  HR: %d  HBP: %d  "
                + "BA: %.3f OBP: %.3f",
                atBats, rbi, homeRuns, hitByPitch,
                battingAverage, onBasePercentage));
        return str.toString();
    }

    /**
     * Unit test for PositionPlayer - creates a PositionPlayer by calling the
     * constructor and then prints it out. There is no need to comment this out!
     * * Do NOT Change. Leave it at the bottom of the file!
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        Player posPlayer = new PositionPlayer(12, "Bob", 7,
                true, 12, 2, 3, 5, 12, 3, 4, 0);
        // test toString
        System.out.println((PositionPlayer) posPlayer);
        // test accessors for Player class
        System.out.println(posPlayer.getPlayerName() + " is a "
                + posPlayer.getPositionInWords());
        Player p = new PositionPlayer(26, "Brock", 4, true, 1443, 33, 83,
                422, 454, 274, 50, 2);
        System.out.println((PositionPlayer) p);
    }
    /* 
        Expeced output from a unit test run:
        PA: 12 BB: 2 SO: 3 H: 5 AB: 12 BI: 3 HR: 4 HBP: 0 BA: 0.42 OBP: 0.58
     */
}
