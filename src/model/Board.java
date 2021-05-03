package model;

import java.util.Random;
//import com.rits.cloning.Cloner;

public class Board {

    /*private final static String RED = "\033[31m";
    private final static String BLUE ="\033[34m";
    private final static String BOLD_FONT = "\033[0;1m";
    private final static String RESET = "\u001B[0m";*/

	private final static String RED = "";
    private final static String BLUE ="";
    private final static String BOLD_FONT = "";
    private final static String RESET = "";

    private Score root;
    private Player firstPlayer;
    private Player winner;
    private Square firstSquare;
    private String board;
    private int maxOcupation;
    private float currentOcupation;
    private int columnsAmount;
    private int rowsAmount;
    private boolean gameStatus;
    private String auxRow;
    private String gameParameters;

    public Board() {
      firstSquare = new Square(0,0,1);
      gameStatus = false;
    }//End Constructor

    public String getWinnerInfo() {
        String info = "El jugador " + winner.getSymbol() + " ha ganado el juego con " + winner.getMovements() + " movimientos.";
        return info;
    }//End getWinnerinfo

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }//End setGameStatus

    public boolean getGameStatus() {
        return gameStatus;
    }//End getGameStatus

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
        generateSnakesAndLadders(snakes, ladders);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    public void receiveGameParameters(int rows, int columns, int snakes, int ladders, int amountPlayers) {
        String availableSymbols = "*!OX%$#+&";
        String symbols = availableSymbols.substring(0, amountPlayers);
        String params = rows + " " + columns + " " + snakes + " " + ladders + " " + symbols;
        setGameParameters(params);
        createBoard(rows, columns);
        generateSnakesAndLadders(snakes, ladders);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    public String throwDice() {
        Random r = new Random();
        int steps = r.nextInt(6) + 1;
        String info = "El jugador " + firstPlayer.getSymbol() + " ha lanzado el dado y obtuvo el puntaje " + steps;
        setGameStatus(movePlayer(firstPlayer.getSymbol(), steps));
        firstPlayer.setMovements(firstPlayer.getMovements() + steps);
        firstPlayer = firstPlayer.getNext();
        return info;
    }//End throwDice

    public void createBoard(int row,int col) {
      columnsAmount = col;
      rowsAmount = row;
      maxOcupation = (int) Math.floor(((row*col)-2)/2);
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
      getRows(firstSquare,false);
      return board;
    }//End getEnumeratedBoard

    public String getPlayableBoard(){
      board = new String();
      auxRow = new String();
      getRows(firstSquare,true);
      return board;
    }//End getPlayableBoard

    private void getColumns(Square current, boolean config) {
      if(current != null){
        String snake = (current.getSnakeHead() != null)?RED + current.getSnake():"";
        String tail = (current.getSnakeTail() != null)?RED + current.getSnake():"";
        String top = (current.getLadderTop() != null)?BLUE + current.getLadder():"";
        String bot = (current.getLadderBot() != null)?BLUE + current.getLadder():"";
        if(config)
          auxRow += RESET+"["+BOLD_FONT+current.getCurrentPlayers()+RESET+snake+tail+top+bot+RESET+"]";
        else
          auxRow += RESET+"["+current.getSquareNumber()+" "+BOLD_FONT+current.getCurrentPlayers()+RESET+snake+tail+top+bot+RESET+"]";
        getColumns(current.getNext(),config);
      }//End if
    }//End getColumns

    private void getRows(Square current, boolean config) {
      if(current != null){
        getColumns(current,config);
        board = auxRow+"\n"+board;
        auxRow = new String();
        getRows(current.getDown(),config);
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

    public boolean movePlayer(String symbol, int steps) {
      Player toMove = searchPlayer(symbol,firstPlayer);
      move(toMove,steps);
      return checkPosition(toMove);
    }//End movePlayer

    private boolean checkPosition(Player currentPlayer){
      boolean check = false;
      if( currentPlayer.getPosition().getSquareNumber() == (columnsAmount*rowsAmount) ){
          check = true;
          winner = currentPlayer;
      }else if( currentPlayer.getPosition().getSnakeTail() != null ){
          moveToSnakeTail(currentPlayer);
      }else if( currentPlayer.getPosition().getLadderTop() != null ){
          moveToLadderTop(currentPlayer);
      }
      return check;
    }//End checkPosition

    private void moveToSnakeTail(Player current){
      Square currentSquare = current.getPosition();
      current.setPosition(currentSquare.getSnakeTail());
      current.getPosition().addCurrentsPlayers(current);
      currentSquare.removePlayer(current);
    }//End moveToSnakeTail

    private void moveToLadderTop(Player current){
      Square currentSquare = current.getPosition();
      current.setPosition(currentSquare.getLadderTop());
      current.getPosition().addCurrentsPlayers(current);
      currentSquare.removePlayer(current);
    }//End moveToLadderTop

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
          currentSquare.removePlayer(player);
        }else if( currentSquare.getDown() != null ){
          currentSquare.getDown().addCurrentsPlayers(player);
          player.setPosition( currentSquare.getDown());
          currentSquare.removePlayer(player);
        } //End else
        //currentSquare.removePlayer(player);
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

    public void generateSnakesAndLadders(int snakesAmount,int laddersAmount){
      char s = 65;
      generateSnakes(snakesAmount,s);
      generateLadders(laddersAmount,1);
    }//End generateSnakesAndLadders

    public void generateSnakes(int snakesAmount, char symbol){
      if(snakesAmount > 0 && rowsAmount > 1){ //rowsAmount columnsAmount
        int squareHeadNumber = generateHeadSquare();
        Square head = setSnakeHead(firstSquare.getDown(),symbol,squareHeadNumber);
        if(head != null)
            setSnakeTail(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
        generateSnakes((--snakesAmount),(++symbol));
      }//End if
    }//End generateSnakes

    private int generateHeadSquare(){
      Random selector = new Random();
      return selector.nextInt( ( (rowsAmount*columnsAmount) - columnsAmount - 1) ) + columnsAmount + 1;
    }//End generateHeadSquare

    private int generateTailSquare(int squareHeadNumber){
      int r = (int) Math.ceil(squareHeadNumber/((double)columnsAmount));
      //int n = ((r-1)*columnsAmount);
      Random selector = new Random();
      //int s = selector.nextInt(n) + 1;
			//s = (s==1)? selector.nextInt( ( (r-1)*columnsAmount) -1) + 2 : s;
			int s = selector.nextInt( ( (r-1)*columnsAmount) - 1) + 2;
      return s;
    }//End generateTailSquare

    public Square setSnakeHead(Square current,char symbol,int goal){
      if(current != null && currentOcupation < maxOcupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null){
        current.setSnake(String.valueOf(symbol));
        currentOcupation += 0.5;
        return current;
      }else if( current != null &&  current.getSquareNumber() == goal &&
      (current.getSnakeHead() != null || current.getSnakeTail() != null ||
       current.getLadderTop() != null || current.getLadderBot() != null) ){
        return setSnakeHead(firstSquare.getDown(),symbol,generateHeadSquare());
      }else if(current != null && currentOcupation < maxOcupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getNext():current.getPrev();
        next = (next == null)?current.getDown():next;
        return setSnakeHead(next,symbol,goal);
      }else
          return current;
    }//End setSnake

    public void setSnakeTail(Square current,char symbol, int goal,Square head){
      if(current != null && currentOcupation < maxOcupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null ){
        current.setSnake(String.valueOf(symbol));
        current.setSnakeHead(head);
        head.setSnakeTail(current);
        currentOcupation += 0.5;
      }else if( current != null &&  current.getSquareNumber() == goal &&
      (current.getSnakeHead() != null || current.getSnakeTail() != null ||
       current.getLadderTop() != null || current.getLadderBot() != null) ){
        setSnakeTail(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
      }else if(current != null && currentOcupation < maxOcupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getPrev():current.getNext();
        next = (next == null)?current.getDown():next;
        setSnakeTail(next,symbol,goal,head);
      }
    }//End setSnake

    public void generateLadders(int laddersAmount, int symbol){
      if(laddersAmount > 0 && rowsAmount > 1){ //rowsAmount columnsAmount
        int squareTopNumber = generateHeadSquare();
        Square head = setLadderTop(firstSquare.getDown(),symbol,squareTopNumber);
        if(head != null)
            setLadderBot(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
        generateLadders((--laddersAmount),(++symbol));
      }//End if
    }//End generateLadders

    public Square setLadderTop(Square current,int symbol,int goal){
      if(current != null && currentOcupation < maxOcupation && current.getSquareNumber() == goal
      && current.getSnakeHead() == null && current.getLadderTop() == null
      && current.getLadderBot() == null && current.getSnakeTail() == null){
        current.setLadder(String.valueOf(symbol));
        currentOcupation += 0.5;
        return current;
      }else if( current != null &&  current.getSquareNumber() == goal &&
       (current.getSnakeHead() != null || current.getSnakeTail() != null ||
        current.getLadderTop() != null || current.getLadderBot() != null) ){
        return setLadderTop(firstSquare.getDown(),symbol,generateHeadSquare());
      }else if(current != null && currentOcupation < maxOcupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getNext():current.getPrev();
        next = (next == null)?current.getDown():next;
        return setLadderTop(next,symbol,goal);
      }else
          return current;
    }//End setLadderTop

    public void setLadderBot(Square current,int symbol, int goal,Square head){
      if(current != null && currentOcupation < maxOcupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null){
        current.setLadder(String.valueOf(symbol));
        current.setLadderTop(head);
        head.setLadderBot(current);
        currentOcupation += 0.5;
      }else if(current != null &&  current.getSquareNumber() == goal &&
       (current.getSnakeHead() != null || current.getSnakeTail() != null ||
        current.getLadderTop() != null || current.getLadderBot() != null) ){
        setLadderBot(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
      }else if(current != null && currentOcupation < maxOcupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getPrev():current.getNext();
        next = (next == null)?current.getDown():next;
        setLadderBot(next,symbol,goal,head);
      }
    }//End setLadderBot

    public void addScore(String name) {
        winner.setName(name);
        int value = winner.getMovements() * columnsAmount * rowsAmount;
        Score toAdd = new Score(winner, value, getGameParameters());
        if(root == null) {
            root = toAdd;
        } else {
            addScore(root, toAdd);
        }//End if/else
    }//End addScore

    private void addScore(Score current, Score toAdd) {
        if(current.getScore() > toAdd.getScore()) {
            if(current.getRight() == null) {
                current.setRight(toAdd);
            } else {
                addScore(current.getRight(), toAdd);
            }//End if/else
        } else {
            if(current.getLeft() == null) {
                current.setLeft(toAdd);
            } else {
                addScore(current.getLeft(), toAdd);
            }//End if/else
        }//End if/else
    }//End addScore

    /*public Board cloneBoard() {
        Cloner cloner = new Cloner();
        return cloner.deepClone(this);
    }//End clone

    /*
    public static void main(String[] args){
      Board b = new Board();
      Scanner s = new Scanner(System.in);
      boolean win = false;
      String text = new String();
      System.out.print("Filas ");
      int n = s.nextInt();
      s.nextLine();
      System.out.print("Columnas ");
      int m = s.nextInt();
      b.createBoard(n,m);
      System.out.print("Serpientes ");
      s.nextLine();
      int c = s.nextInt();
      System.out.print("Escaleras ");
      s.nextLine();
      int d = s.nextInt();
      b.generateSnakesAndLadders(c,d);
      b.addPlayer("$");
      b.addPlayer("#");
      b.addPlayer("%");
      System.out.println(b.getEnumeratedBoard());
      System.out.println("\n");
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("#",2);
      if(win)
        text = "Gano #";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("%",3);
      if(win)
        text = "Gano %";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("#",1);
      if(win)
        text = "Gano #";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("#",1);
      if(win)
        text = "Gano #";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("%",1);
      if(win)
        text = "Gano %";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      win = b.movePlayer("$",1);
      if(win)
        text = "Gano $";
      System.out.println(b.getPlayableBoard());
      System.out.println("\n");
      System.out.println(text);
      s.close();
    }//End main*/

}//End Board Class
