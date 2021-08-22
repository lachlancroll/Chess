import java.util.LinkedList;
import java.awt.Color;
import java.lang.Math.*;

public class CheckPaths {
	
	public static boolean checkCheckWhite(String[] boardArr) {
		int kingPos = 0;
		for (int i = 0; i < 64; i ++) {
			if (boardArr[i] == "wK") {
				kingPos = i;
			}
		}
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("bk")) {
				if (CheckPaths.knightPath(false, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("bp")) {
				if (CheckPaths.blackPawnPath(false, boardArr, i, kingPos) == true){
					return true;
				}
			} else if (boardArr[i].equals("br")) {
				if (CheckPaths.rookPath(false, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("bb")) {
				if (CheckPaths.bishopPath(false, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("bq")) {
				if (CheckPaths.rookPath(false, false, boardArr, i, kingPos) == true || CheckPaths.bishopPath(false, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("bK")) {
				if (CheckPaths.kingPath(false, false, boardArr, i, kingPos) == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkCheckBlack(String[] boardArr) {
		int kingPos = 0;
		for (int i = 0; i < 64; i ++) {
			if (boardArr[i].equals("bK")) {
				kingPos = i;
			}
		}
		
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("wk")) {
				if (CheckPaths.knightPath(true, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("wp")) {
				if (CheckPaths.pawnPath(false, boardArr, i, kingPos) == true){
					return true;
				}
			} else if (boardArr[i].equals("wr")) {
				if (CheckPaths.rookPath(true, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("wb")) {
				if (CheckPaths.bishopPath(true, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("wq")) {
				if (CheckPaths.rookPath(true, false, boardArr, i, kingPos) == true || CheckPaths.bishopPath(true, false, boardArr, i, kingPos) == true) {
					return true;
				}
			} else if (boardArr[i].equals("wK")) {
				if (CheckPaths.kingPath(true, false, boardArr, i, kingPos) == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean putWhiteCheck(int oldSquare, int newSquare, String[]boardArr) {
		boolean isCheck = false;
		String converter = boardArr[oldSquare];
		String converter2 = boardArr[newSquare];
		boardArr[oldSquare] = "o";
		boardArr[newSquare] = "wq";
		if (checkCheckWhite(boardArr) == true) {
				isCheck = true;
		}
		boardArr[oldSquare] = converter;
		boardArr[newSquare] = converter2;
		
		if (isCheck == true) {
			return true;
		}
		return false;
	}
	
	public static boolean putBlackCheck(int oldSquare, int newSquare, String[]boardArr) {
		boolean isCheck = false;
		String converter = boardArr[oldSquare];
		String converter2 = boardArr[newSquare];
		boardArr[oldSquare] = "o";
		boardArr[newSquare] = "bq";
		if (checkCheckBlack(boardArr) == true) {
				isCheck = true;
		}
		boardArr[oldSquare] = converter;
		boardArr[newSquare] = converter2;
		
		if (isCheck == true) {
			return true;
		}
		return false;
	}
	
	public static boolean knightPath(boolean isWhite, boolean check, String[] boardArr, int oldSquare, int newSquare) {
		if ((newSquare != (oldSquare + 17)) && (newSquare != (oldSquare - 17)) && (newSquare != (oldSquare + 15)) && (newSquare != (oldSquare - 15)) && (newSquare != (oldSquare + 6)) && (newSquare != (oldSquare - 6)) && (newSquare != (oldSquare + 10)) && (newSquare != (oldSquare - 10))) {
			return false;
		}
		
		int col = oldSquare % 8;
		int row = oldSquare / 8;
		boolean isLegal = false;

		if (check == true) {
			if (isWhite == true) {
				if (putWhiteCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
				
			} else if (isWhite == false) {
				if (putBlackCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
			}
		}
		
		if (isWhite == true) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "bp" || boardArr[newSquare] == "br" ||boardArr[newSquare] == "bb" ||boardArr[newSquare] == "bk" ||boardArr[newSquare] == "bq" ||boardArr[newSquare] == "bK") {
				isLegal = true;
			} else {
				isLegal = false;
			}
		} else if (isWhite == false) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "wp" || boardArr[newSquare] == "wr" ||boardArr[newSquare] == "wb" ||boardArr[newSquare] == "wk" ||boardArr[newSquare] == "wq" ||boardArr[newSquare] == "wK") {
				isLegal = true;
			} else {
				isLegal = false;
			}
		}
		
		if (isLegal == true) {
		
		if (col == 6) {
			if ((newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 17)) || (newSquare == (oldSquare + 6)) || (newSquare == (oldSquare - 10))) {
				return true;
			} else {
				return false;
			}
		} else if (col == 7) {
			if ((newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 6)) || (newSquare == (oldSquare - 10))) {
				return true;
			} else {
				return false;
			}
		} else if (col == 0){
			if ((newSquare == (oldSquare + 17))  ||(newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10))) {
				return true;
			} else {
				return false;
			}
		} else if (col == 1) {
			if ((newSquare == (oldSquare + 17)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare - 15)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10))) {
				return true;
			} else {
				return false;
			}
		}
		if ((newSquare == (oldSquare - 15)) || (newSquare == (oldSquare - 17)) || (newSquare == (oldSquare + 15)) || (newSquare == (oldSquare + 17)) || (newSquare == (oldSquare - 10)) || (newSquare == (oldSquare - 6)) || (newSquare == (oldSquare + 10)) || (newSquare == (oldSquare + 6))) {
			return true;
		} else {
			return false;
		}
		}
		return false;
	}
	
	public static boolean kingPath(boolean isWhite, boolean check, String[] boardArr, int oldSquare, int newSquare) {
		int col = oldSquare % 8;
		int row = oldSquare / 8;
		boolean isLegal = false;
		
		if ((newSquare != (oldSquare + 8)) && (newSquare != (oldSquare - 8)) && (newSquare != (oldSquare + 9)) && (newSquare != (oldSquare - 9)) && (newSquare != (oldSquare + 7)) && (newSquare != (oldSquare - 7)) && (newSquare != (oldSquare + 1)) && (newSquare != (oldSquare - 1)) && (newSquare != (oldSquare + 2)) && (newSquare != (oldSquare - 2))) {
			return false;
		}
		
		if (isWhite == true) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "bp" || boardArr[newSquare] == "br" ||boardArr[newSquare] == "bb" ||boardArr[newSquare] == "bk" ||boardArr[newSquare] == "bq" ||boardArr[newSquare] == "bK") {
				isLegal = true;
			} else {
				return false;
			}
		} else if (isWhite == false) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "wp" || boardArr[newSquare] == "wr" ||boardArr[newSquare] == "wb" ||boardArr[newSquare] == "wk" ||boardArr[newSquare] == "wq" ||boardArr[newSquare] == "wK") {
				isLegal = true;
			} else {
				return false;
			}
		} 
		
		if (check == true) {
		if (isWhite == true) {
			boolean isCheck = false;
			String converter = boardArr[oldSquare];
			String converter2 = boardArr[newSquare];
			boardArr[oldSquare] = "o";
			boardArr[newSquare] = "wK";
			if (checkCheckWhite(boardArr) == true) {
					isCheck = true;
			}
			boardArr[oldSquare] = converter;
			boardArr[newSquare] = converter2;
			
			if (isCheck == true) {
				return false;
			}
		} else if (isWhite == false) {
			boolean isCheck = false;
			String converter = boardArr[oldSquare];
			String converter2 = boardArr[newSquare];
			boardArr[oldSquare] = "o";
			boardArr[newSquare] = "bK";
			if (checkCheckBlack(boardArr) == true) {
					isCheck = true;
			}
			boardArr[oldSquare] = converter;
			boardArr[newSquare] = converter2;
			
			if (isCheck == true) {
				return false;
			}
		}
		}
		
		if (isLegal == true) {
			if (isWhite == true) {
				if (newSquare == 58 && oldSquare == 60 && Board.wKingMoved == false && Board.wRookMovedL == false && Board.wCastled == false && boardArr[57].equals("o") && boardArr[58].equals("o") && boardArr[59].equals("o")) {
					return true;
				}
				if (newSquare == 62 && oldSquare == 60 && Board.wKingMoved == false && Board.wRookMovedR == false && Board.wCastled == false && boardArr[62].equals("o") && boardArr[61].equals("o")) {
					return true;
				}
			} else if (isWhite == false){
				if (newSquare == 2 && oldSquare == 4 && Board.bKingMoved == false && Board.bRookMovedL == false && Board.bCastled == false && boardArr[2].equals("o") && boardArr[3].equals("o") && boardArr[1].equals("o")) {
					return true;
				}
				if (newSquare == 6 && oldSquare == 4 && Board.bKingMoved == false && Board.bRookMovedR == false && Board.bCastled == false && boardArr[6].equals("o") && boardArr[5].equals("o")) {
					return true;
				}
			}
			
		if (col == 7) {
			if ((newSquare == (oldSquare + 8)) || (newSquare == (oldSquare - 8)) || (newSquare == (oldSquare - 9)) || (newSquare == (oldSquare + 7)) || (newSquare == (oldSquare - 1))) {
				return true;
			} else {
				return false;
			}
		} else if (col == 0) {
			if ((newSquare == (oldSquare + 8)) || (newSquare == (oldSquare - 8)) || (newSquare == (oldSquare + 9)) || (newSquare == (oldSquare - 7)) || (newSquare == (oldSquare + 1))) {
				return true;
			} else {
				return false;
			}
		}
		
		if ((newSquare == (oldSquare + 8)) || (newSquare == (oldSquare - 8)) || (newSquare == (oldSquare + 9)) || (newSquare == (oldSquare - 9)) || (newSquare == (oldSquare + 7)) || (newSquare == (oldSquare - 7)) || (newSquare == (oldSquare + 1)) || (newSquare == (oldSquare - 1))) {
			return true;
		}
		}
		return false;
	}
	
	public static boolean pawnPath(boolean check, String[] boardArr, int oldSquare, int newSquare) {
		
		if (newSquare != oldSquare - 8 && newSquare != oldSquare - 7 && newSquare != oldSquare - 9 && newSquare != oldSquare - 16) {
			return false;
		}
		if ( boardArr[newSquare] == "wp" || boardArr[newSquare] == "wr" ||boardArr[newSquare] == "wb" ||boardArr[newSquare] == "wk" ||boardArr[newSquare] == "wq" ||boardArr[newSquare] == "wK") {
			return false;
		}
		
		if (check == true) {
			if (putWhiteCheck( oldSquare,  newSquare, boardArr) == true) {
				return false;
			}
		}
		
		int col = oldSquare % 8;
		int row = oldSquare / 8;
		
		if (boardArr[newSquare].equals("o")) {
			if (newSquare == (oldSquare - 8)) {
				return true;
			} else if (row == 6 && newSquare == (oldSquare - 16)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (col == 0 && (newSquare == (oldSquare - 7))) {
				return true;
			} else if (col == 7 && (newSquare == (oldSquare - 9))) {
				return true;
			} else if (col > 0 && col < 7 && (newSquare == (oldSquare - 9) || newSquare == (oldSquare - 7))) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	//BLACK PATH
	public static boolean blackPawnPath(boolean check, String[] boardArr, int oldSquare, int newSquare) {
		
		if (newSquare != oldSquare + 8 && newSquare != oldSquare + 7 && newSquare != oldSquare + 9 && newSquare != oldSquare + 16) {
			return false;
		}
		
		if (boardArr[newSquare] == "bp" || boardArr[newSquare] == "br" ||boardArr[newSquare] == "bb" ||boardArr[newSquare] == "bk" ||boardArr[newSquare] == "bq" ||boardArr[newSquare] == "bK") {
			return false;
		}
		
		if (check == true) {
			if (putBlackCheck(oldSquare, newSquare, boardArr) == true) {
				return false;
			}
		}
		
		int col = oldSquare % 8;
		int row = oldSquare / 8;
		
		if (boardArr[newSquare].equals("o")) {
			if (newSquare == (oldSquare + 8)) {
				return true;
			} else if (row == 1 && newSquare == (oldSquare + 16)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (col == 7 && (newSquare == (oldSquare + 7))) {
				return true;
			} else if (col == 0 && (newSquare == (oldSquare + 9))) {
				return true;
			} else if (col > 0 && col < 7 && (newSquare == (oldSquare + 9) || newSquare == (oldSquare + 7))) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean rookPath(boolean isWhite, boolean check, String[] boardArr, int oldSquare, int newSquare) {

		if ((newSquare % 8 != oldSquare % 8) && (newSquare / 8 != oldSquare / 8)) {
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
		boolean isLegal = false;
		
		//do quick check if false to save time looking
		
		if (check == true) {
			if (isWhite == true) {
				if (putWhiteCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
			} else if (isWhite == false) {
				if (putBlackCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
			}
		}
		
		if (isWhite == true) {
			if (!(boardArr[newSquare] == "o" || boardArr[newSquare] == "bp" || boardArr[newSquare] == "br" ||boardArr[newSquare] == "bb" ||boardArr[newSquare] == "bk" ||boardArr[newSquare] == "bq" ||boardArr[newSquare] == "bK")) {
				return false;
			}
		} else if (isWhite == false) {
			if (!(boardArr[newSquare] == "o" || boardArr[newSquare] == "wp" || boardArr[newSquare] == "wr" ||boardArr[newSquare] == "wb" ||boardArr[newSquare] == "wk" ||boardArr[newSquare] == "wq" ||boardArr[newSquare] == "wK")) {
				return false;
			}
		}
			
		// find COLS
		
		//row = /
		//col = %
		col = oldSquare % 8;
		row = oldSquare / 8;
		int newCol = newSquare % 8;
		int newRow = newSquare / 8;
		
		
		//finding max and min rows
		
		for (int i = 0; i < 8; i++) {
			if (foundMinRow == false && !boardArr[(i*8) + col].equals("o") && ((i*8) + col) != oldSquare) {
				minRow = i;
			} else if ((i*8) + col == oldSquare) {
				foundMinRow = true;
			} else if (foundMinRow == true && !boardArr[(i*8) + col].equals("o") && foundMaxRow == false) {
				maxRow = i;
				foundMaxRow = true;
			} else if (foundMaxRow == false && i == 7) {
				maxRow = 7;
			}
			
		}
		
		// finding min and max cols
		for (int j = 0; j < 8; j++) {
			if (foundMinCol == false && !boardArr[(row*8) + j].equals("o") && ((row*8) + j) < oldSquare) {
				minCol = j;
			} else if (((row*8) + j) == oldSquare) {
				foundMinCol = true;
			} else if (foundMinCol == true && !boardArr[(row*8) + j].equals("o") && foundMaxCol == false) {
				maxCol = j;
				foundMaxCol = true;
			} else if (foundMaxCol == false && j == 7) {
				maxCol = 7;
			}
			
		}
		
		
		if (col == newCol && newRow <= maxRow && newRow >= minRow) {
			return true;
		}
		if (row == newRow && newCol <= maxCol && newCol >= minCol) {
			return true;
		}
	
		return false;
	}
	
	public static boolean bishopPath(boolean isWhite, boolean check, String[] boardArr, int oldSquare, int newSquare) {
		if (newSquare % 8 == oldSquare % 8 && newSquare / 8 == oldSquare / 8) {
			return false;
		}
		if (Math.abs((newSquare % 8) - (oldSquare % 8)) != Math.abs((newSquare / 8) - (oldSquare / 8))) {
			return false;
		}
		
		int col = 0;
		int row = 0;
		int ogrow = 0;
		int ogcol = 0;
		int maxCol = 7; // /
		int maxRow = 7;
		int minCol = 0;
		int minRow = 0;
		boolean haveMax = false;
		boolean haveMin = false;
		boolean finished = false;
		
		int maxCol_ = 7; //  \
		int maxRow_ = 7;
		int minCol_ = 0;
		int minRow_ = 0;
		boolean haveMax_ = false;
		boolean haveMin_ = false;
		boolean isLegal = false;
		
		if (check == true) {
			if (isWhite == true) {
				if (putWhiteCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
			} else if (isWhite == false) {
				if (putBlackCheck( oldSquare,  newSquare, boardArr) == true) {
					return false;
				}
			}
		}
		
		
		if (isWhite == true) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "bp" || boardArr[newSquare] == "br" ||boardArr[newSquare] == "bb" ||boardArr[newSquare] == "bk" ||boardArr[newSquare] == "bq" ||boardArr[newSquare] == "bK") {
				isLegal = true;
			} else {
				isLegal = false;
			}
		} else if (isWhite == false) {
			if (boardArr[newSquare] == "o" || boardArr[newSquare] == "wp" || boardArr[newSquare] == "wr" ||boardArr[newSquare] == "wb" ||boardArr[newSquare] == "wk" ||boardArr[newSquare] == "wq" ||boardArr[newSquare] == "wK") {
				isLegal = true;
			} else {
				isLegal = false;
			}
		}
		
		if (isLegal == true) {
			
		//finding the row and col to start counting
		ogcol = oldSquare % 8;
		ogrow = oldSquare / 8;
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
			if (haveMax == false && !boardArr[(row*8)+ col].equals("o")) {
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
			if (haveMin == false && !boardArr[(row*8)+ col].equals("o")) {
				minRow = row;
				maxCol = col;
				haveMin = true;
			} else if (haveMin == false && (row == 0|| col == 7)) {
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
			if (haveMax_ == false && !boardArr[(row*8)+ col].equals("o")) {
				maxRow_ = row;
				maxCol_ = col;
				haveMax_ = true;
			} else if (haveMax_ == false && (row == 7|| col == 7)) {
				maxRow_ = row;
				maxCol_ = col;
				haveMax_ = true;
			}
		}
		
		row = ogrow;
		col = ogcol;
		
		while (row > 0 && col > 0) {
			row--;
			col--;
			if (haveMin_ == false && !boardArr[(row*8)+ col].equals("o")) {
				minRow_ = row;
				minCol_ = col;
				haveMin_ = true;
			} else if (haveMin_ == false && (row == 0|| col == 0)) {
				minRow_ = row;
				minCol_ = col;
				haveMin_ = true;
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
				return true;
			}
		}
			
		row = ogrow;
		col = ogcol;
		
		while (row < 7 && col < 7) {
			row++;
			col++;
			if (((row*8) + col) == newSquare && newSquare > ((maxRow_*8) + maxCol_)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				return true;
			}
		}
		
		row = ogrow;
		col = ogcol;
		
		while (row > 0 && col > 0) {
			row--;
			col--;
			if (((row*8) + col) == newSquare && newSquare < ((minRow_*8) + minCol_)){
				return false;
			}
			if (((row*8) + col) == newSquare) {
				return true;
			}
		}
		
		
		// - 7 or - 9
		}
		return false;
	}
	
	
}
