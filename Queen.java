
public class Queen extends Piece{
	
	Queen(Colour colour){
		this.value = 9;
		this.colour = colour;
		if (this.colour == Colour.WHITE) {
			this.image = NewBoard.whiteQueen;
		} else {
			this.image = NewBoard.blackQueen;
		}
	}
	
	public boolean canMove(int oldNum, int newNum, Square[] squares, boolean check) {
		return (new Bishop(this.colour).canMove(oldNum, newNum, squares, check) || new Rook(this.colour).canMove(oldNum, newNum, squares, check));
	}
}

