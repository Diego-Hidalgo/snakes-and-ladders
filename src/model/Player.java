package model;

public class Player {
    
    private String name;
    private String symbol;
    private int score;

    private Player parent;
    private Player left;
    private Player right;

    public Player(String symbol) {
        this.symbol = symbol;
    }//End constructor

    public void setName(String name) {
        this.name = name;
    }//End setName

    public String getName() {
        return name;
    }//End getName

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }//End setSymbol

    public String getSymbol() {
        return symbol;
    }//End getSymbol

    public void setScore(int score) {
        this.score = score;
    }//End setScore

    public int getScore() {
        return score;
    }//End getScore

    public void setParent(Player parent) {
        this.parent = parent;
    }//End setParent

    public Player getParent() {
        return parent;
    }//End getParent

    public void setLeft(Player left) {
        this.left = left;
    }//End setLeft

    public Player getLeft() {
        return left;
    }//End getleft

    public void setRight(Player right) {
        this.right = right;
    }//End setRight

    public Player getRight() {
        return right;
    }//End getRight

}//End Player class