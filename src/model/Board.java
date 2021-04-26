package model;
import java.util.Scanner;

public class Board {

    private Player root;
    private Square first;
    private String board;
    private int columnsAmount;
    private int rowsAmount;
    public Board() {
      first = new Square(0,0);
      board = new String();
    }//End board
    public void createBoard(int row,int col){
      columnsAmount = col;
      rowsAmount = row;
      addColumns(first);
      addRows(first);
    }//End createBoard
    private void addColumns(Square current){
      if(current.getColumn() < (columnsAmount-1)){
        Square newCol = new Square(current.getRow(),(current.getColumn()+1));
        newCol.setPrev(current);
        current.setNext(newCol);
        if(current.getUp() != null && (current.getUp()).getNext() != null){
          Square up = (current.getUp()).getNext();
          up.setDown(newCol);
          newCol.setUp(up);
        }//End if
        addColumns(newCol);
      }//End if
    }//End addColumns
    private void addRows(Square current){
      if(current.getRow() < (rowsAmount - 1) ){
        Square newRow = new Square((current.getRow()+1),current.getColumn());
        newRow.setUp(current);
        current.setDown(newRow);
        addColumns(newRow);
        addRows(newRow);
      }//End if
    }//End addRows
    public String getEnumerateBoard(){
      getRows(first);
      return board;
    }//End getEnumerateBoard
    public void getColumns(Square current){
      if(current != null){
        board += "["+current.getRow()+","+current.getColumn()+"]";
        getColumns(current.getNext());
      }//End if
    }//End getColumns
    public void getRows(Square current){
      if(current != null){
        getColumns(current);
        board += "\n";
        getRows(current.getDown());
      }//End if
    }//End getRows
    public static void main(String[] args){
      Board b = new Board();
      Scanner s = new Scanner(System.in);
      System.out.print("Filas ");
      int n = s.nextInt();
      s.nextLine();
      System.out.print("Columnas ");
      int m = s.nextInt();
    //  b.createBoard(n,m);
      b.createBoard(n,m);
      System.out.println(b.getEnumerateBoard());
    }//End main
}//End Board
