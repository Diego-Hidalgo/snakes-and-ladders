package model;

public class Score {

    private Player player;
    private int score;
    private String parameters;

    private Score parent;
    private Score right;
    private Score left;

    public Score(Player player, int score, String parameters) {
        this.player = player;
        this.score = score;
        this.parameters = parameters;
    }//End Constructor

    public void setPlayer(Player player) {
        this.player = player;
    }//End setPlayer

    public Player getPlayer() {
        return player;
    }//End getPlayer

    public void setScore(int score) {
        this.score = score;
    }//End setScore

    public int getScore() {
        return score;
    }//End getScore

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }//End setParameters

    public String getParameters() {
        return parameters;
    }//End getParameters

    public void setParent(Score parent) {
        this.parent = parent;
    }//End setParent

    public Score getParent() {
        return parent;
    }//End getParent

    public void setRight(Score right) {
        this.right = right;
    }//End setRight

    public Score getRight() {
        return right;
    }//End getRight

    public void setLeft(Score left) {
        this.left = left;
    }//End setLeft

    public Score getLeft() {
        return left;
    }//End getLeft

    public String toString() {
        String msg = "Nickname: " + player.getName() + "\n";
        msg += "Puntaje: " + getScore() + "\n";
        msg += "Parametros de juego: " + getParameters() + "\n";
        return msg;
    }//End toString

}//End Score Class