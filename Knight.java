import javax.swing.ImageIcon;

public class Knight extends Piece{
	
	Knight(Colour colour){
		this.value = 3;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whiteKnight;
		} else {
			this.image = NewBoard.blackKnight;
		}
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		
		int col = oldSquare % 8;
		
		if ((newSquare != (oldSquare + 17)) && (newSquare != (oldSquare - 17)) && (newSquare != (oldSquare + 15)) && (newSquare != (oldSquare - 15)) && (newSquare != (oldSquare + 6)) && (newSquare != (oldSquare - 6)) && (newSquare != (oldSquare + 10)) && (newSquare != (oldSquare - 10))) {
			return false;
		}
		if (squares[oldSquare].getColour() == squares[newSquare].getColour()) {
			return false;
		}
		
		if (col == 6) {
			if ((newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 17)) || (newSquare == (oldSquare + 6)) || (newSquare == (oldSquare - 10))) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (col == 7) {
			if ((newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 6)) || (newSquare == (oldSquare - 10))) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (col == 0){
			if ((newSquare == (oldSquare + 17))  ||(newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10))) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else if (col == 1) {
			if ((newSquare == (oldSquare + 17)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare - 15)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10))) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		}
		if ((newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 17)) || (newSquare == (oldSquare - 10)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10)) || (newSquare == (oldSquare + 6))) {
			if (check && Check.check(squares, oldSquare, newSquare)) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
}

