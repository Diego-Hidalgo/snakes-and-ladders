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
         bw.write("\n");
         bw.flush();
        return option;
    }//End readOption

    public void doOperation(int option) throws IOException{
        switch(option) {
            case PLAY:
                br.readLine();
                break;
            case SHOW_POSITIONS:
                br.readLine();
                break;
            case EXIT:
                break;
            default:
                bw.write("Opción inválida\n");
                bw.flush();
                br.readLine();
        }//End switch
    }//End doOperation

    public void startProgram() throws IOException{
        int option = 0;
        do {
            showMainMenu();
            try {
                option = readOption();
                doOperation(option);
                ;
            } catch(NumberFormatException nfe) {
                bw.write("\n...\nLa entrada no es un número\n...\n\n");
                bw.flush();
            }
        } while(option != 3);
        bw.close();
        br.close();
    }

}//End Menu class