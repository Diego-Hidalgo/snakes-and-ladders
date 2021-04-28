package model;
import java.util.Scanner;

public class Board {

    private Player firstPlayer;
    private Square firstSquare;
    private String board;
    private int columnsAmount;
    private int rowsAmount;
    private String auxRow;
    private String gameParameters;

    public Board() {
      firstSquare = new Square(0,0,1);
    }//End Constructor

    public void setGameParameters(String gameParameters) {
        this.gameParameters = gameParameters;
    }//End setGameParameters

    public String getGameParameters() {
        return gameParameters;
    }//End getGameParameters

    public void receiveGameParameters(int rows, int columns, int snakes, int ladders, String symbols) {
        String params = rows + " " + columns + " " + snakes + " " + ladders + " " + symbols;
        setGameParameters(params);
        createBoard(rows, columns);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    public void receiveGameParameters(int rows, int columns, int snakes, int ladders, int amountPlayers) {
        String availableSymbols = "*!OX%$#+&";
        String symbols = availableSymbols.substring(0, amountPlayers);
        String params = rows + " " + columns + " " + snakes + " " + ladders + " " + symbols;
        setGameParameters(params);
        createBoard(rows, columns);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    public void createBoard(int row,int col) {
      columnsAmount = col;
      rowsAmount = row;
      addColumns(firstSquare);
      addRows(firstSquare);
    }//End createBoard

    private void addColumns(Square current) {
      if(current.getColumn() < (columnsAmount-1)){
        int num = ((current.getRow()+1)%2==0)?(current.getSquareNumber()-1):(current.getSquareNumber()+1);
        Square newCol = new Square(current.getRow(),(current.getColumn()+1),num);
        newCol.setPrev(current);
        current.setNext(newCol);
        if(current.getUp() != null && (current.getUp()).getNext() != null){
          Square up = (current.getUp()).getNext();
          up.setDown(newCol);
          newCol.setUp(up);
        }//End if
        addColumns(newCol);
      }//End if
    }//End addColumnsRight

    private void addRows(Square current) {
      if(current.getRow() < (rowsAmount - 1) ){
        int num = ((current.getRow()+1)%2!=0)?(2*columnsAmount)*((current.getRow()+2)/2):current.getSquareNumber()+1;
        Square newRow = new Square((current.getRow()+1),current.getColumn(),num);
        newRow.setUp(current);
        current.setDown(newRow);
        addColumns(newRow);
        addRows(newRow);
      }//End if
    }//End addRows

    public String getEnumeratedBoard() {
      board = new String();
      auxRow = new String();
      getRows(firstSquare);
      return board;
    }//End getEnumerateBoard

    public void getColumns(Square current) {
      if(current != null){
        auxRow += "["+current.getSquareNumber()+" "+current.getCurrentPlayers()+"]";
        getColumns(current.getNext());
      }//End if
    }//End getColumns

    public void getRows(Square current) {
      if(current != null){
        getColumns(current);
        board = auxRow+"\n"+board;
        auxRow = new String();
        getRows(current.getDown());
      }//End if
    }//End getRows

    public void addAllPlayers(String symbols, int index) {
        if(index < symbols.length()) {
            addPlayer(String.valueOf(symbols.charAt(index)));
            addAllPlayers(symbols, ++index);
        }//End if
    }//End addAllPlayers

    public void addPlayer(String symbol) {
      if(firstPlayer == null){
          firstPlayer = new Player(symbol,firstSquare);
          firstPlayer.setPrev(firstPlayer);
          firstPlayer.setNext(firstPlayer);
          firstSquare.addCurrentsPlayers(firstPlayer);
      } else{
        Player toAdd = new Player(symbol,firstSquare);
        firstPlayer.getPrev().setNext(toAdd);
        toAdd.setPrev(firstPlayer.getPrev());
        firstPlayer.setPrev(toAdd);
        toAdd.setNext(firstPlayer);
        firstSquare.addCurrentsPlayers(toAdd);
      }//End else
    }//End addPlayer

    public void movePlayer(String symbol, int steps) {
      Player toMove = searchPlayer(symbol,firstPlayer);
      move(toMove,steps);
    }//End movePlayer

    private void move(Player player,int steps) {
      if(steps > 0){
        Square nextSquare = ((player.getPosition().getRow() + 1) % 2 != 0)?player.getPosition().getNext():player.getPosition().getPrev();
        Square currentSquare = player.getPosition();
        if(nextSquare != null){
          if( (player.getPosition().getRow() + 1) % 2 == 0){
            player.setPosition(currentSquare.getPrev());
          }else{
            player.setPosition( currentSquare.getNext());
          }//End else
          nextSquare.addCurrentsPlayers(player);
        }else{
          currentSquare.getDown().addCurrentsPlayers(player);
          player.setPosition( currentSquare.getDown());
        } //End else
        currentSquare.removePlayer(player);
        move(player,(--steps));
      }//End if
    }//End move

    public Player getPlayers() {
      return firstPlayer;
    }//End getPlayers

    public Player searchPlayer(String symbol,Player current) {
      if(current.getSymbol().equalsIgnoreCase(symbol))
        return current;
      else
        return searchPlayer(symbol,current.getNext());
    }//End searchPlayer

    public void clearBoard() {
      board = new String();
    }//End clearBoard

    /*
    public static void main(String[] args){
      Board b = new Board();
      Scanner s = new Scanner(System.in);
      System.out.print("Filas ");
      int n = s.nextInt();
      s.nextLine();
      System.out.print("Columnas ");
      int m = s.nextInt();
      b.createBoard(n,m);
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.addPlayer("$");
      b.addPlayer("#");
      b.addPlayer("%");
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("#",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("%",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("#",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("#",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("%",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
      b.movePlayer("$",1);
      b.clearBoard();
      System.out.println(b.getEnumerateBoard());
      System.out.println("\n");
    }//End main
     */

}//End Board Class
