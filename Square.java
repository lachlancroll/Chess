import java.awt.Color;
import java.util.LinkedList;

import javax.swing.*;

public class Square extends JButton{
	
	private static final long serialVersionUID = 1L;
	Piece piece;
	public static enum SquareColour{
		LIGHT, DARK;
	}
	SquareColour colour;
	int num;
	
	Square(Piece piece, int num){
		this.num = num;
		this.piece = piece;
		if (this.piece != null) {
			this.setIcon(piece.getIcon());
		}
		this.setSize(80, 80);
		this.setFocusable(false);
		this.setText(" " + num);
		/*if (num == 0) { 
			this.setText("1         ");
		} else if (num == 8) { 
			this.setText("2         ");
		}*/
	}
	
	Square(int num){
		this.num = num;
		this.setSize(80, 80);
		this.setFocusable(false);
		this.setText(" " + num);
		/*this.setForeground(new Color(190, 190, 190));
		if (num == 16) { 
			this.setText("3         ");
		} else if (num == 24) { 
			this.setText("4         ");
		} else if (num == 32) {
			this.setText("5         ");
		} else if (num == 40) { 
			this.setText("6         ");
		}*/
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
		if (piece != null) {
			this.setIcon(piece.getIcon());
		} else {
			this.setIcon(null);
		}
	}
	
	public void setPieceInvisible(Piece piece){
		this.piece = piece;
	}
	
	public Piece getPiece() {
		if (this.piece == null) {
			return null;
		}
		return this.piece;
	}
	
	public boolean checkKing() {
		if (this.piece == null) {
			return false;
		}
		return this.piece.checkKing();
	}
	
	public void setColour(SquareColour colour) {
		this.colour = colour;
		if (this.colour == SquareColour.DARK) {
			this.setBackground(new Color(135, 70, 50));
		} else {
			this.setBackground(new Color(230, 225, 220));
		}
	}
	
	public void light() {
		if (this.colour == SquareColour.DARK) {
			this.setBackground(new Color(190, 170, 100));
		} else {
			this.setBackground(new Color(220, 200, 100));
		}
	}
	
	public boolean canMove(int newSquare, Square[] squares, boolean check) {	
		return this.piece.canMove(this.num, newSquare, squares, check);
	}
	
	public int getNum() {
		return this.num;
	}
	
	public Piece.Colour getPieceColour(){
		if (this.piece == null) {
			return null;
		}
		return this.piece.getColour();
	}
	
	public Piece.Colour getColour(){
		if (this.piece == null) {
			return null;
		} else {
			return this.piece.getColour();
		}
	}
	
	public int score() {
		if (this.piece == null) {
			return 0;
		} else {
			return this.piece.score();
		}
	}
	
	public boolean isBishop() {
		if (this.piece == null) {
			return false;
		} else {
			return this.piece.isBishop();
		}
	}
	
	public int countMoves(Square[] squares, int[] nonPieces) {
		int total = 0;
		
		for (int i = 0; i < nonPieces.length; i++) {
			if (this.canMove(nonPieces[i], squares, true)) {
				total ++;
			}
		}
		return total;
	}
	
}
