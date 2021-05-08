package model;

public class Score {

    private Player player;
    private int score;
    private String parameters;

    private Score parent;
    private Score right;
    private Score left;

    /**
     * Constructor of the Score class.<br>
     *     <b>pre:</b> the parameters are initialized. <br>
     *     <b>post:</b> a new Score object has been created. <br>
     * @param player the player with the current score.
     * @param score the value of the score.
     * @param parameters the game parameters that were used to play.
     */
    public Score(Player player, int score, String parameters) {
        this.player = player;
        this.score = score;
        this.parameters = parameters;
    }//End Constructor

    /**
     * changes the player that got this score.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player has been changed. <br>
     * @param player the new player with this score.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }//End setPlayer

    /**
     * returns the player that got this score.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player with this score. <br>
     */
    public Player getPlayer() {
        return player;
    }//End getPlayer

    /**
     * changes the value of the score. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the score has been changed. <br>
     * @param score the new value of the score.
     */
    public void setScore(int score) {
        this.score = score;
    }//End setScore

    /**
     * returns the value of the score.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the value of the score. <br>
     */
    public int getScore() {
        return score;
    }//End getScore

    /**
     * changes the game parameters of the played game.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the game parameters of the game have been changed. <br>
     * @param parameters the new parameters for the played game.
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }//End setParameters

    /**
     * returns the game parameters of the played game.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the game parameters of the played game. <br>
     */
    public String getParameters() {
        return parameters;
    }//End getParameters

    /**
     * changes the parent of the current node in the binary search tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the parent of the current node has been changed. <br>
     * @param parent the new parent of the current node.
     */
    public void setParent(Score parent) {
        this.parent = parent;
    }//End setParent

    /**
     * returns the parent of the current node in the binary search tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the parent of the current node. <br>
     */
    public Score getParent() {
        return parent;
    }//End getParent

    /**
     * changes the right child of the current node in the binary search tree. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the right child of the current node has been changed. <br>
     * @param right the new right child for the current node.
     */
    public void setRight(Score right) {
        this.right = right;
    }//End setRight

    /**
     * returns the right child of the current node in the binary search tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the right child of the current node. <br>
     */
    public Score getRight() {
        return right;
    }//End getRight

    /**
     * Changes the left child of the current node in the binary search tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the left child of the node has been changed. <br>
     * @param left the new left child for the current node.
     */
    public void setLeft(Score left) {
        this.left = left;
    }//End setLeft

    /**
     * returns the left child of the current node in the binary search tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the left child of the current node. <br>
     */
    public Score getLeft() {
        return left;
    }//End getLeft

    /**
     * puts the information of the object into a String. <br>
     *     <b>pre:</b> the player is not null. The object that calls the method is not null.<br>
     *     <b>post:</b> returns the name of the player, the score and the game parameters in a String. <br>
     */
    public String toString() {
        String msg = "\nNickname: " + player.getName() + "\n";
        msg += "Puntaje: " + getScore() + "\n";
        msg += "Parametros de juego: " + getParameters() + "\n\n";
        return msg;
    }//End toString

}//End Score Class