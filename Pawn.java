

public class Pawn extends Piece{
	
	Pawn(Colour colour){
		this.value = 1;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whitePawn;
		} else {
			this.image = NewBoard.blackPawn;
		}
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		int col = oldSquare % 8;
		boolean canMove = false;
		if (this.colour == Piece.Colour.WHITE) {
			if (newSquare != oldSquare - 8 && newSquare != oldSquare - 7 && newSquare != oldSquare - 9 && newSquare != oldSquare - 16) {
				return false;
			}
			if (squares[oldSquare].getColour() == squares[newSquare].getColour()) {
				return false;
			}
			if (newSquare == oldSquare - 8 && squares[newSquare].getPiece() == null) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else if (newSquare == oldSquare - 16 && squares[newSquare].getPiece() == null && squares[oldSquare - 8].getPiece() == null && oldSquare > 47 && oldSquare < 56) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else if (((newSquare == (oldSquare - 7) && col != 7)|| (newSquare == oldSquare - 9 && col != 0)) && squares[newSquare].getPiece() != null) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		} else {
			if (newSquare != oldSquare + 8 && newSquare != oldSquare + 7 && newSquare != oldSquare + 9 && newSquare != oldSquare + 16) {
				return false;
			}
			if (squares[oldSquare].getColour() == squares[newSquare].getColour()) {
				return false;
			}
			if (newSquare == oldSquare + 8 && squares[newSquare].getPiece() == null) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else if (squares[newSquare].getPiece() == null && squares[oldSquare + 8].getPiece() == null && newSquare == (oldSquare + 16) && oldSquare > 7 && oldSquare < 16) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else if (((newSquare == (oldSquare + 7) && col != 0)|| newSquare == (oldSquare + 9) && col != 7) && squares[newSquare].getPiece() != null) {
				if (check && Check.check(squares, oldSquare, newSquare)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		}
		
	}
	
}
