/** *********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ********************************************************************** */
package baseballdriver;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * A class to represent a team and its roster. It needs data members for the
 * name of the team, which will be String w/o embedded blanks, for example,
 * RedSox, BlueJay, Yankees, and WhiteSox, and a Java Collection class for the
 * players on the team.
 * <br>
 * You must use a Map member for the players. You can set up the Map to lookup
 * players by number, which is more efficient than iterating over a list of
 * players.
 * <br>
 * You must define
 * <ol>
 * <li> a public constructor that takes a name and a map of Player objects(which
 * will be invoked by the League class constructor).
 * <code>public Team(String tname, TreeMap<Integer, Player> roster)</code> and
 * creates an instance for that team
 *
 * <li> a public method <code>String  lookupPlayer(int n)</code>
 *
 * that returns the appropriate one of the follow two results (assuming t is the
 * name of this team)
 * <br>
 * No player with number n is on the roster for the t.<br>
 * or<br>
 * <pre><stats for that player></pre>
 * <br>
 * The format of the latter is given in the program assignment and is based on
 * whether the player is a pitcher or a position player. You should write the
 * toString method for Pitcher and PositionPlayer to return the appropriate
 * portion of that result. Dynamic dispatch will take care of the rest.</li>
 *
 * <li> a public method <code>String calcPitchingStats()</code>
 * <br>
 * that returns the appropriate choice from the following two results(assuming t
 * is the name of the team)
 * <ol>
 * <li>The t appear to have no pitchers at this time.</li>
 * <li> <code><aggregated pitching stats for t></code></li>
 * </ol>
 * where the format of the latter is discussed in the program assignment.</li>
 *
 * <li> code a public method String calcHittingStats()
 *
 * that returns the appropriate choice from the following two results(assuming t
 * is the name of the team)
 * <ol>
 * <li>The t appear to have no hitters at this time.</li>
 *
 * <li><code><aggregated hitting  stats for t><code><li>
 * </ol>
 * where the format of the latter is discussed in the program assignment.</ol>
 * If you do the extra credits, you will need to define additional methods.
 * Note, all data members should be private, so only the Team object instance
 * can directly access them.
 *
 * @author dbriggs
 */
public class Team {

    //Properties
    String teamName;
    TreeMap<Integer, Player> roster;

    /**
     * Constructor for the team class
     *
     * @param teamName
     * @param roster
     */
    public Team(String teamName, TreeMap<Integer, Player> roster) {
        this.roster = new TreeMap<>();
        this.teamName = teamName;
        this.roster = roster;
        //Method calls

    }

    /**
     * Check to see if a player exists on a team
     *
     * @param playerNum
     * @return
     */
    public String playerLookUp(int playerNum) {
        StringBuilder str = new StringBuilder();
        if (roster.containsKey(playerNum)) {
            Player p = roster.get(playerNum);
            str.append(String.format("The %s's player number %d is a %s "
                    + "named %s and his statistics are:\n", teamName,
                    playerNum, p.getPositionInWords(), p.getPlayerName()));
            str.append(roster.get(playerNum).toString());
        } else {
            str.append("There is no player with the number ")
                    .append(playerNum).append(" on the team ").append(roster);
        }
        return str.toString();
    }

    /**
     * Calculate a teams pitching data
     *
     * @return formatted pitching data of a team
     */
    public String calcPitchingStats() {
        //Local Varaibles
        int totalPitchers = 0;
        int pitcherPlateAppearances = 0;
        int pitcherWalks = 0;
        int pitcherStrikeouts = 0;
        int pitcherHits = 0;
        int totalInningsPitched = 0;
        int totalEarnedRuns = 0;
        double totalEra = 0;
        double totalWhip = 0;
        StringBuilder str = new StringBuilder();
        //Search map
        for (Integer num : roster.keySet()) {
            Player p = roster.get(num);
            if (p instanceof Pitcher){            
                //Update team pitcher stat totals
                totalPitchers++;
                pitcherPlateAppearances += ((Pitcher) p).getPlateAppearances();
                pitcherWalks += ((Pitcher) p).getWalks();
                pitcherStrikeouts += ((Pitcher) p).getStrikeouts();
                pitcherHits += ((Pitcher) p).getHits();
                totalInningsPitched += ((Pitcher) p).getInningsPitched();
                totalEarnedRuns += ((Pitcher) p).getEarnedRuns();
            }
        }
        //Calculate team ERA
        if (totalInningsPitched > 0) {
            totalEra = (double) totalEarnedRuns / totalInningsPitched * 27;
        } else {
            totalEra = 0;
        }
        //Calculate team Whip
        if (totalInningsPitched > 0) {
            totalWhip = (double) (pitcherWalks + pitcherHits)
                    / totalInningsPitched * 3;
        } else {
            totalWhip = 0;
        }
        //Create dummy pitcher to hold the statistics
        Pitcher p = new Pitcher(0, " ", 0, true, pitcherPlateAppearances,
                pitcherWalks, pitcherStrikeouts, pitcherHits,
                totalInningsPitched, totalEarnedRuns);
        System.out.println("There are a total of " + totalPitchers + " Pitchers"
                + " on the " + teamName + " and their aggregated"
                        + " statistics are:");
        return p.toString();
    }

    /**
     * Calculate a teams hitting data
     *
     * @return formatted hitting data of a team
     */
    public String calcHittingStats() {
        //Local Varaibles
        int totalHitters = 0;
        int hitterPlateAppearances = 0;
        int hitterWalks = 0;
        int hitterStrikeouts = 0;
        int hitterHits = 0;
        int totalAtBats = 0;
        int totalRbi = 0;
        int totalHomeRuns = 0;
        int totalHitByPitch = 0;
        double totalBattingAverage;
        double totalOnBasePercentage;
        StringBuilder str = new StringBuilder();
           //Search map
        for (Integer num : roster.keySet()) {
            Player p = roster.get(num);
            if (p instanceof PositionPlayer){
                //Update team hitter stat totals
                totalHitters++;
                hitterPlateAppearances += ((PositionPlayer)p)
                        .getPlateAppearances();
                hitterWalks += ((PositionPlayer)p).getWalks();
                hitterStrikeouts += ((PositionPlayer)p).getStrikeouts();
                hitterHits += ((PositionPlayer)p).getHits();
                totalAtBats += ((PositionPlayer)p).getAtBats();
                totalRbi += ((PositionPlayer)p).getRbi();
                totalHomeRuns += ((PositionPlayer)p).getHomeRuns();
                totalHitByPitch += ((PositionPlayer)p).getHitByPitch();
            }
        }
        //Calculate team batting average
        if (totalAtBats > 0) {
            totalBattingAverage = (double) hitterHits / totalAtBats;
        } else {
            totalBattingAverage = 0;
        }
        //Calculate team on base percentage
        if (hitterPlateAppearances > 0) {
            totalOnBasePercentage = (double) (hitterHits + hitterWalks
                    + totalHitByPitch) / hitterPlateAppearances;
        } else {
            totalOnBasePercentage = 0;
        }
        //Create dummy position player
        PositionPlayer p = new PositionPlayer(0, " ", 0, true,
                hitterPlateAppearances, hitterWalks, hitterStrikeouts,
                hitterHits, totalAtBats, totalRbi, totalHomeRuns, 
                totalHitByPitch);
        System.out.println("There are a total of " + totalHitters + " hitters"
                + " on the " + teamName + " and their aggregated"
                        + " statistics are:");
        return p.toString();
    }

    /**
     * Method formats our method into an easy to read string PA: k BB: m SO: p
     * H: q AB: r BI: s HR: t HBP: u BA: x OBP: y format
     *
     * @return a formatted string
     */
    @Override
    public String toString() {

        return String.format("");
    }

    /**
     * Unit test for Team - Creates a map with two players and calls the Team
     * Constructor There is no need to comment this out! * Do NOT Change. Leave
     * it at the bottom of the file!
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        TreeMap<Integer, Player> players = new TreeMap<>();
        players.put(65, new Pitcher(65, "Jonathan", 1, true, 416, 23, 80,
                111, 259, 32));
        players.put(26, new PositionPlayer(26, "Brock", 4, true, 1443, 33, 83,
                422, 454, 274, 50, 2));
        Team team = new Team("RedSox", players);
        for (Integer num : players.keySet()) {
            System.out.println(players.get(num));
        }
    }
    /*
Expected output of this unit test:
PA: 1443 BB: 33 SO: 83 H: 422 AB: 454 BI: 274 HR: 50 HBP: 2 BA: 0.93 OBP: 0.32
PA: 416 BB: 23 SO: 80 H: 111 IP: 259 ER: 32 ERA: 3.336 WHIP: 1.55
     */
}
