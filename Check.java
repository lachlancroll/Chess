import java.util.LinkedList;

public class Check {
	Check(){}
	
	public static boolean check(Square[] squares, int oldSquare, int newSquare) {
		Piece.Colour colour = squares[oldSquare].getColour();
		Piece oldConverter = squares[oldSquare].getPiece();
		Piece newConverter = squares[newSquare].getPiece();
		squares[newSquare].setPieceInvisible(squares[oldSquare].getPiece());
		squares[oldSquare].setPieceInvisible(null);
		int kingNum = 0;
		Piece.Colour newColour;
		if (colour == Piece.Colour.WHITE) {
			newColour = Piece.Colour.BLACK;
		} else {
			newColour = Piece.Colour.WHITE;
		}
		int piecesSize = 0;
		for (int i = 0; i < 64; i++) {
			if (squares[i].getColour() == colour && squares[i].score() == 1000) {
				kingNum = i;
			}
			if (squares[i].getColour() == newColour) {
				piecesSize++;
			}
		}
		int piecesCount = 0;
		int[] pieces = new int[piecesSize];
		for (int i = 0; i < 64; i++) {
			if (squares[i].getColour() == newColour) {
				pieces[piecesCount++] = i;
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			if (squares[pieces[i]].getPiece().canMove(pieces[i], kingNum, squares, false)) {
				squares[newSquare].setPieceInvisible(newConverter);
				squares[oldSquare].setPieceInvisible(oldConverter);
				return true;
			}
		}
		squares[newSquare].setPieceInvisible(newConverter);
		squares[oldSquare].setPieceInvisible(oldConverter);
		return false;
	}
	
	public static boolean check2(Square[] squares, Piece.Colour colour, int kingNum, int[] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			if (squares[pieces[i]].getPiece().canMove(pieces[i], kingNum, squares, false)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean checkMate(Square[] squares, Piece.Colour colour) {
		int total = 0;
		
		int kingNum = 0;
		int enemySize = 0;
		int pieceSize = 0;
		int nonPieceSize = 0;
		for (int i = 0; i < 64; i++) {
			if (squares[i].getColour() != colour && squares[i].getPiece() != null) {
				enemySize++;
				nonPieceSize++;
			} else if (squares[i].getColour() != colour){
				nonPieceSize++;
			} else {
				pieceSize++;
			}
			if (squares[i].getColour() == colour && squares[i].score() == 1000) {
				kingNum = i;
			}
		}
		
		int[] enemies = new int[enemySize];
		int[] nonPieces = new int[nonPieceSize];
		int[] pieces = new int[pieceSize];
		int enemyCount = 0;
		int pieceCount = 0;
		int nonPieceCount = 0;
		for (int i = 0; i < 64; i++) {
			if (squares[i].getColour() != colour && squares[i].getPiece() != null) {
				enemies[enemyCount++] = i;
				nonPieces[nonPieceCount++] = i;
			} else if (squares[i].getColour() != colour){
				nonPieces[nonPieceCount++] = i;
			} else {
				pieces[pieceCount++] = i;
			}
		}
		if (check2(squares, colour, kingNum, pieces) == false) {
			return false;
		}
		
		for (int i = 0; i < pieces.length; i++) {
			total += squares[pieces[i]].countMoves(squares, nonPieces);
		}
		if (total == 0) {
			//System.out.println("CHEECKKKMMAAAATE");
			return true;
		} else {
			return false;
		}
	}
}
