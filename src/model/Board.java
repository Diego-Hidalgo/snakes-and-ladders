package model;
import java.util.Scanner;

public class Board {

    private Player root;
    private Square first;
    private String board;
    public Board() {
      first = new Square(0,0);
      board = new String();
    }//End board
    public void createBoard(int row,int col){
      if(first.getNext() == null && first.getDown() == null){
        if(first.getColumn() < col){
            first.setNext(new Square(0,1));
            board += "[0]";
        }
        if( first.getRow() < row){
            first.setDown(new Square(0,1));
        }
      }else{
        addColums(first.getNext(),col);
        addRows(first.getDown(),row,col);
      }//End else
    }//End createBoard

    public void addColums(Square base,int colLimit){
      if(base.getColumn() < colLimit ){
        Square newCol = new Square(base.getRow(),(base.getColumn()+1));
        newCol.setPrev(base);
        base.setNext(newCol);
        if(base.getUp() != null && (base.getUp()).getNext() != null){
          Square up = (base.getUp()).getNext();
          up.setDown(newCol);
          newCol.setUp(up);
        }//End if
        board += "[0]";
       if(base.getColumn()+1 >= colLimit )
          board += "\n";
        addColums(newCol,colLimit);
      }//End if
    }//End addColums
    public String getBoard(){
      return board;
    }
    public void addRows(Square base,int rowLimit,int colLimit){
      if(base.getRow() < (rowLimit - 1) ){
        Square newRow = new Square((base.getRow()+1),base.getColumn());
        newRow.setUp(base);
        base.setDown(newRow);
        board += "[0]";
        addColums(newRow,colLimit);
        addRows(newRow,rowLimit,colLimit);
      }//End if
    }//End addColums
    public void clearBoard(){
      board = new String();
    }
    public static void main(String[] args){
      Board b = new Board();
      Scanner s = new Scanner(System.in);
      System.out.print("Filas ");
      int n = s.nextInt();
      s.nextLine();
      System.out.print("Columnas ");
      int m = s.nextInt();
      b.createBoard(n,m);
      b.createBoard(n,m);
      System.out.println(b.getBoard());
    }//End main
}//End Board
