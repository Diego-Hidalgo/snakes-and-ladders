package model;

public class Player {

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

    public Player(String symbol, Square position) {
        this.symbol = symbol;
        this.position = position;
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

    public void setMovements(int movements) {
        this.movements = movements;
    }//End setScore

    public int getMovements() {
        return movements;
    }//End getScore

    public void setPrev(Player prev) {
        this.prev = prev;
    }//End setLeft

    public Player getPrev() {
        return prev;
    }//End getleft

    public void setNext(Player next) {
        this.next = next;
    }//End setRight

    public Player getNext() {
        return next;
    }//End getRight

    public void setPosition(Square position){
      this.position = position;
    }//End setPosition

    public Square getPosition(){
      return position;
    }//End getPosition

    public void setSquareNextPartner(Player next){
      squareNextPartner = next;
    }//End setSquareNextPartner

    public Player getSquareNextPartner(){
      return squareNextPartner;
    }//End setSquareNextPartner

    public void setSquarePrevPartner(Player prev){
      squarePrevPartner = prev;
    }//End setSquareNextPartner

    public Player getSquarePrevPartner(){
      return squarePrevPartner;
    }//End setSquareNextPartner

    public String toString(){
      return symbol;
    }

}//End Player Class
