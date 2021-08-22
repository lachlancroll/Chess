import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.Copies;
import javax.swing.*;

import java.util.LinkedList;
import java.util.Random;

public class Board extends JFrame implements ActionListener{
	
	boolean whiteMove = true;
	// fix checkmate which isn't working after a certain amount of moves
	// need to stop ai from repeating turns
	// need to checkCheck for castling so that you can't castle into check
	
	static ImageIcon pawn = new ImageIcon("white pawn.png");
	static ImageIcon rook = new ImageIcon("white rook.png");
	static ImageIcon bishop = new ImageIcon("white bishop.png");
	static ImageIcon queen = new ImageIcon("white queen.png");
	static ImageIcon knight = new ImageIcon("white knight.png");
	static ImageIcon king = new ImageIcon("white king.png");
	static ImageIcon Bpawn = new ImageIcon("black pawn.png");
	static ImageIcon Brook = new ImageIcon("black rook.png");
	static ImageIcon Bbishop = new ImageIcon("black bishop.png");
	static ImageIcon Bqueen = new ImageIcon("black queen.png");
	static ImageIcon Bknight = new ImageIcon("black knight.png");
	static ImageIcon Bking = new ImageIcon("black king.png");
	
	static int countM = 2;
	static int countB = 2;
	
	static LinkedList<Integer> path = new LinkedList<Integer>();
	//static LinkedList<Integer> pieces = new LinkedList<Integer>();
	
	static JButton[] buttons = new JButton[64];
	static Color light = new Color(220, 220, 220);
	static Color dark = new Color(50, 50, 50);
	
	static int pawnFinish;
	static int pawnFinishOld;
	static boolean pawnFinished = false;
	static JFrame pawnS = new JFrame();
	static boolean isBlackPawn = false;
	
	static boolean isWhiteCkheck = false;
	static boolean isBlackCkheck = false;
	
	static boolean wKingMoved = false;
	static boolean wRookMovedR= false;
	static boolean wRookMovedL= false;
	static boolean wCastled = false;
	
	static boolean bKingMoved = false;
	static boolean bRookMovedR = false;
	static boolean bRookMovedL = false;
	static boolean bCastled = false;
	
	static JButton rookB = new JButton();
	static JButton knightB = new JButton();
	static JButton bishopB = new JButton();
	static JButton queenB = new JButton();
	
	//Implement un passant and castle
	
	//BOOLS FOR MOVES
	static boolean hasPawn = false;
	static boolean hasRook = false;
	static boolean hasBishop = false;
	static boolean hasQueen = false;
	static boolean hasKnight = false;
	static boolean hasKing = false;
	
	static boolean hasPawnB = false;
	static boolean hasRookB = false;
	static boolean hasBishopB = false;
	static boolean hasQueenB = false;
	static boolean hasKnightB = false;
	static boolean hasKingB = false;
	
	boolean comp = false;
	
	static String[] boardArr = new String[64];
	static boolean canMove = false;
	//change replacer to oldSquare
	static int replacer;
	
	static JButton compB = new JButton();
	static JButton p2 = new JButton();
	static JFrame chooseComp = new JFrame();
	
	
	//MAIN!!
	Board() {
		
		compB.setBounds(20, 40, 160, 80);
		compB.addActionListener(this);
		compB.setBackground(light);
		compB.setText("1 player");
		
		p2.setBounds(200, 40, 160, 80);
		p2.addActionListener(this);
		p2.setBackground(light);
		p2.setText("2 player");
		
		chooseComp.setVisible(true);
		chooseComp.setSize(400, 200);
		chooseComp.setResizable(false);
		chooseComp.setLayout(null);
		chooseComp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel label = new JLabel();
		label.setText("Choose a new peice");
		label.setFont(new Font("Calibri", 10, 20));
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		chooseComp.add(compB);
		chooseComp.add(p2);
		
		this.setVisible(true);
		this.setTitle("chess");
		this.setSize(640, 640);
		//this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		chooseComp.toFront();
		
		//setting up the board
		for (int i = 0; i < 64; i++) {
			if (i > 47 && i < 56) {
				boardArr[i] = "wp";
			} else if (i == 56 || i == 63) {
				boardArr[i] = "wr";
			} else if (i == 61 || i == 58){
				boardArr[i] = "wb";
			} else if (i == 59) {
				boardArr[i] = "wq";
			} else if (i == 57 || i == 62) {
				boardArr[i] = "wk";
			} else if (i == 60){
				boardArr[i] = "wK";
			} else if (i > 7 && i < 16) {
				boardArr[i] = "bp";
			} else if (i == 0 || i == 7) {
				boardArr[i] = "br";
			} else if (i == 1 || i == 6) {
				boardArr[i] = "bk";
			} else if (i == 2 || i == 5) {
				boardArr[i] = "bb";
			} else if (i == 3) {
				boardArr[i] = "bq";
			} else if (i == 4) {
				boardArr[i] = "bK";
			} else {
				boardArr[i] = "o";
			}
				
		}
		
		this.setLayout(new GridLayout(8,8));
		this.setResizable(false);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int num = (i*8) + j;
				buttons[(i*8) + j] = new JButton();
				buttons[(i*8) + j].setFocusable(false);
				buttons[(i*8) + j].setText("" + num);
				if((j + i) % 2 == 0) {
					buttons[(i*8) + j].setBackground(light);
					buttons[(i*8) + j].setForeground(dark);
				} else {
					buttons[(i*8) + j].setBackground(dark);
					buttons[(i*8) + j].setForeground(light);
				}
				if (boardArr[num].equals("wp")) {
					buttons[num].setIcon(pawn);
				} else if (boardArr[num].equals("wr")) {
					buttons[num].setIcon(rook);
				} else if (boardArr[num].equals("wb")) {
					buttons[num].setIcon(bishop);
				} else if (boardArr[num].equals("wq")) {
					buttons[num].setIcon(queen);
				} else if (boardArr[num].equals("wk")) {
					buttons[num].setIcon(knight);
				} else if (boardArr[num].equals("wK")) {
					buttons[num].setIcon(king);
				} else if (boardArr[num].equals("bp")) {
					buttons[num].setIcon(Bpawn);
				} else if (boardArr[num].equals("br")) {
					buttons[num].setIcon(Brook);
				} else if (boardArr[num].equals("bb")) {
					buttons[num].setIcon(Bbishop);
				} else if (boardArr[num].equals("bq")) {
					buttons[num].setIcon(Bqueen);
				} else if (boardArr[num].equals("bk")) {
					buttons[num].setIcon(Bknight);
				} else if (boardArr[num].equals("bK")) {
					buttons[num].setIcon(Bking);
				}
				
				buttons[(i*8) + j].addActionListener(this);
				this.add(buttons[(i*8) + j]);
			}
		}
	}
	
	public void resetButtonColour() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if((j + i) % 2 == 0) {
					buttons[(i*8) + j].setBackground(light);
					buttons[(i*8) + j].setForeground(dark);
				} else {
					buttons[(i*8) + j].setBackground(dark);
					buttons[(i*8) + j].setForeground(light);
				}
			}
		}
	}
	
	public void pawnFinisher(int newSquare) {
		queenB.setBounds(20, 40, 80, 80);
		queenB.addActionListener(this);
		bishopB.setBounds(120, 40, 80, 80);
		bishopB.addActionListener(this);
		knightB.setBounds(220, 40, 80, 80);
		knightB.addActionListener(this);
		rookB.setBounds(320, 40, 80, 80);
		rookB.addActionListener(this);
		
		if (isBlackPawn == true) {
			queenB.setIcon(Bqueen);
			rookB.setIcon(Brook);
			knightB.setIcon(Bknight);
			bishopB.setIcon(Bbishop);
		} else {
			queenB.setIcon(queen);
			rookB.setIcon(rook);
			knightB.setIcon(knight);
			bishopB.setIcon(bishop);
		}
		
		
		pawnS.setVisible(true);
		pawnS.setSize(440, 200);
		pawnS.setResizable(false);
		pawnS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
			if ((newSquare % 2 == 0 && newSquare <= 7 && newSquare >= 0) || (newSquare % 2 == 1 && newSquare <= 63 && newSquare >= 56)) {
				queenB.setBackground(light);
				knightB.setBackground(light);
				bishopB.setBackground(light);
				rookB.setBackground(light);
			} else {
				queenB.setBackground(dark);
				knightB.setBackground(dark);
				bishopB.setBackground(dark);
				rookB.setBackground(dark);
			}
		
		JLabel label = new JLabel();
		label.setText("Choose a new peice");
		label.setFont(new Font("Calibri", 10, 20));
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		pawnS.add(queenB);
		pawnS.add(knightB);
		pawnS.add(bishopB);
		pawnS.add(rookB);
		pawnS.add(label);
	}
	
	public void blackCheckMate() {
		int countMove = 0;
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("bk")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bp")) {
				LinkedList<Integer> pawnMoves = new LinkedList<Integer>();
				pawnMoves.add(i + 8);
				pawnMoves.add(i + 7);
				pawnMoves.add(i + 9);
				pawnMoves.add(i + 16);
				for (int j = 0; j < pawnMoves.size(); j ++) {
					if (CheckPaths.blackPawnPath(true, boardArr, i, pawnMoves.get(j)) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("br")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bb")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bq")) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(false, true, boardArr, i, j) == true || CheckPaths.rookPath(false, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bK")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.kingPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			}
		}
		if (countMove == 0) {
			JFrame mate = new JFrame();
			mate.setVisible(true);
			mate.setBounds(500, 500, 300, 100);
			
			JLabel cLabel = new JLabel();
			cLabel.setText("Check Mate, white wins");
			cLabel.setBounds(50, 20, 150, 50);
			
			mate.add(cLabel);
			
		}
	}
	
	public static boolean boolBlackCheckMate() {
		int countMove = 0;
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("bk")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bp")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.blackPawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("br")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bb")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bq")) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(false, true, boardArr, i, j) == true || CheckPaths.rookPath(false, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("bK")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.kingPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			}
		}
		if (countMove == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int countMoves(int oldSquare){
		int counter = 0;
		if (boardArr[oldSquare].equals("bp")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.blackPawnPath(true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		} else if (boardArr[oldSquare].equals("bb")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.bishopPath(false, true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		} else if (boardArr[oldSquare].equals("br")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.rookPath(false, true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		} else if (boardArr[oldSquare].equals("bk")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.knightPath(false, true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		} else if (boardArr[oldSquare].equals("bK")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.kingPath(false, true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		} else if (boardArr[oldSquare].equals("bq")) {
			for (int i = 0; i < 64; i++) {
				if (CheckPaths.bishopPath(false, true, boardArr, oldSquare, i) || CheckPaths.rookPath(false, true, boardArr, oldSquare, i)) {
					counter++;
				}
			}
		}
		return counter;
	}
		
	public void whiteCheckMate() {
		int countMove = 0;
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("wk")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wp")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.pawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wr")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wb")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wq")) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(true, true, boardArr, i, j) == true || CheckPaths.rookPath(true, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wK")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.kingPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			}
		}
		if (countMove == 0) {
			JFrame Bmate = new JFrame();
			Bmate.setVisible(true);
			Bmate.setBounds(500, 500, 300, 100);
			
			JLabel bLabel = new JLabel();
			bLabel.setText("Check Mate, black wins");
			bLabel.setBounds(50, 20, 150, 50);
			
			Bmate.add(bLabel);
			
		}
	}
	
	public static boolean boolWhiteCheckMate() {
		int countMove = 0;
		for (int i = 0; i < 64; i++) {
			if (boardArr[i].equals("wk")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wp")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.pawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wr")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wb")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wq")) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(true, true, boardArr, i, j) == true || CheckPaths.rookPath(true, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i].equals("wK")) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.kingPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			}
		}
		if (countMove == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int turnScorer(int newSquare) {
		
		if (boardArr[newSquare].equals("wp")) {
			return 1;
		} else if (boardArr[newSquare].equals("wr")) {
			return 5;
		} else if (boardArr[newSquare].equals("wb")) {
			return 3;
		} else if (boardArr[newSquare].equals("wk")) {
			return 3;
		} else if (boardArr[newSquare].equals("wq")) {
			return 9;
		} else if (boardArr[newSquare].equals("wk")) {
			return 100;
		}
		return 0;
	}
	
	public static int whiteTurnScorer(int newSquare) {
		if (boardArr[newSquare].equals("bp")) {
			return 1;
		} else if (boardArr[newSquare].equals("br")) {
			return 5;
		} else if (boardArr[newSquare].equals("bb")) {
			return 3;
		} else if (boardArr[newSquare].equals("bk")) {
			return 3;
		} else if (boardArr[newSquare].equals("bq")) {
			return 9;
		} else if (boardArr[newSquare].equals("bk")) {
			return 100;
		}
		return 0;
	}
	
	public static int[] bestBlackMove(boolean last) {
		int bestMoveVal = Integer.MIN_VALUE;
		int bestMoveold = 0;
		int bestMovenew = 0;
		
		LinkedList<Integer> nonPieces = new LinkedList<Integer>();
		LinkedList<Integer> pieces = new LinkedList<Integer>();
		int minus = 0;
		
			if (boolBlackCheckMate() == true) {
				int[] finisher = {0, 0, -1000};
				return finisher;
			}
		
		for (int i = 0; i < 64; i++) {
			if (!(boardArr[i].equals("bp") || boardArr[i].equals("br") || boardArr[i].equals("bk") || boardArr[i].equals("bb") || boardArr[i].equals("bq") || boardArr[i].equals("bK"))) {
				nonPieces.add(i);
			} else if (!boardArr[i].equals("o")) {
				pieces.add(i);
			}
		}
		
		for (int i = 0; i < pieces.size(); i++) {
			if (boardArr[pieces.get(i)].equals("br")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "br";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "br";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("bk")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.knightPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bk";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bk";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("bb")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.bishopPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bb";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bb";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("bq")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) == true || CheckPaths.bishopPath(false, true, boardArr, (int) pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bq";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bq";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("bK")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.kingPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bK";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bK";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						//make sure it checks for check so that it cant just castle into check
					} else if (bKingMoved == false && bRookMovedR == false && bCastled == false && pieces.get(i) == 4 && nonPieces.get(j) == 6 && boardArr[nonPieces.get(j) - 1].equals("o")) {
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("bp")) {
				// check the j = i
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.blackPawnPath( true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (last == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bp";
							minus = bestWhiteMove(false)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						} else if (last == true) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "bp";
							minus = wFinisher()[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			}
		}
		int[] finisher = {bestMoveold, bestMovenew, bestMoveVal};
		return finisher;
	}

	public static int[] bestWhiteMove(boolean finish) {
		int bestMoveVal = Integer.MIN_VALUE;
		int bestMoveold = 0;
		int bestMovenew = 0;
		
		LinkedList<Integer> nonPieces = new LinkedList<Integer>();
		LinkedList<Integer> pieces = new LinkedList<Integer>();
		int minus = 0;
		
		if (boolWhiteCheckMate() == true) {
			int[] finisher = {0, 0, -1000};
			return finisher;
		}
		for (int i = 0; i < 64; i++) {
			if (!(boardArr[i].equals("wp") || boardArr[i].equals("wr") || boardArr[i].equals("wk") || boardArr[i].equals("wb") || boardArr[i].equals("wq") || boardArr[i].equals("wK"))) {
				nonPieces.add(i);
			} else if (!boardArr[i].equals("o")) {
				pieces.add(i);
			}
		}
		
		for (int i = 0; i < pieces.size(); i++) {
			if (boardArr[pieces.get(i)].equals("wr")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wr";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wk")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.knightPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wk";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = (int) pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wb")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wb";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wq")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true || CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wq";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wK")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.kingPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wK";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					} else if (wKingMoved == false && wRookMovedR == false && wCastled == false && pieces.get(i) == 60 && nonPieces.get(j) == 62 && boardArr[nonPieces.get(j) - 1].equals("o")) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wK";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(pieces.get(i));
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wp")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.pawnPath(true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
						if (finish == false) {
							String converter1 = boardArr[pieces.get(i)];
							String converter2 = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = "o";
							boardArr[nonPieces.get(j)] = "wp";
							minus = bestBlackMove(true)[2];
							boardArr[pieces.get(i)] = converter1;
							boardArr[nonPieces.get(j)] = converter2;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			}
		}
		int[] finisher = {bestMoveold, bestMovenew, bestMoveVal};
		return finisher;
	}
	
	public static int[] wFinisher() {
		int bestMoveVal = Integer.MIN_VALUE;
		int bestMoveold = 0;
		int bestMovenew = 0;
		
		LinkedList<Integer> nonPieces = new LinkedList<Integer>();
		LinkedList<Integer> pieces = new LinkedList<Integer>();
		int minus = 0;
		
		
		if (boolWhiteCheckMate() == true) {
			int[] finisher = {0, 0, -1000};
			return finisher;
		}
		for (int i = 0; i < 64; i++) {
			if ((boardArr[i].equals("bp") || boardArr[i].equals("br") || boardArr[i].equals("bk") || boardArr[i].equals("bb") || boardArr[i].equals("bq") || boardArr[i].equals("bK"))) {
				nonPieces.add(i);
			} else if (!boardArr[i].equals("o")) {
				pieces.add(i);
			}
		}
		
		
		for (int i = 0; i < pieces.size(); i++) {
			if (boardArr[pieces.get(i)].equals("wr")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[(int) pieces.get(i)].equals("wk")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.knightPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wb")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wq")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true || CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wK")) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.kingPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)].equals("wp")) {
				LinkedList<Integer> pawns = new LinkedList<Integer>();
				pawns.add(pieces.get(i) - 8);
				if (pieces.get(i) > 47) {
					pawns.add(pieces.get(i) - 16);
				}
				pawns.add(pieces.get(i) - 9);
				pawns.add(pieces.get(i) - 7);
				for (int j = 0; j < pawns.size(); j++) {
					if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
						if (CheckPaths.pawnPath(true, boardArr, pieces.get(i), pawns.get(j)) == true) {
							bestMoveVal = Board.whiteTurnScorer(pawns.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = pawns.get(j);
						}
					}
				}
			}
		}
		int[] finisher = {bestMoveold, bestMovenew, bestMoveVal};
		return finisher;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == p2) {
			comp = false;
			chooseComp.dispose();
		}
		if (e.getSource() == compB) {
			comp = true;
			chooseComp.dispose();
		}
		if (e.getSource() == rookB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(Brook);
					boardArr[pawnFinish] = "br";
				} else {
					buttons[pawnFinish].setIcon(rook);
					boardArr[pawnFinish] = "wr";
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = "o";
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == queenB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(Bqueen);
					boardArr[pawnFinish] = "bq";
				} else {
					buttons[pawnFinish].setIcon(queen);
					boardArr[pawnFinish] = "wq";
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = "o";
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == knightB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(Bknight);
					boardArr[pawnFinish] = "bk";
				} else {
					buttons[pawnFinish].setIcon(knight);
					boardArr[pawnFinish] = "wk";
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = "o";
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == bishopB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(Bbishop);
					boardArr[pawnFinish] = "bb";
				} else {
					buttons[pawnFinish].setIcon(bishop);
					boardArr[pawnFinish] = "wb";
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = "o";
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		
		//WHITE MOVES
		if (whiteMove == true) {
			//whiteCheckMate();
		
		if (pawnFinished == false) {
			for (int i = 0; i < 64; i++)
			if (e.getSource() == buttons[i]) {
				//SETUP WHITE
				if (boardArr[i].equals("wp")) {
					replacer = i;
					hasPawn = true;
					hasRook = false;
					hasQueen = false;
					hasKnight = false;
					hasBishop = false;
					hasKing = false;
					//checkCheck();
					resetButtonColour();
					path.clear();
					if (i > 15) {
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 8)) {
							path.add(i - 8);
							if (buttons[i - 8].getBackground() == light) {
								buttons[i - 8].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 8].setBackground(new Color(180, 167, 0));
							}
						} 
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 16)) {
							path.add(i - 16);
							if (buttons[i - 16].getBackground() == light) {
								buttons[i - 16].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 16].setBackground(new Color(180, 167, 0));
							}
						}
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 7)) {
							path.add(i - 7);
							if (buttons[i - 7].getBackground() == light) {
								buttons[i - 7].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 7].setBackground(new Color(180, 167, 0));
							}
						}
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 9)) {
							path.add(i - 9);
							if (buttons[i - 9].getBackground() == light) {
								buttons[i - 9].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 9].setBackground(new Color(180, 167, 0));
							}
						}
					} else {
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 8)) {
							path.add(i - 8);
							if (buttons[i - 8].getBackground() == light) {
								buttons[i - 8].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 8].setBackground(new Color(180, 167, 0));
							}
						} 
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 9)) {
							path.add(i - 9);
							if (buttons[i - 9].getBackground() == light) {
								buttons[i - 9].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 9].setBackground(new Color(180, 167, 0));
							}
						}
						if (CheckPaths.pawnPath(true, boardArr, replacer, replacer - 7)) {
							path.add(i - 7);
							if (buttons[i - 7].getBackground() == light) {
								buttons[i - 7].setBackground(new Color(230, 207, 0));
							} else {
								buttons[i - 7].setBackground(new Color(180, 167, 0));
							}
						}
					}
					
				} else if (boardArr[i].equals("wr")) {
					replacer = i;
					hasPawn = false;
					hasRook = true;
					hasBishop = false;
					hasKnight = false;
					hasQueen = false;
					hasKing = false;
					resetButtonColour();
					path.clear();
					for (int j = 0; j < 64; j++) {
						if (CheckPaths.rookPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
					}
				} else if (boardArr[i].equals("wb")) {
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasQueen = false;
					hasBishop = true;
					hasKnight = false;
					hasKing = false;
					resetButtonColour();
					path.clear();
					for (int j = 0; j < 64; j++) {
						if (CheckPaths.bishopPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
					}
				} else if (boardArr[i].equals("wq")) {
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasBishop = false;
					hasQueen = true;
					hasKnight = false;
					hasKing = false;
					resetButtonColour();
					path.clear();
					for (int j = 0; j < 64; j++) {
						if (CheckPaths.bishopPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
						if (CheckPaths.rookPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
					}
				} else if (boardArr[i].equals("wk")) {
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasQueen = false;
					hasBishop = false;
					hasKnight = true;
					hasKing = false;
					resetButtonColour();
					path.clear();
					for (int j = 0; j < 64; j++) {
						if (CheckPaths.knightPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
					}
				} else if (boardArr[i].equals("wK")){
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasQueen = false;
					hasBishop = false;
					hasKnight = false;
					hasKing = true;
					resetButtonColour();
					path.clear();
					for (int j = 0; j < 64; j++) {
						if (CheckPaths.kingPath(true, true, boardArr, replacer, j)) {
							path.add(j);
							if (buttons[j].getBackground() == light) {
								buttons[j].setBackground(new Color(230, 207, 0));
							} else {
								buttons[j].setBackground(new Color(180, 167, 0));
							}
						}
						
					}
				//PAWN MOVES
					
				} else if (hasPawn == true) {
					boolean canMove = (path.contains(i));
					if (canMove == true && i >= 0 && i <= 7) {
						pawnFinish = i;
						pawnFinishOld = replacer;
						pawnFinished = true;
						pawnFinisher(i);
						whiteMove = false;
						hasPawn = false;
					} else if (canMove == true) {
						buttons[i].setIcon(pawn);
						boardArr[i] = "wp";
						hasPawn = false;
						resetButtonColour();
						buttons[replacer].setIcon(null);
						boardArr[replacer] = "o";
						whiteMove = false;
					}
					
				//ROOK MOVES
					
				} else if (hasRook == true && path.contains(i)) {
					buttons[i].setIcon(rook);
					boardArr[i] = "wr";
					hasRook = false;
					resetButtonColour();
					if (replacer == 63) {
						wRookMovedR = true;
					} else if (replacer == 56) {
						wRookMovedL = true;
					}
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					whiteMove = false;
				
				// BISHOP MOVES
				} else if (hasBishop == true) {
					boolean canMove = (path.contains(i));
					if (canMove == true) {
						buttons[i].setIcon(bishop);
						boardArr[i] = "wb";
						hasBishop = false;
						resetButtonColour();
						buttons[replacer].setIcon(null);
						boardArr[replacer] = "o";
						whiteMove = false;
					}
				}
				
				//QUEEN MOVES
				else if (hasQueen == true && path.contains(i)){
					buttons[i].setIcon(queen);
					boardArr[i] = "wq";
					hasQueen = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					whiteMove = false;
				}
				
				// KNIGHT MOVES
				else if (hasKnight == true && path.contains(i)){
					buttons[i].setIcon(knight);
					boardArr[i] = "wk";
					hasKnight = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					whiteMove = false;
				}
				
				// KING MOVES
				else if (hasKing == true && i == 62 && wKingMoved == false && wRookMovedR == false && wCastled == false && hasKing == true && replacer == 60 && i == 62 && boardArr[i - 1].equals("o") && CheckPaths.kingPath(true, true, boardArr, replacer, i)) {
					buttons[i].setIcon(king);
					boardArr[i] = "wK";
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					boardArr[63] = "o";
					boardArr[61] = "wr";
					buttons[63].setIcon(null);
					buttons[61].setIcon(rook);
					wCastled = false;
					whiteMove = false;
					//Castle queen side
				} else if (hasKing == true && i == 58 && wKingMoved == false && wRookMovedL == false && wCastled == false && hasKing == true && replacer == 60 && i == 58 && boardArr[59].equals("o") && boardArr[58].equals("o") && boardArr[57].equals("o") && CheckPaths.kingPath(true, true, boardArr, replacer, i)) {
					buttons[i].setIcon(king);
					boardArr[i] = "wK";
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					boardArr[56] = "o";
					boardArr[59] = "wr";
					buttons[56].setIcon(null);
					buttons[59].setIcon(rook);
					wCastled = true; //Probs dont need this
					whiteMove = false;
				} else if (hasKing == true && path.contains(i)){
					buttons[i].setIcon(king);
					boardArr[i] = "wK";
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					wKingMoved = true;
					whiteMove = false;
				}
				//PAWN END OF THE BOARD CONDITIONS
				whiteCheckMate();
			}
			}
		
		}
		
		
		if (whiteMove == false) {
			blackCheckMate();
			//blackCheckMate();
		//BLACK MOVES
		if (pawnFinished == false) {
			if (comp == false) {
		for (int i = 0; i < 64; i++)
		if (e.getSource() == buttons[i]) {
			//SETUP BLACK
			if (boardArr[i].equals("bp")) {
				replacer = i;
				hasPawnB = true;
				hasRookB = false;
				hasQueenB = false;
				hasKnightB = false;
				hasBishopB = false;
				hasKingB = false;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.blackPawnPath(true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			} else if (boardArr[i].equals("br")) {
				replacer = i;
				hasPawnB = false;
				hasRookB = true;
				hasBishopB = false;
				hasKnightB = false;
				hasQueenB = false;
				hasKingB = false;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.rookPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			} else if (boardArr[i].equals("bb")) {
				replacer = i;
				hasPawnB = false;
				hasRookB = false;
				hasQueenB = false;
				hasBishopB = true;
				hasKnightB = false;
				hasKingB = false;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.bishopPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			} else if (boardArr[i].equals("bq")) {
				replacer = i;
				hasPawnB = false;
				hasRookB = false;
				hasBishopB = false;
				hasQueenB = true;
				hasKnightB = false;
				hasKingB = false;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.bishopPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
					if (CheckPaths.rookPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			} else if (boardArr[i].equals("bk")) {
				replacer = i;
				hasPawnB = false;
				hasRookB = false;
				hasQueenB = false;
				hasBishopB = false;
				hasKnightB = true;
				hasKingB = false;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.knightPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			} else if (boardArr[i].equals("bK")){
				replacer = i;
				hasPawnB = false;
				hasRookB = false;
				hasQueenB = false;
				hasBishopB = false;
				hasKnightB = false;
				hasKingB = true;
				resetButtonColour();
				for (int j = 0; j < 64; j++) {
					if (CheckPaths.kingPath(false, true, boardArr, replacer, j)) {
						if (buttons[j].getBackground() == light) {
							buttons[j].setBackground(new Color(230, 207, 0));
						} else {
							buttons[j].setBackground(new Color(180, 167, 0));
						}
					}
				}
			//PAWN MOVES
			} else if (hasPawnB == true && CheckPaths.blackPawnPath(true, boardArr, replacer, i) && i > 56 && i < 64) {
				pawnFinish = i;
				pawnFinished = true;
				isBlackPawn = true;
				pawnFinishOld = replacer;
				pawnFinisher(i);
				whiteMove = true;
			} else if (hasPawnB == true && CheckPaths.blackPawnPath(true, boardArr, replacer, i)) {
				buttons[i].setIcon(Bpawn);
				boardArr[i] = "bp";
				hasPawnB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
				
			//ROOK MOVES
				
			} else if (hasRookB == true && CheckPaths.rookPath(false, true, boardArr, replacer, i)) { //THIS LINE
				buttons[i].setIcon(Brook);
				boardArr[i] = "br";
				hasRookB = false;
				resetButtonColour();
				if (replacer == 7) {
					bRookMovedR = true;
				} else if (replacer == 0) {
					bRookMovedL = true;
				}
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
			
			// BISHOP MOVES
			} else if (hasBishopB == true && CheckPaths.bishopPath(false, true, boardArr, replacer, i)) {
				buttons[i].setIcon(Bbishop);
				boardArr[i] = "bb";
				hasBishopB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
			}
			
			//QUEEN MOVES
			else if (hasQueenB == true && (CheckPaths.bishopPath(false, true, boardArr, replacer, i) || CheckPaths.rookPath(false, true, boardArr, replacer, i))){
				buttons[i].setIcon(Bqueen);
				boardArr[i] = "bq";
				hasQueenB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
			}
			
			// KNIGHT MOVES
			else if (hasKnightB == true && CheckPaths.knightPath(false, true, boardArr, replacer, i)){
				buttons[i].setIcon(Bknight);
				boardArr[i] = "bk";
				hasKnightB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
			}
			
			// KING MOVES
			 else if (hasKingB == true && CheckPaths.kingPath(false, true, boardArr, replacer, i) && replacer == 4 && i == 6) {
				buttons[6].setIcon(Bking);
				boardArr[6] = "bK";
				hasKingB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				boardArr[7] = "o";
				boardArr[5] = "br";
				buttons[7].setIcon(null);
				buttons[5].setIcon(Brook);
				bCastled = true;
				whiteMove = true;
				//Castle queen side
			}  else if (hasKingB == true && CheckPaths.kingPath(false, true, boardArr, replacer, i) && replacer == 4 && i == 2) {
				buttons[i].setIcon(Bking);
				boardArr[i] = "bK";
				hasKingB = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				boardArr[0] = "o";
				boardArr[3] = "br";
				buttons[0].setIcon(null);
				buttons[3].setIcon(Brook);
				bCastled = true; //Probs dont need this
				whiteMove = true;
			} else if (hasKingB == true && CheckPaths.kingPath(false, true, boardArr, replacer, i)){
				buttons[i].setIcon(Bking);
				boardArr[i] = "bK";
				hasKingB = false;
				bKingMoved = true;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = "o";
				whiteMove = true;
				//Castle
			}
			//PAWN END OF THE BOARD CONDITIONS
		}
			} else {
				
				
				//// AIIIIIII BABYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
					
					int[] klin = Board.bestBlackMove(false);
					int choice = klin[0];
					int newMove = klin[1];
					
							
					if (boardArr[choice].equals("bp")) {
						buttons[newMove].setIcon(Bpawn);
						boardArr[newMove] = "bp";
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = "o";
						whiteMove = true;
					} else if (boardArr[choice].equals("bk")) {
						buttons[newMove].setIcon(Bknight);
						boardArr[newMove] = "bk";
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = "o";
						whiteMove = true;
					} else if (boardArr[choice].equals("bb")) {
						buttons[newMove].setIcon(Bbishop);
						boardArr[newMove] = "bb";
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = "o";
						whiteMove = true;
					} else if (boardArr[choice].equals("br")) {
						buttons[newMove].setIcon(Brook);
						boardArr[newMove] = "br";
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						if (choice == 7) {
							bRookMovedR = true;
						} else if (choice == 0) {
							bRookMovedL = true;
						}
						buttons[choice].setIcon(null);
						boardArr[choice] = "o";
						whiteMove = true;
					}  else if (boardArr[choice].equals("bq")) {
						buttons[newMove].setIcon(Bqueen);
						boardArr[newMove] = "bq";
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = "o";
						whiteMove = true;
					} else if (boardArr[choice].equals("bK")) {
						if (bKingMoved == false && bRookMovedR == false && bCastled == false && choice == 4 && newMove == 6) {
							buttons[newMove].setIcon(Bking);
							boardArr[newMove] = "bK";
							resetButtonColour();
							buttons[choice].setIcon(null);
							boardArr[choice] = "o";
							
							boardArr[7] = "o";
							boardArr[5] = "br";
							buttons[7].setIcon(null);
							buttons[5].setIcon(Brook);
							bKingMoved = true;
							whiteMove = true;
						} else if (choice == 4 && newMove == 2) {
							buttons[newMove].setIcon(Bking);
							boardArr[newMove] = "bK";
							resetButtonColour();
							buttons[choice].setIcon(null);
							boardArr[choice] = "o";
							boardArr[0] = "o";
							boardArr[3] = "br";
							buttons[0].setIcon(null);
							buttons[3].setIcon(Brook);
							bKingMoved = true;
							whiteMove = true;
						} else {
							buttons[newMove].setIcon(Bking);
							boardArr[newMove] = "bK";
							resetButtonColour();
							buttons[newMove].setBackground(new Color(180, 167, 0));
							buttons[choice].setBackground(new Color(180, 167, 0));
							buttons[choice].setIcon(null);
							boardArr[choice] = "o";
							bKingMoved = true;
							whiteMove = true;
						}
					}
					
					/*} else if (bKingMoved == false && bRookMovedR == false && bCastled == false && hasKingB == true && replacer == 4 && i == 6 && boardArr[i - 1].equals("o")) {
					buttons[6].setIcon(Bking);
					boardArr[6] = "bK";
					hasKingB = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = "o";
					boardArr[7] = "o";
					boardArr[5] = "br";
					buttons[7].setIcon(null);
					buttons[5].setIcon(Brook);
					bCastled = true;
					whiteMove = true;
				}*/
			}
		}
		}
	}

}
