package model;

import java.io.Serializable;

public class Square implements Serializable {

	private static final long serialVersionUID = 1L;
	private int row;
    private int column;
    private String snake;
    private String ladder;
    private String playerInSquare;
    private Player currentPlayers;
    private int squareNumber;
    //Snake
    private Square snakeHead;
    private Square snakeTail;
    //Ladder
    private Square ladderTop;
    private Square ladderBot;
    //Board
    private Square next;
    private Square prev;
    private Square up;
    private Square down;

    /**
     * Constructor of the Square class. <br>
     *     <b>pre:</b> the parameters are initialized. <br>
     *     <b>post:</b> a new Square object has been created. <br>
     * @param row the row where the square is placed in the board. row > 0
     * @param column the column where the square is placed in the board. column > 0
     * @param squareNumber the number of the squared in the board. It's given by row * column.
     */
    public Square(int row, int column,int squareNumber) {
        this.row = row;
        this.column = column;
        this.squareNumber = squareNumber;
    }//End constructor

    /**
     * changes the row where the square is located in the board. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the row has been changed. <br>
     * @param row the new row where the square will be located.
     */
    public void setRow(int row) {
        this.row = row;
    }//End setRow

    /**
     * returns the row where the square is located in the board.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the row of the square in the board. <br>
     */
    public int getRow() {
        return row;
    }//End getRow

    /**
     * changes the column where the square is located in the board.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the column has been changed. <br>
     * @param column the new column where the square will be located.
     */
    public void setColumn(int column) {
        this.column = column;
    }//End setColumn

    /**
     * returns the column where the square is located in the board.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the column of the square in the board. <br>
     */
    public int getColumn() {
        return column;
    }//End getColumn

    /**
     * changes the symbol of the snake in the square. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the snake has been changed. <br>
     * @param snake the new symbol of the snake.
     */
    public void setSnake(String snake) {
        this.snake = snake;
    }//End setSnake

    /**
     * returns the symbol of the snake in the square. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the snake in the square. <br>
     */
    public String getSnake() {
        return snake;
    }//End getSnake

    /**
     * changes the symbol of the ladder in the square. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the ladder has been changed. <br>
     * @param ladder the new symbol of the ladder in the square.
     */
    public void setLadder(String ladder) {
        this.ladder = ladder;
    }//End setLadder

    /**
     * returns the symbol of the ladder in the square. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the ladder in the square.
     */
    public String getLadder() {
        return ladder;
    }//End getLadder

    /**
     * changes the number of the square in the board. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the number of the square in the board has been changed. <br>
     * @param n the new number of the square in the board.
     */
    public void setSquareNumber(int n){
      squareNumber = n;
    }//End setSquareNumber

    /**
     * returns the current number of the square in the board.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the number of the square in the board. <br>
     */
    public int getSquareNumber(){
      return squareNumber;
    }//End getSquareNumber

    /**
     * changes the next element of the current object in the Square's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the next element of the list has been changed. <br>
     * @param next the new next element in the list. <br>
     */
    public void setNext(Square next) {
        this.next = next;
    }//End setNext

    /**
     * returns the next element of the current object in the Square's linked lost.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the next element in the list. <br>
     */
    public Square getNext() {
        return next;
    }//End getNext

    /**
     * changes the previous element of the current object in the Square's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the previous element of the list has been changed. <br>
     * @param prev the new previous element in the list. <br>
     */
    public void setPrev(Square prev) {
        this.prev = prev;
    }//End setPrev

    /**
     * returns the previous element of the current object in the Square's linked lost.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the previous element in the list. <br>
     */
    public Square getPrev() {
        return prev;
    }//End getPrev

    /**
     * changes the element above the current object in the Square's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the element above in the linked list has been changed. <br>
     * @param up the new element that will be above the current object.
     */
    public void setUp(Square up) {
        this.up = up;
    }//End setUp

    /**
     * returns the element above the current object in the Square's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the element above the current object in the linked list. <br>
     */
    public Square getUp() {
        return up;
    }//End getUp

    /**
     * changes the element under the current object in the Square's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the element under in the linked list has been changed. <br>
     * @param down the new element that will be under the current object.
     */
    public void setDown(Square down) {
        this.down = down;
    }//End setDown

    /**
     * returns the element that is under the current Square in the linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the element under the current object in the linked list. <br>
     */
    public Square getDown() {
        return down;
    }//End getDown

    /**
     * Adds a new player that is placed on the current Square to the linked list of player<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new player has been added to the list of players of the current Square. <br>
     * @param p the new player that is placed on this Square.
     */
    public void addCurrentsPlayers(Player p){
      if(currentPlayers == null){
        currentPlayers = p;
      }else{
        addCurrentsPlayers(currentPlayers,p);
      }
    }//End addCurrentsPlayers

    /**
     * Adds a new player that is placed on the current Square to the linked list of players. Its called when the list is not empty.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new player has been added to the list of players of the curren Square. <br>
     * @param current the current player in the list. The new player will be added next to this.
     * @param toAdd the new player that is placed on this Square and will be added.
     */
    private void addCurrentsPlayers(Player current,Player toAdd){
      if(current.getSquareNextPartner() == null){
        current.setSquareNextPartner(toAdd);
        toAdd.setSquarePrevPartner(current);
      }else
        addCurrentsPlayers(current.getSquareNextPartner(),toAdd);
    }//End addCurrentsPlayers

    /**
     * removes a specific player from the current Square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player has been removed from the Square. <br>
     * @param toRemove the player that will be removed from the Square.
     */
    public void removePlayer(Player toRemove){
      if(currentPlayers == toRemove){
        currentPlayers = currentPlayers.getSquareNextPartner();
        if(currentPlayers != null){
            toRemove.setSquareNextPartner(null);
        }
      }else if(currentPlayers != null){
          removePlayer(currentPlayers.getSquareNextPartner(),toRemove);
      }
    }//End removeCurrentPlayer

    /**
     * removes a specific player from the current Square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player has been removed from the Square. <br>
     * @param current the current player that is being used as reference to find the player to remove.
     * @param toRemove the player that will be removed from the Square.
     */
    private void removePlayer(Player current, Player toRemove){
      if(current != null){
        if(current == toRemove){
          Player nextP = current.getSquareNextPartner();
          Player prevP = current.getSquarePrevPartner();
          if(prevP != null)
            prevP.setSquareNextPartner(current.getSquareNextPartner());
          if(nextP != null)
            nextP.setSquarePrevPartner(current.getSquarePrevPartner());
          current.setSquareNextPartner(null);
          current.setSquarePrevPartner(null);
        }else
          removePlayer(current.getSquareNextPartner(),toRemove);
      }//End if
    }//End removePlayer

    /**
     * calls the method that puts the symbols of the players in a String and returns it.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> returns the symbols of the current players in a String. <br>
     */
    public String getCurrentPlayers(){
      playerInSquare = new String();
      currentPlayers(currentPlayers);
      return playerInSquare;
    }//End getCurrentsPlayers

    /**
     * puts the symbols of the current players in the square in a String.<br>
     *     <b>pre:</b> the variable where the symbols will be putted has been initialized. <br>
     *     <b>post:</b> puts the symbols of the current players in a String. <br>
     * @param current
     */
    private void currentPlayers(Player current){
      if(current != null){
        playerInSquare += current.getSymbol();
        currentPlayers(current.getSquareNextPartner());
      }//End if
    }//End currentPlayers

    /**
     * changes the end (head) of a snake represented by a Square object.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the head of the snake has been changed. <br>
     * @param head the new head of the snake.
     */
    public void setSnakeHead(Square head){
      snakeHead = head;
    }//End setSnakeHead

    /**
     * changes the start (tail) of a snake represented by a Square object.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the tail of the snake has been changed. <br>
     * @param tail the new tail of the snake.
     */
    public void setSnakeTail(Square tail){
      snakeTail = tail;
    }//End setSnakeTail

    /**
     * returns the end of a snake (head) represented by a Square object. <br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the head of the snake. <br>
     */
    public Square getSnakeHead(){
      return snakeHead;
    }//End getSnakeHead

    /**
     * returns the start of a snake (tail) represented by a Square object.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the tail of the snake. <br>
     */
    public Square getSnakeTail(){
      return snakeTail;
    }//End getSnakeTail

    /**
     * changes the end of a ladder (top) represented by a Square object. <br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the top of the ladder has been changed. <br>
     * @param top the new top of the ladder.
     */
    public void setLadderTop(Square top){
      ladderTop = top;
    }//End setLadderTop

    /**
     * changes the start of a ladder (bottom) represented by a Square object.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/>
     * @param bot the new bottom of the ladder.
     */
    public void setLadderBot(Square bot){
      ladderBot = bot;
    }//End setLadderBot

    /**
     * returns the top of the ladder in the current Square.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the top of the ladder of the current Square. <br>
     */
    public Square getLadderTop(){
      return ladderTop;
    }//End getLadderTop

    /**
     * returns the bottom of the ladder in the current Square.<br>
     *     <b>pre</b> the object that calls the method is not null. <br>
     *     <b>post:<b/> the bottom of the ladder of the current Square. <br>
     */
    public Square getLadderBot(){
      return ladderBot;
    }//End getLadderBop

    /**
     * puts the information of the Square object into a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the information of the Square contained in a Stirng. <br>
     */
    public String toString(){
      return "["+squareNumber+"]";
    }//End toString

}//End Square Class
