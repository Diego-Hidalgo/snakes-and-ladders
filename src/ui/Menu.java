package ui;

import model.Board;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Menu {

    private BufferedReader br;
    private BufferedWriter bw;

    private final static int PLAY = 1;
    private final static int SHOW_POSITIONS = 2;
    private final static int EXIT = 3;

    private final static String THROW_DICE_COMMAND = "";
    private final static String NUM_COMMAND = "NUM";
    private final static String SIMUL_COMMAND = "SIMUL";
    private final static String MENU_COMMAND = "MENU";

    private Board board;

    public Menu() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        board = new Board();
    }//End constructor

    public void showMainMenu() throws IOException {
        bw.write("Qu\u00e9 desea hacer?\n[1] Jugar\n[2] Ver lista de puntuaciones\n[3] Salir\nOpcion: ");
        bw.flush();
    }//End showMenu

    public int readOption() throws NumberFormatException, IOException {
        int option = Integer.parseInt(br.readLine());
        return option;
    }//End readOption

    public void readParameters() throws IOException {
        bw.write("\nPar\u00e1metros de juego: ");
        bw.flush();
        String params = br.readLine();
        bw.write("\n");
        bw.flush();
        try {
            int rows = Integer.parseInt(String.valueOf(params.charAt(0)));
            int columns = Integer.parseInt(String.valueOf(params.charAt(2)));
            int snakes = Integer.parseInt(String.valueOf(params.charAt(4)));
            int ladders = Integer.parseInt(String.valueOf(params.charAt(6)));
            String subPlayers = params.substring(8);
            passParameters(rows, columns, snakes, ladders, subPlayers);
        } catch (NumberFormatException | StringIndexOutOfBoundsException exception) {
            bw.write("Por favor revise los par\\u00e1metros de juego.\n");
            bw.flush();
        }//End try/catch
    }//End readParameters

    public void passParameters(int rows, int columns, int snakes, int ladders, String subPlayers) throws IOException {
        try {
            int players = Integer.parseInt(subPlayers);
            board.receiveGameParameters(rows, columns, snakes, ladders, players);
        } catch (NumberFormatException nfe) {
            board.receiveGameParameters(rows, columns, snakes, ladders, subPlayers);
        }//End try/catch
        bw.write("--- JUEGO INICIADO ---\n");
        bw.write(board.getEnumeratedBoard() + "\n");
        bw.flush();
        readCommandOperation();
    }//End readParameters

    public void readCommandOperation() throws IOException {
        bw.write("Comando: ");
        bw.flush();
        String command = br.readLine().toUpperCase();
        bw.write("\n");
        bw.flush();
        doCommandOperation(command);
        if(!board.getGameStatus() && !command.equals(MENU_COMMAND)) {
            readCommandOperation();
        } else {
            restart();
        }//End if/else
    }//End readCommandOperation

    public void restart() {
        board = new Board();
    }//End restart

    public void play() throws IOException {
        bw.write(board.throwDice() + "\n");
        bw.write(board.getPlayableBoard());
        bw.flush();
        if(board.getGameStatus()) {
            bw.write("--- JUEGO TERMINADO ---\n");
            bw.write(board.getWinnerInfo());
            bw.flush();
            bw.write("Nickname: ");
            String nickname = br.readLine();
            board.addScore(nickname);
            bw.write("\n");
            bw.flush();
        }//End if
    }//End play

   public void simulation() throws IOException {
        try {
            Board simul = (Board) board.deepClone();
            bw.write("--- SIMULACION INICIADA ---\n");
            bw.flush();
            simulation(simul);
        } catch(Exception e) {
            bw.write("Un error ha ocurrido :(\n");
            bw.flush();
        }//End try/catch
        bw.write(board.getPlayableBoard());
        bw.flush();
    }//End simulation

    public void simulation(Board simul) throws IOException {
        if(!simul.getGameStatus()) {
            bw.write(simul.throwDice() + "\n");
            bw.write(simul.getPlayableBoard());
            bw.flush();
            try {
                Thread.sleep(2000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            simulation(simul);
        } else {
            bw.write(simul.getWinnerInfo() + "\n");
            bw.write("--- SIMULACION TERMINADA ---\n\n");
            bw.flush();
        }//End if/else
    }//End simulation

    public void doCommandOperation(String command) throws IOException {
        switch(command) {
            case THROW_DICE_COMMAND:
                play();
                break;
            case NUM_COMMAND:
                bw.write("\n" + board.getEnumeratedBoard() + "\n");
                bw.flush();
                break;
            case SIMUL_COMMAND:
                simulation();
                break;
            case MENU_COMMAND:
                restart();
                break;
            default:
                bw.write("Comando no reconocido\n");
                bw.flush();
        }//End switch
    }//End doCommandOperation

    public void showPositioningList() {
        //To Do
    }//End showPositioningList

    public void doOperation(int option) throws IOException{
        switch(option) {
            case PLAY:
                readParameters();
                break;
            case SHOW_POSITIONS:
                showPositioningList();
                break;
            case EXIT:
                break;
            default:
                bw.write("Opción inválida\n");
                bw.flush();
                br.readLine();
        }//End switch
    }//End doOperation

    public void startProgram() throws IOException {
        showMainMenu();
        int option = 0;
        try {
            option = readOption();
            doOperation(option);
        } catch(NumberFormatException nfe) {
            bw.write("\n...\nLa entrada no es un número\n...\n\n");
            bw.flush();
        }//End try/catch
        if(option != 3) {
            startProgram();
        } else {
            bw.close();
            br.close();
        }//End if/else
    }//End startProgram

}//End Menu Class
