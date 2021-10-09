
public class King extends Piece{
	
	King(Colour colour){
		this.value = 1000;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whiteKing;
		} else {
			this.image = NewBoard.blackKing;
		}
	}
	
	public boolean canMove(int oldSquare, int newSquare, Square[] squares, boolean check) {
		int col = oldSquare % 8;
		int row = oldSquare / 8;
		int newCol = newSquare % 8;
		int newRow = newSquare / 8; 
		
		if ((Math.abs(newRow - row) > 1 || Math.abs(newCol - col) > 1) && newSquare != 62 && newSquare != 58 && newSquare != 6 && newSquare != 2) {
			return false;
		}
		if (check && Check.check(squares, oldSquare, newSquare)) {
			return false;
		}
		
		if (this.colour == Piece.Colour.WHITE &&  this.hasMoved == false && NewBoard.whiteRRookMoved == false && newSquare == 62 && squares[61].getPiece() == null) {
			return true;
		} else if (this.colour == Piece.Colour.WHITE && this.hasMoved == false && NewBoard.whiteLRookMoved == false && newSquare == 58 && squares[59].getPiece() == null && squares[57].getPiece() == null ) {
			return true;
		} else if (this.colour == Piece.Colour.BLACK && this.hasMoved == false && NewBoard.blackRRookMoved == false && newSquare == 6 && squares[5].getPiece() == null) {
			return true;
		} else if (this.colour == Piece.Colour.BLACK &&  this.hasMoved == false && NewBoard.blackLRookMoved == false && newSquare == 2 && squares[3].getPiece() == null && squares[1].getPiece() == null ) {
			return true;
		}
		//move this to the start
		if (squares[newSquare].getColour() == squares[oldSquare].getColour()) {
			return false;
		}
		if (col == 0 && ((newCol - col) == 1 || (newCol - col) == 0) && Math.abs(newRow - row) <= 1){
			return true;
		}
		
		if (col == 7 && (newSquare == oldSquare + 8 || newSquare == oldSquare + 7 || newSquare == oldSquare - 1 || newSquare == oldSquare - 8 || newSquare == oldSquare - 9)){
			return true;
		}
		
		if (Math.abs(newRow - row) <= 1 && Math.abs(newCol - col) <= 1) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public boolean checkKing() {
		return true;
	}
	
}
