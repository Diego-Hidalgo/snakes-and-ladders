package model;

public class Square {

    private int row;
    private int column;
    private String snake;
    private String ladder;
    private String playerInSquare;
    private Player currentPlayers;
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

    public void addCurrentsPlayers(Player p){
      if(currentPlayers == null){
        currentPlayers = p;
      }else{
        addCurrentsPlayers(currentPlayers,p);
      }
    }//End addCurrentsPlayers

    private void addCurrentsPlayers(Player current,Player toAdd){
      if(current.getSquareNextPartner() == null){
        current.setSquareNextPartner(toAdd);
        toAdd.setSquarePrevPartner(current);
      }else
        addCurrentsPlayers(current.getSquareNextPartner(),toAdd);
    }//End addCurrentsPlayers

    public void removePlayer(Player toRemove){
      if(currentPlayers == toRemove){
        currentPlayers = currentPlayers.getSquareNextPartner();
        if(currentPlayers != null){
            currentPlayers.getSquarePrevPartner().setSquareNextPartner(null);
        }
      }else if(currentPlayers != null){
          removePlayer(currentPlayers.getSquareNextPartner(),toRemove);
      }
    }//End removeCurrentPlayer

    private void removePlayer(Player current, Player toRemove){
      if(current != null){
        if(current == toRemove){
          current.getSquarePrevPartner().setSquareNextPartner(current.getSquareNextPartner());
          current.getSquareNextPartner().setSquarePrevPartner(current.getSquarePrevPartner());
          current.setSquareNextPartner(null);
          current.setSquarePrevPartner(null);
        }else
          removePlayer(current.getSquareNextPartner(),toRemove);
      }//End if
    }//End removePlayer

    public String getCurrentPlayers(){
      playerInSquare = new String();
      currentPlayers(currentPlayers);
      return playerInSquare;
    }//End getCurrentsPlayers

    private void currentPlayers(Player current){
      if(current != null){
        playerInSquare += current.getSymbol();
        currentPlayers(current.getSquareNextPartner());
      }//End if
    }//End currentPlayers

    public String toString(){
      return "[]";
    }

}//End Square class
