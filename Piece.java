
import javax.swing.*;

public class Piece {

	public static enum Colour {
		WHITE, BLACK;
	}
	
	//BoardArray.Board board;
	Colour colour;
	int value;
	ImageIcon image;
	boolean hasMoved;
	
	Piece(Colour colour){
		this.colour = colour;
	}
	
	Piece(){}
	
	public ImageIcon getIcon() {
		return this.image;
	}
	
	public Colour getColour() {
		return this.colour;
	}
	
	public boolean checkKing() {
		return false;
	}
	
	public boolean isRook(){
		return false;
	}
	
	public boolean isBishop() {
		return true;
	}
	
	public int score(){
		return value;
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		return true;
	}
	
}
