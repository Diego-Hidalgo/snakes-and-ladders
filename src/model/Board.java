package model;

import java.io.*;
import java.util.Random;

public class Board implements Serializable {

    private static final long serialVersionUID = 1L;

    /*private static final String BLUE ="\033[34m";
    private static final String RED = "\033[31m";
    private static final String BOLD_FONT = "\033[0;1m";
    private static final String RESET = "\u001B[0m";*/
	
	private static final String BLUE ="";
    private static final String RED = "";
    private static final String BOLD_FONT = "";
    private static final String RESET = "";
	
    private Score root;
    private Player firstPlayer;
    private Player winner;
    private Square firstSquare;
    private String board;
    private int maxOccupation;
    private int currentOccupation;
    private int columnsAmount;
    private int rowsAmount;
    private boolean gameStatus;
    private String auxRow;
    private String gameParameters;

    /**
     * Constructor of the Board class.<br>
     *     <b>pre:</b> <br>
     *     <b>post:</b> A new Board object has been created. sets the game status to false (not over yet) and creates the first square. <br>
     */
    public Board() {
      firstSquare = new Square(0,0,1);
      gameStatus = false;
    }//End Constructor

    /**
     * resets the attributes of the Board object to its default status.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the attributes of the board have been set to its default status. <br>
     */
    public void reset() {
        firstPlayer = null;
        winner = null;
        firstSquare = new Square(0, 0, 1);
        board = "";
        maxOccupation = 0;
        currentOccupation = 0;
        columnsAmount = 0;
        rowsAmount = 0;
        gameStatus = false;
        auxRow = "";
        gameParameters = "";
    }//End reset

    /**
     * returns the information of the winner that won the current game in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the symbol and total of movements of the player tha won the game. <br>
     */
    public String getWinnerInfo() {
        String info = "El jugador " + winner.getSymbol() + " ha ganado el juego con " + winner.getMovements() + " movimientos.";
        return info;
    }//End getWinnerinfo

    /**
     * changes the current game status.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the status of the game has been changed. true if its over, false if not. <br>
     * @param gameStatus the new status of the current game that is being played.
     */
    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }//End setGameStatus

    /**
     * returns the status of the current game.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the status of the game. True if its over (A player has won), false if not. <br>
     */
    public boolean getGameStatus() {
        return gameStatus;
    }//End getGameStatus

    /**
     * changes the parameters of the current game that is being played.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the parameters of the game have been changed. <br>
     * @param gameParameters the new game parameters for the current game.
     */
    public void setGameParameters(String gameParameters) {
        this.gameParameters = gameParameters;
    }//End setGameParameters

    /**
     * returns the parameters of the current game that is being played.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the parameters of the game. <br>
     */
    public String getGameParameters() {
        return gameParameters;
    }//End getGameParameters

    /**
     * receives the parameters of the current game and calls the methods that create the board, generate the snakes and ladders and adds the players.<br>
     *     <b>pre:</b> the object that calls the method is not null. Parameters are initialized. <br>
     *     <b>post:</b> a new board has been created and its ready to be used for the game. <br>
     * @param rows the amount of rows of the board to be created. rows > 0.
     * @param columns the amount of columns of the board to be created. columns > 0.
     * @param snakes the amount of snakes to place in the board.
     * @param ladders the amount of ladders to place in the board.
     * @param symbols the symbols of the players that are going ot play.
     */
    public void receiveGameParameters(int rows, int columns, int snakes, int ladders, String symbols) {
        String params = rows + " " + columns + " " + snakes + " " + ladders + " " + symbols;
        setGameParameters(params);
        createBoard(rows, columns);
        generateSnakesAndLadders(snakes, ladders);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    /**
     * receives the parameters of the current game and calls the methods that create the board, generate the snakes and ladders and adds the players.<br>
     *     <b>pre:</b> the object that calls the method is not null. the parameters are initialized. <br>
     *     <b>post:</b> a new board has been created and it's ready to be used for the game. <br>
     * @param rows the amount of rows of the board to be created. rows > 0
     * @param columns the amount of columns of the board to be created. columns > 0
     * @param snakes the amount of snakes to place in the board.
     * @param ladders the amount of ladders to place in the board.
     * @param amountPlayers the amount of players to place in the board.
     */
    public void receiveGameParameters(int rows, int columns, int snakes, int ladders, int amountPlayers) {
        String availableSymbols = "*!OX%$#+&";
        String symbols = availableSymbols.substring(0, amountPlayers);
        String params = rows + " " + columns + " " + snakes + " " + ladders + " " + symbols;
        setGameParameters(params);
        createBoard(rows, columns);
        generateSnakesAndLadders(snakes, ladders);
        addAllPlayers(symbols, 0);
    }//End receiveGameParameters

    /**
     * throws a dice of six faces and moves the current player a number of squares given by the value of the dice.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the current player has moved the given value by the dice. returns the information corresponding to the throw. <br>
     */
    public String throwDice() {
        String info = "";
        Random r = new Random();
        int steps = r.nextInt(6) + 1;
        info = "El jugador " + firstPlayer.getSymbol() + " ha lanzado el dado y obtuvo el puntaje " + steps + ".";
        if((firstPlayer.getPositionNumber() + steps) <= (rowsAmount * columnsAmount)) {
            setGameStatus(movePlayer(firstPlayer.getSymbol(), steps));
            firstPlayer.setMovements(firstPlayer.getMovements() + steps);
        } else {
            info += "\nNo se pudo mover porque necesita un puntaje menor o igual a " + ((rowsAmount*columnsAmount) - firstPlayer.getPositionNumber()) + ".";
        }//End if/else
        firstPlayer = firstPlayer.getNext();
        return info;
    }//End throwDice

    /**
     * creates a new board. A linked list of Square objects<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new linked list of squares hat represents the board has been created. <br>
     * @param row the amount of rows of the new board. row > 0
     * @param col the amount of columns of the new board. col > 0
     */
    public void createBoard(int row,int col) {
      columnsAmount = col;
      rowsAmount = row;
      maxOccupation = ((int) Math.floor(((row*col)-2)/2));
      maxOccupation = maxOccupation - (int) Math.floor(col/2.0);
      addColumns(firstSquare);
      addRows(firstSquare);
    }//End createBoard

    /**
     * adds the columns to the board that is being generated. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new column has been added. <br>
     * @param current the current square in the linked list.
     */
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

    /**
     * adds the rows to the board that is being generated.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new row has been added to the board. <br>
     * @param current the current square in the linked list.
     */
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

    /**
     * returns the board with the enumerated squares in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the String that contains the board with the enumerated squares. <br>
     */
    public String getEnumeratedBoard() {
      board = new String();
      auxRow = new String();
      getRows(firstSquare,false);
      return board;
    }//End getEnumeratedBoard

    /**
     * returns the board without the enumerated squares in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the String that contains the board without the enumerated squares. <br>
     */
    public String getPlayableBoard(){
      board = new String();
      auxRow = new String();
      getRows(firstSquare,true);
      return board;
    }//End getPlayableBoard

    /**
     * adds the columns of the board in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the columns have been added to the String. <br>
     * @param current the current square in the linked list.
     * @param config the configuration for the board. false if enumerated, true if not.
     */
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

    /**
     * adds the rows of the board in a String.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the rows have been added to the String. <br>
     * @param current the current square that is being visited in the linked list.
     * @param config the configuration for the board. false if enumerated, true if not.
     */
    private void getRows(Square current, boolean config) {
      if(current != null){
        getColumns(current,config);
        board = auxRow+"\n"+board;
        auxRow = new String();
        getRows(current.getDown(),config);
      }//End if
    }//End getRows

    /**
     * calls the method that adds a specific player given its symbol.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> all the players have been added. <br>
     * @param symbols all the symbols of the players to be added in a String.
     * @param index an auxiliar used to stop the recursive call to the method. index >= 0
     */
    public void addAllPlayers(String symbols, int index) {
        if(index < symbols.length()) {
            addPlayer(String.valueOf(symbols.charAt(index)));
            addAllPlayers(symbols, ++index);
        }//End if
    }//End addAllPlayers

    /**
     * Adds a player given its symbol.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new player has been added. <br>
     * @param symbol the symbol of the player to add.
     */
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

    /**
     * moves a player given its symbol a certain amount of steps.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the player has been moved in the board. <br>
     * @param symbol the symbol of the player to be moved. the symbol is currently being used by a player
     * @param steps the amount of steps to move the player. steps > 0
     */
    public boolean movePlayer(String symbol, int steps) {
      Player toMove = searchPlayer(symbol,firstPlayer);
      move(toMove,steps);
      return checkPosition(toMove);
    }//End movePlayer

    /**
     * checks the position of a player in the board and does a certain action depending in the current square where it's placed.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> returns true if the player is in the last square, false if not. <br>
     * @param currentPlayer the player to check the position. currentPlayer != null.
     */
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

    /**
     * moves a player to the tail (beginning) of a snake.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a player has been moved to the square where the tail of the snake is placed. <br>
     * @param current the player to move to the tail of the snake. current != null.
     */
    private void moveToSnakeTail(Player current){
      Square currentSquare = current.getPosition();
      current.setPosition(currentSquare.getSnakeTail());
      current.getPosition().addCurrentsPlayers(current);
      currentSquare.removePlayer(current);
    }//End moveToSnakeTail

    /**
     * moves a player to the top (end) of a ladder.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a player has been mode to the square where the top of the ladder is placed. <br>
     * @param current the player to move to the top of the ladder. current != null
     */
    private void moveToLadderTop(Player current){
      Square currentSquare = current.getPosition();
      current.setPosition(currentSquare.getLadderTop());
      current.getPosition().addCurrentsPlayers(current);
      currentSquare.removePlayer(current);
    }//End moveToLadderTop

    /**
     * Moves a player a given amount of steps in the board.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a player has been moved the specified amount of steps in the board. <br>
     * @param player the player to move. player != null.
     * @param steps the amount of steps to move the player.
     */
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

    /**
     * returns the circular linked list of players. <br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the circular linked list of players.<br>
     */
    public Player getPlayers() {
      return firstPlayer;
    }//End getPlayers

    /**
     * searches a player in the linked list of players given its symbol.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the Player with the given symbol. <br>
     * @param symbol the symbol of the player to search.
     * @param current the current player of the linked list. current != null.
     */
    public Player searchPlayer(String symbol,Player current) {
      if(current.getSymbol().equalsIgnoreCase(symbol))
        return current;
      else
        return searchPlayer(symbol,current.getNext());
    }//End searchPlayer

    /**
     * calls the method the method that generates the snakes and ladders passing as parameters the initial values.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the snakes and ladders have been successfully added to the board.
     * @param snakesAmount the amount of snakes to place in the board. snakeAmount > 0
     * @param laddersAmount the amount of ladders to place in the board. laddersAmount > 0
     */
    public void generateSnakesAndLadders(int snakesAmount,int laddersAmount){
      char s = 65;
      Random selector = new Random();
	    generateSnakesAndLadders(snakesAmount,laddersAmount,s,1,selector);
    }//End generateSnakesAndLadders

	private void generateSnakesAndLadders(int snakesAmount,int laddersAmount,char snakeSymbol,int ladderSymbol,Random selector){
		int select = selector.nextInt(2);
		if( currentOccupation < maxOccupation && snakesAmount > 0 && select == 0  ){
			generateSnakes(snakesAmount,snakeSymbol);
			currentOccupation += 1;
			--snakesAmount;
			++snakeSymbol;
		}else if(currentOccupation < maxOccupation && laddersAmount > 0){
			generateLadders(laddersAmount,ladderSymbol);
			currentOccupation += 1;
			--laddersAmount;
			++ladderSymbol;
		}//End else
		if(currentOccupation < maxOccupation && (snakesAmount > 0 || laddersAmount > 0) )
			generateSnakesAndLadders(snakesAmount,laddersAmount,snakeSymbol,ladderSymbol,selector);
	}//End generateSnakesAndLadders

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param snakesAmount
     * @param symbol
     */
    public void generateSnakes(int snakesAmount, char symbol){
      if(snakesAmount > 0 && rowsAmount > 1){ //rowsAmount columnsAmount
        int squareHeadNumber = generateHeadSquare();
        Square head = setSnakeHead(firstSquare.getDown(),symbol,squareHeadNumber);
        if(head != null)
            setSnakeTail(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
        //generateSnakes((--snakesAmount),(++symbol));
      }//End if
    }//End generateSnakes

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     */
    private int generateHeadSquare(){
      Random selector = new Random();
      return selector.nextInt( ( (rowsAmount*columnsAmount) - columnsAmount - 1) ) + columnsAmount + 1;
    }//End generateHeadSquare

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param squareHeadNumber
     */
    private int generateTailSquare(int squareHeadNumber){
      int r = (int) Math.ceil(squareHeadNumber/((double)columnsAmount));
      Random selector = new Random();
	  int s = selector.nextInt( ( (r-1)*columnsAmount) - 1) + 2;
      return s;
    }//End generateTailSquare

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param head
     * @param symbol
     */
    public Square reLocatedHead(Square head,char symbol){
        head.setSnake(null);
        return setSnakeHead(firstSquare.getDown(),symbol,generateHeadSquare());
    }//End reLocatedHead

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param head
     * @param symbol
     */
    public Square reLocatedTop(Square head, int symbol){
        head.setLadder(null);
        return setLadderTop(firstSquare.getDown(),symbol,generateHeadSquare());
    }//End reLocatedHead

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param current
     * @param symbol
     * @param goal
     */
    public Square setSnakeHead(Square current,char symbol,int goal){
      if(current != null && currentOccupation < maxOccupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null){
				current.setSnake(String.valueOf(symbol));
        return current;
      }else if( current != null &&  current.getSquareNumber() == goal &&
      (current.getSnakeHead() != null || current.getSnakeTail() != null ||
       current.getLadderTop() != null || current.getLadderBot() != null) ){
        return setSnakeHead(firstSquare.getDown(),symbol,generateHeadSquare());
      }else if(current != null && currentOccupation < maxOccupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getNext():current.getPrev();
        next = (next == null)?current.getDown():next;
        return setSnakeHead(next,symbol,goal);
      }else
          return current;
    }//End setSnake

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param current
     * @param symbol
     * @param goal
     * @param head
     */
    public void setSnakeTail(Square current,char symbol, int goal,Square head){
      if(current != null && currentOccupation < maxOccupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null ){
		    current.setSnake(String.valueOf(symbol));
        current.setSnakeHead(head);
        head.setSnakeTail(current);
      }else if( current != null &&  current.getSquareNumber() == goal &&
      (current.getSnakeHead() != null || current.getSnakeTail() != null ||
       current.getLadderTop() != null || current.getLadderBot() != null) ){
        head = reLocatedHead(head,symbol);
				int tailGoal = generateTailSquare(head.getSquareNumber());
				setSnakeTail(firstSquare,symbol,tailGoal,head);
      }else if(current != null && currentOccupation < maxOccupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getPrev():current.getNext();
        next = (next == null)?current.getDown():next;
        setSnakeTail(next,symbol,goal,head);
      }//End else
    }//End setSnake

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param laddersAmount
     * @param symbol
     */
    public void generateLadders(int laddersAmount, int symbol){
      if(laddersAmount > 0 && rowsAmount > 1){ //rowsAmount columnsAmount
        int squareTopNumber = generateHeadSquare();
        Square head = setLadderTop(firstSquare.getDown(),symbol,squareTopNumber);
        if(head != null)
            setLadderBot(firstSquare,symbol,generateTailSquare(head.getSquareNumber()),head);
        //generateLadders((--laddersAmount),(++symbol));
      }//End if
    }//End generateLadders

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param current
     * @param symbol
     * @param goal
     */
    public Square setLadderTop(Square current,int symbol,int goal){
      if(current != null && currentOccupation < maxOccupation && current.getSquareNumber() == goal
      && current.getSnakeHead() == null && current.getLadderTop() == null
      && current.getLadderBot() == null && current.getSnakeTail() == null){
				current.setLadder(String.valueOf(symbol));
        return current;
      }else if( current != null &&  current.getSquareNumber() == goal &&
       (current.getSnakeHead() != null || current.getSnakeTail() != null ||
        current.getLadderTop() != null || current.getLadderBot() != null) ){
        return setLadderTop(firstSquare.getDown(),symbol,generateHeadSquare());
      }else if(current != null && currentOccupation < maxOccupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getNext():current.getPrev();
        next = (next == null)?current.getDown():next;
        return setLadderTop(next,symbol,goal);
      }else
          return current;
    }//End setLadderTop

    /**
     * <br>
     *     <b>pre:</b>
     *     <b>post:</b>
     * @param current
     * @param symbol
     * @param goal
     * @param head
     */
    public void setLadderBot(Square current,int symbol, int goal,Square head){
      if(current != null && currentOccupation < maxOccupation && current.getSquareNumber() == goal && current.getSnakeHead() == null
      && current.getLadderTop() == null && current.getLadderBot() == null && current.getSnakeTail() == null){
		current.setLadder(String.valueOf(symbol));
        current.setLadderTop(head);
        head.setLadderBot(current);
      }else if(current != null &&  current.getSquareNumber() == goal &&
       (current.getSnakeHead() != null || current.getSnakeTail() != null ||
        current.getLadderTop() != null || current.getLadderBot() != null) ){
        head = reLocatedTop(head,symbol);
				int botGoal = generateTailSquare(head.getSquareNumber());
        setLadderBot(firstSquare,symbol,botGoal,head);
      }else if(current != null && currentOccupation < maxOccupation){
        Square next = ( (current.getRow() + 1) % 2 == 0 )?current.getPrev():current.getNext();
        next = (next == null)?current.getDown():next;
        setLadderBot(next,symbol,goal,head);
      }//End else
    }//End setLadderBot

    /**
     * Adds a new score to the search binary tree. if root is null, the score will be added there. Otherwise the method that searches the place of save it will be called.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new score has been added to the inverted binary search tree. <br>
     * @param name the name of the winner player that got the score to be added.
     */
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

    /**
     * Adds a new score to the inverted search binary tree.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> a new score has been added to the search binary tree. <br>
     * @param current the current node that is being visited. <br>
     * @param toAdd the new score to add to the binary search tree.
     */
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

    /**
     * returns the saved information of the scores of previous played games. Traverses the binary search tree inorder.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the information of the previous played gamed .<br>
     * @param current the current node that is being visited inorder in the tree structure.
     */
    private String getScoresInString(Score current) {
        String info = "";
        if(current != null) {
            info += current.toString();
            info += getScoresInString(current.getLeft());
            info += getScoresInString(current.getRight());
        }//End if
        return info;
    }//End getScoresInString

    /**
     * returns the saved information of the scores of previous played games.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> the information in a String of the saved Scores. <br>
     */
    public String getScoresInString() {
        if(root == null) {
            return "\nNo hay puntuaciones por mostrar \n";
        } else {
            return getScoresInString(root);
        }//End if/else
    }//End getScoresInString

    /**
     * makes a deep clone of the current Board object using serialization.<br>
     *     <b>pre:</b> the object that calls the method is not null. <br>
     *     <b>post:</b> returns a new object that is a deep clone of the current Board. <br>
     */
    public Object deepClone() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return objectInputStream.readObject();
    }//End deepClone

}//End Board Class
