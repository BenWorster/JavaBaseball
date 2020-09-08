/***********************************************************************
 * REVISION HISTORY (Newest First)
 ***********************************************************************
 * 08/04/2016 - Anne Applin - formatting only
 * 2014 - David Briggs - original starting code and comments
 ***********************************************************************/ 
package baseballdriver;
/**
 * The super class for the Pitcher and PositionPlayer classes that unifies 
 * them.
 * It implements the Comparable interface so that a Team's players can be sort
 * by number. 
 * You must
 * <ol>
 * <li> define data members for the common data(always make data members  for a 
 *     super class protected)</li>
 * <li> define a constructor that initializes all data members</li>
 * <li> define getters for the data members that you need to produce the program
 *    output</li>
 * <li> a public toString method that returns a String in the format:<br>
 *    <pre>PA: k BB: m SO: p H: q</pre><br>
 *    where k, m, p, and q are the values for plate appearances, walks, 
 *    strike-outs, and hits.</li>
 * <li> IF YOU DO THE FIRST EXTRA CREDIT
 *    define protected methods for the updateable data members(that is, 
 *    for counts that can change; you can assume no players are traded, so 
 *    name and number and position, and throwsRightHanded will not change)
 *    the update methods should ADD to the existing values.</li>
 * </ol>
 * All constructors and methods should be declared protected(or private, if 
 * they are auxiliary).
 * @author dbriggs
 */
public abstract class Player implements Comparable<Player>{
    // class properties should be protected 
    protected int playerNumber;
    protected String playerName;
    protected int playerPosition;
    protected boolean throwsRightHanded;
    protected int plateAppearances;
    protected int walks;
    protected int strikeouts;
    protected int hits;

    /**
     * Parameterized constructor for the Player class.
     * @param playerNumber
     * @param playerName
     * @param playerPosition
     * @param throwsRightHanded
     * @param plateAppearances
     * @param walks
     * @param strikeouts
     * @param hits 
     */
    protected Player(int playerNumber, String playerName, int playerPosition,
            boolean throwsRightHanded, int plateAppearances, int walks, int strikeouts, int hits) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.throwsRightHanded = throwsRightHanded;
        this.plateAppearances = plateAppearances;
        this.walks = walks;
        this.strikeouts = strikeouts;
        this.hits = hits;
    }

    /**
     * Access to the player number
     * @return player number
     */
    protected int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Access to the player name
     * @return player name
     */
    protected String getPlayerName() {
        return playerName;
    }

    /**
     * Access to the player position
     * @return player position
     */
    protected int getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Access to whether the player is right or left handed
     * @return which hand the player throws with
     */
    protected boolean isThrowsRightHanded() {
        return throwsRightHanded;
    }

    /**
     * Access to the players plate appearances
     * @return number of plate appearances
     */
    protected int getPlateAppearances() {
        return plateAppearances;
    }

    /**
     * Access to number of player walks
     * @return number of walks
     */
    protected int getWalks() {
        return walks;
    }

    /**
     * Access to number of player strike outs
     * @return number of strike outs
     */
    protected int getStrikeouts() {
        return strikeouts;
    }

    /**
     * Access to the number of player hits
     * @return number of hits
     */
    protected int getHits() {
        return hits;
    }
    
    /**
     * Changes the player position from a number to a string
     * @return player position as a string
     */
    public String getPositionInWords(){
        //Variables
        String positionInWords = "";
        //If statements to turn position into a string
        if (playerPosition == 1){
            positionInWords = "Pitcher";
        } else if (playerPosition == 2){
            positionInWords = "Catcher";
         }else if (playerPosition == 3){
            positionInWords = "First Baseman";
        } else if (playerPosition == 4){
            positionInWords = "Second Baseman";
        } else if (playerPosition == 5){
            positionInWords = "Third Baseman";
        } else if (playerPosition == 6){
            positionInWords = "Shortstop";
        } else if (playerPosition == 7){
            positionInWords = "Left Fielder";
        } else if (playerPosition == 8){
            positionInWords = "Center Fielder";
        } else if (playerPosition == 9){
            positionInWords = "Right Fielder";
        }
        
        return positionInWords;
    }
    
    /**
     * Method formats our method into an easy to read string
     * @return a formatted string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("PA: %d  BB: %d  SO: %d  H: %d",
        plateAppearances, walks, strikeouts, hits));
        
        return str.toString();
    }

    /**
     * compares two Players by number.  Do not change!
     * @param that a Player object
     * @return an integer value < 0 , 0, or > 0
     */
    @Override
    public int compareTo(Player that){
        return this.playerNumber - that.playerNumber;
    }
    
}
