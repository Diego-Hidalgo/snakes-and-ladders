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
        bw.write("¿Qué desea hacer?\n" + "[1] Jugar\n" + "[2] Ver lista de puntuaciones\n" + "[3] Salir\n" + "Opcion: ");
        bw.flush();
    }//End showMenu

    public int readOption() throws NumberFormatException, IOException {
        int option = Integer.parseInt(br.readLine());
        return option;
    }//End readOption

    public void readParameters() throws IOException {
        bw.write("\nParámetros de juego: ");
        bw.flush();
        String params = br.readLine();
        try {
            int rows = Integer.parseInt(String.valueOf(params.charAt(0)));
            int columns = Integer.parseInt(String.valueOf(params.charAt(2)));
            int snakes = Integer.parseInt(String.valueOf(params.charAt(4)));
            int ladders = Integer.parseInt(String.valueOf(params.charAt(6)));
            String subPlayers = params.substring(8);
            passParameters(rows, columns, snakes, ladders, subPlayers);
        } catch (NumberFormatException | StringIndexOutOfBoundsException exception) {
            bw.write("\nPor favor revise los parámetros de juego.\n\n");
            bw.flush();
            br.readLine();
        }//End try/catch
    }//End readParameters

    public void passParameters(int rows, int columns, int snakes, int ladders, String subPlayers) throws IOException {
        try {
            int players = Integer.parseInt(subPlayers);
            board.receiveGameParameters(rows, columns, snakes, ladders, players);
        } catch (NumberFormatException nfe) {
            board.receiveGameParameters(rows, columns, snakes, ladders, subPlayers);
        }//End try/catch
        bw.write(board.getEnumeratedBoard() + "\n");
        bw.flush();
        readCommandOperation();
    }//End readParameters

    public void readCommandOperation() throws IOException {
        bw.write(": ");
        bw.flush();
        String command = br.readLine();
        if(!command.equalsIgnoreCase(MENU_COMMAND)) {
            doCommandOperation(command.toUpperCase());
            readCommandOperation();
        }//End if
    }//End readCommandOperation

    public void doCommandOperation(String command) throws IOException {
        switch(command) {
            case THROW_DICE_COMMAND:
                break;
            case NUM_COMMAND:
                br.readLine();
                break;
            case SIMUL_COMMAND:
                br.readLine();
                break;
            case MENU_COMMAND:
                break;
            default:
                bw.write("Comando no reconocido\n");
                bw.flush();
        }//End switch
    }//End doCommandOperation

    public void showPositioningList() {

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