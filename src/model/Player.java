package model;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
    private String symbol;
    private int movements;
    private Square position;
    //Players
    private Player next;
    private Player prev;
    //Square partners data
    private Player squareNextPartner;
    private Player squarePrevPartner;

    /**
     * Constructor of the Player class.<br>
     *     <b>pre:</b> parameters are initialized. <br>
     *     <b>post:</b> a new Player object has been created. <br>
     * @param symbol the current symbol used by the player.
     * @param position the current position of the player on the board.
     */
    public Player(String symbol, Square position) {
        this.symbol = symbol;
        this.position = position;
    }//End constructor

    /**
     * changes the name of the player.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the name of the player has been changed. <br>
     * @param name the new name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }//End setName

    /**
     * returns the name of the player. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the current name of the player. <br>
     */
    public String getName() {
        return name;
    }//End getName

    /**
     * changes the symbol of the player. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the symbol has been changed. <br>
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }//End setSymbol

    /**
     * returns the symbol of the player. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the current symbol of the player. <br>
     */
    public String getSymbol() {
        return symbol;
    }//End getSymbol

    /**
     * changes the amount of movements made by the player represented by the times the player has rolled the dice. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the amount of movements has been changed. <br>
     * @param movements the new amount of movements made by the player. movements >= 0.
     */
    public void setMovements(int movements) {
        this.movements = movements;
    }//End setScore

    /**
     * returns the amount of movements made by the player. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the current amount of movements made by the player. <br>
     */
    public int getMovements() {
        return movements;
    }//End getScore

    /**
     * changes the previous element of the current object in the player's linked list. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the previous element in the list has been changed. <br>
     * @param prev the new previous element in the linked list.
     */
    public void setPrev(Player prev) {
        this.prev = prev;
    }//End setLeft

    /**
     * returns the previous element of the current object in the player's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the previous element in the list. <br>
     */
    public Player getPrev() {
        return prev;
    }//End getleft

    /**
     * changes the next element of the current object in the player's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the next element in the list has been changed. <br>
     * @param next the new next element in the linked list. <br>
     */
    public void setNext(Player next) {
        this.next = next;
    }//End setRight

    /**
     * returns the next element of the current object in the player's linked list.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the next element in the list. <br>
     */
    public Player getNext() {
        return next;
    }//End getRight

    /**
     * changes the current square's position of the player in the board. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the position of the player has been changed. <br>
     * @param position the new position of the player in the board.
     */
    public void setPosition(Square position){
      this.position = position;
    }//End setPosition

    /**
     * returns the current position of the player in the board represented by a square object.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the square where the player is located in the board. <br>
     */
    public Square getPosition(){
      return position;
    }//End getPosition

    /**
     * changes the player that is next to the current player in the square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the next partner has been changed. <br>
     * @param next the new player that is placed next to the current player.
     */
    public void setSquareNextPartner(Player next){
      squareNextPartner = next;
    }//End setSquareNextPartner

    /**
     * returns the player that is next to the current player in the square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player next to the current player in the square. <br>
     */
    public Player getSquareNextPartner(){
      return squareNextPartner;
    }//End setSquareNextPartner

    /**
     * changes the player that is previous to the current player in the square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the previous partner has been changed. <br>
     * @param prev the new player that is placed previous to the current player.
     */
    public void setSquarePrevPartner(Player prev){
      squarePrevPartner = prev;
    }//End setSquareNextPartner

    /**
     * returns the player that is previous to the current player in the square.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player previous to the current player in the square. <br>
     */
    public Player getSquarePrevPartner(){
      return squarePrevPartner;
    }//End setSquareNextPartner

    /**
     * puts the information of the player in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the information of the player contained in a String. <br>
     */
    public String toString(){
      return symbol;
    }//End toString

}//End Player Class
