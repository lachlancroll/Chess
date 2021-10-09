import javax.swing.ImageIcon;

public class Rook extends Piece{
	//public static ImageIcon whiteRook = new ImageIcon("white rook.png");
	//ImageIcon blackRook = new ImageIcon("black rook.png");
	
	Rook(Colour colour){
		this.value = 5;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whiteRook;
		} else {
			this.image = NewBoard.blackRook;
		}
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		if (((newSquare % 8) != (oldSquare % 8)) && ((newSquare / 8) != (oldSquare / 8))) {
			return false;
		}

		if (squares[newSquare].getColour() == squares[oldSquare].getColour()) {
			return false;
		}
			
		int col = 0;
		boolean foundMinCol = false;
		boolean foundMinRow = false;
		boolean foundMaxCol = false;
		boolean foundMaxRow = false;
		int row = 0;
		int maxCol = 7;
		int maxRow = 7;
		int minCol = 0;
		int minRow = 0;
		
		//do quick check if false to save time looking
		//do a check to make sure it isn't putting it in check
		
		col = oldSquare % 8;
		row = oldSquare / 8;
		int newCol = newSquare % 8;
		int newRow = newSquare / 8;
		
		
		//finding max and min rows
		
		for (int i = 0; i < 8; i++) {
			if (foundMinRow == false && squares[(i*8)+ col].getPiece() != null && ((i*8) + col) != oldSquare) {
				minRow = i;
			} else if ((i*8) + col == oldSquare) {
				foundMinRow = true;
			} else if (foundMinRow == true && squares[(i*8)+ col].getPiece() != null && foundMaxRow == false) {
				maxRow = i;
				foundMaxRow = true;
			} else if (foundMaxRow == false && i == 7) {
				maxRow = 7;
			}
			
		}
		
		// finding min and max cols
		for (int j = 0; j < 8; j++) {
			if (foundMinCol == false && squares[(row*8)+ j].getPiece() != null && ((row*8) + j) < oldSquare) {
				minCol = j;
			} else if (((row*8) + j) == oldSquare) {
				foundMinCol = true;
			} else if (foundMinCol == true && squares[(row*8)+ j].getPiece() != null && foundMaxCol == false) {
				maxCol = j;
				foundMaxCol = true;
			} else if (foundMaxCol == false && j == 7) {
				maxCol = 7;
			}
		}
		
		
		if (col == newCol && newRow <= maxRow && newRow >= minRow) {
			if (check && Check.check(squares, oldSquare, newSquare)) {
				return false;
			}
			return true;
		}
		if (row == newRow && newCol <= maxCol && newCol >= minCol) {
			if (check && Check.check(squares, oldSquare, newSquare)) {
				return false;
			}
			return true;
		}
	
		return false;
	}
	
	public boolean isRook() {
		return true;
	}
	
}

