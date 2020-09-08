/***********************************************************************
 * REVISION HISTORY (Newest First)
 *********************************************************************** 
 * 08/04/2016 - Anne Applin - formatting and JavaDoc
 * 2014 - David Briggs - original starting code and comments
 ***********************************************************************/ 
package baseballdriver;

/**
 * A class to represent the pitchers on a team.
 * You must
 * <ol>
 * <li> define private data members for the additional
 *    attributes that are specific to pitchers,
 *    essentially a few additional statistics </li>
 * <li>  define a public constructor to take values for all the
 *    inherited and local data members, which sets their
 *    data members' values to the parameter values</li>
 * <li>  define public getters for the count data members to be 
 *    used in calculating aggregate statistics for the the team's
 *    pitching</li>
 * <li> define a public toString method that produces a
 *    String in the format needed for the lookup method of
 *    Team </li>
 * </ol>
 *<pre>    n is a pitcher named name and has statistics
 *    PA: k  BB: m  SO: p  H: q  IP: r  ER: s  ERA: x  WHIP: y</pre>
 *    which is discussed in the program assignment. The first 4 items 
 *    come from the super class toString()
 * If you do extra credits, you may need to define more methods, but
 * all data members must be private, so only object instances can
 * directly access them.
 * 
 * @author dbriggs
 */
public class Pitcher extends Player{
    // Class properties
    int inningsPitched;
    int earnedRuns;
    double era;
    double whip;
    Player p;

    /**
     * Constructor for the Player class
     * @param playerNumber
     * @param playerName
     * @param playerPosition
     * @param throwsRightHanded
     * @param plateAppearances
     * @param walks
     * @param strikeouts
     * @param hits
     * @param inningsPitched
     * @param earnedRuns 
     */
    public Pitcher (int playerNumber, String playerName, int playerPosition,
            boolean throwsRightHanded, int plateAppearances, int walks,
            int strikeouts, int hits, int inningsPitched, int earnedRuns){
        super (playerNumber, playerName, playerPosition, throwsRightHanded, 
                plateAppearances, walks, strikeouts, hits);
        this.inningsPitched = inningsPitched;
        this.earnedRuns = earnedRuns;
        //Method calls
        calcEra();
        calcWhip();
    }
    
    /**
     * Method calculates a pitchers earned run average
     */
    public void calcEra(){
        if (inningsPitched > 0){
            era = (double)earnedRuns / inningsPitched * 27;
        }else{
            era = 0;
        }
        
    }
    
    /**
     * Method calculates a pitchers whip
     */
    public void calcWhip(){
        if (inningsPitched > 0){
            whip = (double)(walks + hits) / inningsPitched * 3;
        }else{
            whip = 0;
        }
    }
    
    /**
     * Access for a pitchers innings pitched
     * @return number of innings pitched
     */
    public int getInningsPitched(){
        return inningsPitched;
    }
    
    /**
     * Access for a pitchers earned runs
     * @return number of earned runs
     */
    public int getEarnedRuns(){
        return earnedRuns;
    }
    /**
     * Access for a pitchers era
     * @return pitchers era
     */
    public double getEra(){
        return era;
    }
    
    /**
     * Access for the pitchers whip
     * @return pitchers whip
     */
    public double getWhip(){
        return whip;
    }
    
    /**
     * Method formats our method into an easy to read string
     * PA: k BB: m SO: p H: q IP: r ER: s ERA: x WHIP: y format
     * @return a formatted string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append(String.format("  IP: %d  ER: %d  ERA: %.3f  WHIP:  %.2f", 
                inningsPitched, earnedRuns, era, whip));
        return str.toString();
    }
    
    /**
     * Unit test for Pitcher.  There is no need to comment this out! 
     * Do NOT Change.  Leave it at the bottom of the file!
     * @param args command line args 
     */   
    public static void main (String[] args){
        Pitcher pitcher = new Pitcher(4, "Jim", 1 , 
            true, 12, 2, 3, 5, 12, 3);
        // test toString
        System.out.println(pitcher);
        // test accessors for Player class
        System.out.println(pitcher.getPlayerName() + " is a " 
                          + pitcher.getPositionInWords());
        Player p = new Pitcher(65, "Jonathan", 1, true, 416, 23, 80, 
                111, 259, 32);
        System.out.println((Pitcher)p);
    }
    /* 
        Expeced output from a unit test run:
        PA: 12 BB: 2 SO: 3 H: 5 IP: 12 ER: 3 ERA: 6.750 WHIP: 1.75
    */
}