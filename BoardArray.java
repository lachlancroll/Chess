
//public class BoardArray {
//	public static enum Board {
//		BLANK, WHITE_PAWN, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK, WHITE_QUEEN, WHITE_KING, BLACK_PAWN, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK, BLACK_QUEEN, BLACK_KING
//	}
//	Board[] boardArr = new Board[64];
//	
//	BoardArray(){
//		for(int i = 0; i < 64; i++) {
//			if (i == 0 || i == 7) {
//				boardArr[i] = Board.BLACK_ROOK;
//			} else if (i == 1 || i == 6) {
//				boardArr[i] = Board.BLACK_KNIGHT;
//			} else if (i == 2 || i == 5) {
//				boardArr[i] = Board.BLACK_BISHOP;
//			} else if (i == 3) {
//				boardArr[i] = Board.BLACK_QUEEN;
//			} else if (i == 4) {
//				boardArr[i] = Board.BLACK_KING;
//			} else if (i > 7 && i < 16) {
//				boardArr[i] = Board.BLACK_PAWN;
//			} else if (i > 47 && i < 56) {
//				boardArr[i] = Board.WHITE_PAWN;
//			} else if (i == 56 || i == 63) {
//				boardArr[i] = Board.WHITE_ROOK;
//			} else if (i == 57 || i == 62) {
//				boardArr[i] = Board.WHITE_KNIGHT;
//			} else if (i == 58 || i == 61) {
//				boardArr[i] = Board.WHITE_BISHOP;
//			} else if (i == 59) {
//				boardArr[i] = Board.WHITE_QUEEN;
//			} else if (i == 60) {
//				boardArr[i] = Board.WHITE_KING;
//			} else {
//				boardArr[i] = Board.BLANK;
//			}
//		}
//	}
//	
//	BoardArray(Board[] boardArr){
//		this.boardArr = boardArr;
//	}
//	
//	public void setVal(int i, Board value) {
//		boardArr[i] = value;
//	}
//	
//	public Board get(int i) {
//		return boardArr[i];
//	}
//	
//	public boolean compareColour(int oldSquare, int newSquare) {
//		if (boardArr[newSquare] == Board.BLANK || boardArr[oldSquare] == Board.BLANK) {
//			return false;
//		} else if ((boardArr[newSquare] == Board.WHITE_PAWN 
//				|| boardArr[newSquare] == Board.WHITE_BISHOP 
//				|| boardArr[newSquare] == Board.WHITE_KNIGHT 
//				|| boardArr[newSquare] == Board.WHITE_ROOK 
//				|| boardArr[newSquare] == Board.WHITE_QUEEN 
//				|| boardArr[newSquare] == Board.WHITE_KING) 
//				&& 
//				(boardArr[oldSquare] == Board.WHITE_PAWN 
//				|| boardArr[oldSquare] == Board.WHITE_BISHOP 
//				|| boardArr[oldSquare] == Board.WHITE_KNIGHT 
//				|| boardArr[oldSquare] == Board.WHITE_ROOK 
//				|| boardArr[oldSquare] == Board.WHITE_QUEEN 
//				|| boardArr[oldSquare] == Board.WHITE_KING)) {
//			return true;
//		}  else if ((boardArr[newSquare] == Board.BLACK_PAWN || boardArr[newSquare] == Board.BLACK_BISHOP || boardArr[newSquare] == Board.BLACK_KNIGHT || boardArr[newSquare] == Board.BLACK_ROOK || boardArr[newSquare] == Board.BLACK_QUEEN || boardArr[newSquare] == Board.BLACK_KING) && (boardArr[oldSquare] == Board.BLACK_PAWN || boardArr[oldSquare] == Board.BLACK_BISHOP || boardArr[oldSquare] == Board.BLACK_KNIGHT || boardArr[oldSquare] == Board.BLACK_ROOK || boardArr[oldSquare] == Board.BLACK_QUEEN || boardArr[oldSquare] == Board.BLACK_KING)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	@Override public String toString() {
//		String returner = "";
//		for (int i = 0; i < 64; i++) {
//			returner += boardArr[i] + " ";
//			if (i % 8 == 0 && i != 0) {
//				returner += "\n";
//			}
//		}
//		returner += "\n";
//		return returner;
//	}
//	
//	public Piece.Colour getColour(int i) {
//		if (boardArr[i] == Board.BLANK) {
//			return null;
//		} else if (boardArr[i] == Board.WHITE_PAWN || boardArr[i] == Board.WHITE_KNIGHT || boardArr[i] == Board.WHITE_BISHOP || boardArr[i] == Board.WHITE_ROOK || boardArr[i] == Board.WHITE_QUEEN || boardArr[i] == Board.WHITE_KING) {
//			return Piece.Colour.WHITE;
//		} else {
//			return Piece.Colour.BLACK;
//		}
//	}
//	
//	public Board[] deepClone(){
//		Board[] copy = new Board[64];
//		for (int i = 0; i < 64; i++) {
//			copy[i] = boardArr[i];
//		}
//		return copy;
//	}
//	
//	public boolean canMove(int oldNum, int newNum, BoardArray board) {
//		if (board.get(oldNum) == Board.BLACK_PAWN) {
//			return new Pawn(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.BLACK_KNIGHT) {
//			return new Knight(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.BLACK_BISHOP) {
//			return new Bishop(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.BLACK_ROOK) {
//			return new Rook(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.BLACK_KING) {
//			return new King(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_PAWN) {
//			return new Pawn(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_KNIGHT) {
//			return new Knight(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_BISHOP) {
//			return new Bishop(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_ROOK) {
//			return new Rook(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_KING) {
//			return new King(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else if (board.get(oldNum) == Board.WHITE_QUEEN){
//			return new Queen(Piece.Colour.WHITE).canMove(oldNum, newNum, board);
//		} else {
//			return new Queen(Piece.Colour.BLACK).canMove(oldNum, newNum, board);
//		}
//	}
//	//takes the square array and converts it into an array of enums to make the program quicker
//	//while maintaining readability
//}
