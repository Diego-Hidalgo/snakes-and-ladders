package ui;
import model.*;
import java.io.IOException;

public class Main {

	private static Menu m;
	private static Board board;

    public static void main(String[] args) throws IOException {
		board = new Board();
		m = new Menu(board);
        m.startProgram();
    }//End main

}//End Main Class
