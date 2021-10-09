public class Bishop extends Piece{
	
	Bishop(Colour colour){
		this.value = 3;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whiteBishop;
		} else {
			this.image = NewBoard.blackBishop;
		}
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		if (squares[newSquare].getColour() == squares[oldSquare].getColour()) {
			return false;
		}
		if (Math.abs((newSquare % 8) - (oldSquare % 8)) != Math.abs((newSquare / 8) - (oldSquare / 8))) {
			return false;
		}
		
		int col = 0;
		int row = 0;
		int maxCol = 7;
		int maxRow = 7;
		int minCol = 0;
		int minRow = 0;
		boolean haveMax = false;
		boolean haveMin = false;
		boolean finished = false;
		
		int maxColTopLeft = 7; //  \
		int maxRowTopLeft = 7;
		int minColTopLeft = 0;
		int minRowTopLeft = 0;
		boolean haveMaxTopLeft = false;
		boolean haveMinTopLeft = false;
		
		//finding the row and col to start counting
		int ogcol = oldSquare % 8;
		int ogrow = oldSquare / 8;
		int newCol = newSquare % 8;
		int newRow = newSquare / 8;
		
		if (newCol == ogcol && newRow == ogrow) {
			return false;
		}
		
		row = ogrow;
		col = ogcol;
		
		//FINDING MAX
		while (row < 7 && col > 0 && haveMax == false) {
			row++;
			col--;
			if (haveMax == false && squares[(row*8)+ col].getPiece() != null) {
				maxRow = row;
				minCol = col;
				haveMax = true;
			} else if (haveMax == false && (row == 7|| col == 0)) {
				maxRow = row;
				minCol = col;
				haveMax = true;
			}
		}
		
		row = ogrow;
		col = ogcol;
			
		while (row > 0 && col < 7 && haveMin == false) {
			row--;
			col++;
			if (squares[(row*8)+ col].getPiece() != null) {
				minRow = row;
				maxCol = col;
				haveMin = true;
			} else if (row == 0|| col == 7) {
				minRow = row;
				maxCol = col;
				haveMin = true;
			}
		}
		
		row = ogrow;
		col = ogcol;
		
		// FINDING MAX_
		while (row < 7 && col < 7) {
			row++;
			col++;
			if (haveMaxTopLeft == false && squares[(row*8)+ col].getPiece() != null) {
				maxRowTopLeft = row;
				maxColTopLeft = col;
				haveMaxTopLeft = true;
			} else if (haveMaxTopLeft == false && (row == 7|| col == 7)) {
				maxRowTopLeft = row;
				maxColTopLeft = col;
				haveMaxTopLeft = true;
			}
		}
		
		row = ogrow;
		col = ogcol;
		
		while (row > 0 && col > 0) {
			row--;
			col--;
			if (haveMinTopLeft == false && squares[(row*8)+ col].getPiece() != null) {
				minRowTopLeft = row;
				minColTopLeft = col;
				haveMinTopLeft = true;
			} else if (haveMinTopLeft == false && (row == 0|| col == 0)) {
				minRowTopLeft = row;
				minColTopLeft = col;
				haveMinTopLeft = true;
			}
		}

		
		// MOVEMENTS
		row = ogrow;
		col = ogcol;
		
		while (row < 7 && col > 0 && finished == false) {
			row++;
			col--;
			if (((row*8) + col) == newSquare && newSquare > ((maxRow*8) + minCol)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			}
		}
		
		row = ogrow;
		col = ogcol;
			
		while (row > 0 && col < 7) {
			row--;
			col++;
			if (((row*8) + col) == newSquare && newSquare < ((minRow*8) + maxCol)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			}
		}
			
		row = ogrow;
		col = ogcol;
		
		while (row < 7 && col < 7) {
			row++;
			col++;
			if (((row*8) + col) == newSquare && newSquare > ((maxRowTopLeft*8) + maxColTopLeft)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			}
		}
		
		row = ogrow;
		col = ogcol;
		
		while (row > 0 && col > 0) {
			row--;
			col--;
			if (((row*8) + col) == newSquare && newSquare < ((minRowTopLeft*8) + minColTopLeft)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			}
		}
		// - 7 or - 9
		return false;
	}
	
	public boolean isBishop() {
		return true;
	}
	
}
