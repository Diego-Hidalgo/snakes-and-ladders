package model;

public class Square {

    private int row;
    private int column;
    private String snake;
    private String ladder;
    private int squareNumber;
    private Square next;
    private Square prev;
    private Square up;
    private Square down;

    public Square(int row, int column,int squareNumber) {
        this.row = row;
        this.column = column;
        this.squareNumber = squareNumber;
    }//End constructor

    public void setRow(int row) {
        this.row = row;
    }//End setRow

    public int getRow() {
        return row;
    }//End getRow

    public void setColumn(int column) {
        this.column = column;
    }//End setColumn

    public int getColumn() {
        return column;
    }//End getColumn

    public void setSnake(String snake) {
        this.snake = snake;
    }//End setSnake

    public String getSnake() {
        return snake;
    }//End getSnake

    public void setLadder(String ladder) {
        this.ladder = ladder;
    }//End setLadder

    public String getLadder() {
        return ladder;
    }//End getLadder

    public void setSquareNumber(int n){
      squareNumber = n;
    }//End setSquareNumber

    public int getSquareNumber(){
      return squareNumber;
    }//End getSquareNumber

    public void setNext(Square next) {
        this.next = next;
    }//End setNext

    public Square getNext() {
        return next;
    }//End getNext

    public void setPrev(Square prev) {
        this.prev = prev;
    }//End setPrev

    public Square getPrev() {
        return prev;
    }//End getPrev

    public void setUp(Square up) {
        this.up = up;
    }//End setUp

    public Square getUp() {
        return up;
    }//End getUp

    public void setDown(Square down) {
        this.down = down;
    }//End setDown

    public Square getDown() {
        return down;
    }//End getDown
    public String toString(){
      return "[]";
    }
}//End Square class
