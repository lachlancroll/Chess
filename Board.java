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
	// the AI keeps letting my pawns take stuff
	
	static final ImageIcon PAWN = new ImageIcon("white pawn.png");
	static final ImageIcon ROOK = new ImageIcon("white rook.png");
	static final ImageIcon BISHOP = new ImageIcon("white bishop.png");
	static final ImageIcon QUEEN = new ImageIcon("white queen.png");
	static final ImageIcon KNIGHT = new ImageIcon("white knight.png");
	static final ImageIcon KING = new ImageIcon("white king.png");
	static final ImageIcon BLACK_PAWN = new ImageIcon("black pawn.png");
	static final ImageIcon BLACK_ROOK = new ImageIcon("black rook.png");
	static final ImageIcon BLACK_BISHOP = new ImageIcon("black bishop.png");
	static final ImageIcon BLACK_QUEEN = new ImageIcon("black queen.png");
	static final ImageIcon BLACK_KNIGHT = new ImageIcon("black knight.png");
	static final ImageIcon BLACK_KING = new ImageIcon("black king.png");
	
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
	
	static boolean hasBlackPawn = false;
	static boolean hasBlackRook = false;
	static boolean hasBlackBishop = false;
	static boolean hasBlackQueen = false;
	static boolean hasBlackKnight = false;
	static boolean hasBlackKing = false;
	
	boolean comp = false;
	
	static int[] boardArr = new int[64];
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
		
		//103 knight 130 bishop
		//setting up the board
		for (int i = 0; i < 64; i++) {
			if (i > 47 && i < 56) {
				boardArr[i] = 11;
			} else if (i == 56 || i == 63) {
				boardArr[i] = 15;
			} else if (i == 61 || i == 58){
				boardArr[i] = 130;
			} else if (i == 59) {
				boardArr[i] = 19;
			} else if (i == 57 || i == 62) {
				boardArr[i] = 103;
			} else if (i == 60){
				boardArr[i] = 110;
			} else if (i > 7 && i < 16) {
				boardArr[i] = 21;
			} else if (i == 0 || i == 7) {
				boardArr[i] = 25;
			} else if (i == 1 || i == 6) {
				boardArr[i] = 203;
			} else if (i == 2 || i == 5) {
				boardArr[i] = 230;
			} else if (i == 3) {
				boardArr[i] = 29;
			} else if (i == 4) {
				boardArr[i] = 210;
			} else {
				boardArr[i] = 0;
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
				if (boardArr[num] == 11) {
					buttons[num].setIcon(PAWN);
				} else if (boardArr[num] == 15) {
					buttons[num].setIcon(ROOK);
				} else if (boardArr[num] == 130) {
					buttons[num].setIcon(BISHOP);
				} else if (boardArr[num] == 19) {
					buttons[num].setIcon(QUEEN);
				} else if (boardArr[num] == 103) {
					buttons[num].setIcon(KNIGHT);
				} else if (boardArr[num] == 110) {
					buttons[num].setIcon(KING);
				} else if (boardArr[num] == 21) {
					buttons[num].setIcon(BLACK_PAWN);
				} else if (boardArr[num] == 25) {
					buttons[num].setIcon(BLACK_ROOK);
				} else if (boardArr[num] == 230) {
					buttons[num].setIcon(BLACK_BISHOP);
				} else if (boardArr[num] == 29) {
					buttons[num].setIcon(BLACK_QUEEN);
				} else if (boardArr[num] == 203) {
					buttons[num].setIcon(BLACK_KNIGHT);
				} else if (boardArr[num] == 210) {
					buttons[num].setIcon(BLACK_KING);
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
			queenB.setIcon(BLACK_QUEEN);
			rookB.setIcon(BLACK_ROOK);
			knightB.setIcon(BLACK_KNIGHT);
			bishopB.setIcon(BLACK_BISHOP);
		} else {
			queenB.setIcon(QUEEN);
			rookB.setIcon(ROOK);
			knightB.setIcon(KNIGHT);
			bishopB.setIcon(BISHOP);
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
			if (boardArr[i] == 203) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 21) {
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
			} else if (boardArr[i] == 25) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 230) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 29) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(false, true, boardArr, i, j) == true || CheckPaths.rookPath(false, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i] == 210) {
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
			if (boardArr[i] == 203) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 21) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.blackPawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 25) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 230) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 29) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(false, true, boardArr, i, j) == true || CheckPaths.rookPath(false, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i] == 210) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.kingPath(false, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			}
		}
		if (countMove == 0) {
			System.out.println("yes");
			return true;
		} else {
			return false;
		}
	}
		
	public void whiteCheckMate() {
		int countMove = 0;
		for (int i = 0; i < 64; i++) {
			if (boardArr[i] == 103) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 11) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.pawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 15) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 130) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 19) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(true, true, boardArr, i, j) == true || CheckPaths.rookPath(true, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i] == 110) {
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
			if (boardArr[i] == 103) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.knightPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 11) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.pawnPath(true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 15) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.rookPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 130) {
				for (int j = 0; j < 64; j ++) {
					if (CheckPaths.bishopPath(true, true, boardArr, i, j) == true){
						countMove++;
					}
				}
			} else if (boardArr[i] == 19) {
				for (int j = 0; j < 64; j ++) {
					if ((CheckPaths.bishopPath(true, true, boardArr, i, j) == true || CheckPaths.rookPath(true, false, boardArr, i, j) == true)){
						countMove++;
					}
				}
			} else if (boardArr[i] == 110) {
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
		if (boardArr[newSquare] == 11) {
			return 1;
		} else if (boardArr[newSquare] == 15) {
			return 5;
		} else if (boardArr[newSquare] == 130) {
			return 3;
		} else if (boardArr[newSquare] == 103) {
			return 3;
		} else if (boardArr[newSquare] == 19) {
			return 9;
		} else if (boardArr[newSquare] == 110) {
			return 100;
		}
		return 0;
	}
	
	public static int whiteTurnScorer(int newSquare) {
		if (boardArr[newSquare] == 21) {
			return 1;
		} else if (boardArr[newSquare] == 25) {
			return 5;
		} else if (boardArr[newSquare] == 230) {
			return 3;
		} else if (boardArr[newSquare] == 203) {
			return 3;
		} else if (boardArr[newSquare] == 29) {
			return 9;
		} else if (boardArr[newSquare] == 210) {
			return 100;
		}
		return 0;
	}
	
	public static int[] bestBlackMove(int count) {
		int bestMoveVal = Integer.MIN_VALUE;
		int bestMoveold = 0;
		int bestMovenew = 0;
		
		LinkedList<Integer> nonPieces = new LinkedList<Integer>();
		LinkedList<Integer> pieces = new LinkedList<Integer>();
		for (int i = 0; i < 64; i++) {
			if (boardArr[i] == 21 || boardArr[i] == 203 || boardArr[i] == 230 || boardArr[i] == 25 || boardArr[i] == 29 || boardArr[i] == 210) {
				pieces.add(i);
			} else {
				nonPieces.add(i);
			}
		}
		int minus = 0;
		
		if (boolBlackCheckMate() == true) {
			int[] finisher = {0, 0, -1000};
			return finisher;
		}
		
		for (int i = 0; i < pieces.size(); i++) {
			if (boardArr[pieces.get(i)] == 21) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.blackPawnPath(true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 21;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
					minus = 0;
				}
			} else if (boardArr[pieces.get(i)] == 203) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.knightPath(false, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 203;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
				}
			} else if (boardArr[pieces.get(i)] == 230) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.bishopPath(false, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 230;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
				}
			} else if (boardArr[pieces.get(i)] == 25) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(false, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 25;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
				}
			} else if (boardArr[pieces.get(i)] == 29) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(false, true, boardArr, pieces.get(i), nonPieces.get(j)) || CheckPaths.bishopPath(false, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 29;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
				}
			} else if (boardArr[pieces.get(i)] == 210) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.kingPath(false, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 210;
							minus = Board.bestWhiteMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.turnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.turnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
						minus = 0;
					}
				}
			}
		}
		
		int[] finisher = {bestMoveold, bestMovenew, bestMoveVal};
		return finisher;
	}

	public static int[] bestWhiteMove(int count) {
		int bestMoveVal = Integer.MIN_VALUE;
		int bestMoveold = 0;
		int bestMovenew = 0;
		
		LinkedList<Integer> nonPieces = new LinkedList<Integer>();
		LinkedList<Integer> pieces = new LinkedList<Integer>();
		for (int i = 0; i < 64; i++) {
			if (boardArr[i] == 11 || boardArr[i] == 103 || boardArr[i] == 130 || boardArr[i] == 15 || boardArr[i] == 19 || boardArr[i] == 110) {
				pieces.add(i);
			} else {
				nonPieces.add(i);
			}
		}
		int minus = 0;
		
		if (boolWhiteCheckMate() == true) {
			int[] finisher = {0, 0, -1000};
			return finisher;
		}
		
		for (int i = 0; i < pieces.size(); i++) {
			if (boardArr[pieces.get(i)] == 11) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.pawnPath(true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 11;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)] == 103) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.knightPath(true, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 103;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)] == 130) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 130;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)] == 15) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 15;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)] == 19) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.rookPath(true, true, boardArr, pieces.get(i), nonPieces.get(j)) || CheckPaths.bishopPath(true, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 19;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
						}
						if (Board.whiteTurnScorer(nonPieces.get(j)) - minus > bestMoveVal) {
							bestMoveVal = Board.whiteTurnScorer(nonPieces.get(j)) - minus;
							bestMoveold = pieces.get(i);
							bestMovenew = nonPieces.get(j);
						}
					}
				}
			} else if (boardArr[pieces.get(i)] == 110) {
				for (int j = 0; j < nonPieces.size(); j++) {
					if (CheckPaths.kingPath(true, true, boardArr, pieces.get(i), nonPieces.get(j))) {
						if (count != 0) {
							int converterOld = boardArr[pieces.get(i)];
							int converterNew = boardArr[nonPieces.get(j)];
							boardArr[pieces.get(i)] = 0;
							boardArr[nonPieces.get(j)] = 110;
							minus = Board.bestBlackMove(count - 1)[2];
							boardArr[pieces.get(i)] = converterOld;
							boardArr[nonPieces.get(j)] = converterNew;
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
					buttons[pawnFinish].setIcon(BLACK_ROOK);
					boardArr[pawnFinish] = 25;
				} else {
					buttons[pawnFinish].setIcon(ROOK);
					boardArr[pawnFinish] = 15;
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = 0;
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == queenB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(BLACK_QUEEN);
					boardArr[pawnFinish] = 29;
				} else {
					buttons[pawnFinish].setIcon(QUEEN);
					boardArr[pawnFinish] = 19;
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = 0;
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == knightB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(BLACK_KNIGHT);
					boardArr[pawnFinish] = 203;
				} else {
					buttons[pawnFinish].setIcon(KNIGHT);
					boardArr[pawnFinish] = 103;
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = 0;
				pawnS.dispose();
				pawnFinished = false;
			}
		}
		if (e.getSource() == bishopB) {
			if (pawnFinished == true) {
				if (isBlackPawn == true) {
					buttons[pawnFinish].setIcon(BLACK_BISHOP);
					boardArr[pawnFinish] = 230;
				} else {
					buttons[pawnFinish].setIcon(BISHOP);
					boardArr[pawnFinish] = 130;
				}
				isBlackPawn = false;
				buttons[pawnFinishOld].setIcon(null);
				boardArr[pawnFinishOld] = 0;
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
				if (boardArr[i] == 11) {
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
					
				} else if (boardArr[i] == 15) {
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
				} else if (boardArr[i] == 130) {
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
				} else if (boardArr[i] == 19) {
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
				} else if (boardArr[i] == 103) {
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasQueen = false;
					hasBishop = false;
					hasKnight = true;					hasKing = false;
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
				} else if (boardArr[i] == 110){
					replacer = i;
					hasPawn = false;
					hasRook = false;
					hasQueen = false;
					hasBishop = false;
					hasKnight = false; hasKing = true;
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
						buttons[i].setIcon(PAWN);
						boardArr[i] = 11;
						hasPawn = false;
						resetButtonColour();
						buttons[replacer].setIcon(null);
						boardArr[replacer] = 0;
						whiteMove = false;
					}
					
				//ROOK MOVES
					
				} else if (hasRook == true && path.contains(i)) {
					buttons[i].setIcon(ROOK);
					boardArr[i] = 15;
					hasRook = false;
					resetButtonColour();
					if (replacer == 63) {
						wRookMovedR = true;
					} else if (replacer == 56) {
						wRookMovedL = true;
					}
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
					whiteMove = false;
				
				// BISHOP MOVES
				} else if (hasBishop == true) {
					boolean canMove = (path.contains(i));
					if (canMove == true) {
						buttons[i].setIcon(BISHOP);
						boardArr[i] = 130;
						hasBishop = false;
						resetButtonColour();
						buttons[replacer].setIcon(null);
						boardArr[replacer] = 0;
						whiteMove = false;
					}
				}
				
				//QUEEN MOVES
				else if (hasQueen == true && path.contains(i)){
					buttons[i].setIcon(QUEEN);
					boardArr[i] = 19;
					hasQueen = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
					whiteMove = false;
				}
				
				// KNIGHT MOVES
				else if (hasKnight == true && path.contains(i)){
					buttons[i].setIcon(KNIGHT);
					boardArr[i] = 103;
					hasKnight = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
					whiteMove = false;
				}
				
				// KING MOVES
				 if (hasKing == true && i == 62 && wKingMoved == false && wRookMovedR == false && wCastled ==  false && hasKing == true && replacer == 60 && i == 62 && boardArr[i - 1] == 0 && CheckPaths.kingPath(true, true, boardArr, replacer, i)) {
					buttons[i].setIcon(KING);
					boardArr[i] = 110;
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
					boardArr[63] = 0;
					boardArr[61] = 15;
					buttons[63].setIcon(null);
					buttons[61].setIcon(ROOK);
					wCastled = false;
					whiteMove = false;
					//Castle queen side
				}  else if (hasKing == true && i == 58 && wKingMoved == false && wRookMovedL == false && wCastled ==  false && hasKing == true && replacer == 60 && i == 58 && boardArr[59] == 0 && boardArr[58] == 0 && boardArr[57] == 0 && CheckPaths.kingPath(true, true, boardArr, replacer, i)) {
					buttons[i].setIcon(KING);
					boardArr[i] = 110;
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
					boardArr[56] = 0;
					boardArr[59] = 15;
					buttons[56].setIcon(null);
					buttons[59].setIcon(ROOK);
					wCastled = true; //Probs dont need this
					whiteMove = false;
				}  else if (hasKing == true && path.contains(i)){
					buttons[i].setIcon(KING);
					boardArr[i] = 110;
					hasKing = false;
					resetButtonColour();
					buttons[replacer].setIcon(null);
					boardArr[replacer] = 0;
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
			if (boardArr[i] == 21) {
				replacer = i;
				hasBlackPawn = true;
				hasBlackRook = false;
				hasBlackQueen = false;
				hasBlackKnight = false;
				hasBlackBishop = false;
				hasBlackKing = false;
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
			} else if (boardArr[i] == 25) {
				replacer = i;
				hasBlackPawn = false;
				hasBlackRook = true;
				hasBlackBishop = false;
				hasBlackKnight = false;
				hasBlackQueen = false;
				hasBlackKing = false;
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
			} else if (boardArr[i] == 230) {
				replacer = i;
				hasBlackPawn = false;
				hasBlackRook = false;
				hasBlackQueen = false;
				hasBlackBishop = true;
				hasBlackKnight = false;
				hasBlackKing = false;
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
			} else if (boardArr[i] == 29) {
				replacer = i;
				hasBlackPawn = false;
				hasBlackRook = false;
				hasBlackBishop = false;
				hasBlackQueen = true;
				hasBlackKnight = false;
				hasBlackKing = false;
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
			} else if (boardArr[i] == 203) {
				replacer = i;
				hasBlackPawn = false;
				hasBlackRook = false;
				hasBlackQueen = false;
				hasBlackBishop = false;
				hasBlackKnight = true;
				hasBlackKing = false;
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
			} else if (boardArr[i] == 210){
				replacer = i;
				hasBlackPawn = false;
				hasBlackRook = false;
				hasBlackQueen = false;
				hasBlackBishop = false;
				hasBlackKnight = false;
				hasBlackKing = true;
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
			} else if (hasBlackPawn == true && CheckPaths.blackPawnPath(true, boardArr, replacer, i) && i > 56 && i < 64) {
				pawnFinish = i;
				pawnFinished = true;
				isBlackPawn = true;
				pawnFinishOld = replacer;
				pawnFinisher(i);
				whiteMove = true;
			} else if (hasBlackPawn == true && CheckPaths.blackPawnPath(true, boardArr, replacer, i)) {
				buttons[i].setIcon(BLACK_PAWN);
				boardArr[i] = 21;
				hasBlackPawn = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
				
			//ROOK MOVES
				
			} else if (hasBlackRook == true && CheckPaths.rookPath(false, true, boardArr, replacer, i)) { //THIS LINE
				buttons[i].setIcon(BLACK_ROOK);
				boardArr[i] = 25;
				hasBlackRook = false;
				resetButtonColour();
				if (replacer == 7) {
					bRookMovedR = true;
				} else if (replacer == 0) {
					bRookMovedL = true;
				}
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
			
			// BISHOP MOVES
			} else if (hasBlackBishop == true && CheckPaths.bishopPath(false, true, boardArr, replacer, i)) {
				buttons[i].setIcon(BLACK_BISHOP);
				boardArr[i] = 230;
				hasBlackBishop = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
			}
			
			//QUEEN MOVES
			else if (hasBlackQueen == true && (CheckPaths.bishopPath(false, true, boardArr, replacer, i) || CheckPaths.rookPath(false, true, boardArr, replacer, i))){
				buttons[i].setIcon(BLACK_QUEEN);
				boardArr[i] = 29;
				hasBlackQueen = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
			}
			
			// KNIGHT MOVES
			else if (hasBlackKnight == true && CheckPaths.knightPath(false, true, boardArr, replacer, i)){
				buttons[i].setIcon(BLACK_KNIGHT);
				boardArr[i] = 203;
				hasBlackKnight = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
			}
			
			// KING MOVES
			 else if (hasBlackKing == true && CheckPaths.kingPath(false, true, boardArr, replacer, i) && replacer == 4 && i == 6) {
				buttons[6].setIcon(BLACK_KING);
				boardArr[6] = 210;
				hasBlackKing = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				boardArr[7] = 0;
				boardArr[5] = 25;
				buttons[7].setIcon(null);
				buttons[5].setIcon(BLACK_ROOK);
				bCastled = true;
				whiteMove = true;
				//Castle queen side
			}  else if (hasBlackKing == true && CheckPaths.kingPath(false, true, boardArr, replacer, i) && replacer == 4 && i == 2) {
				buttons[i].setIcon(BLACK_KING);
				boardArr[i] = 210;
				hasBlackKing = false;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				boardArr[0] = 0;
				boardArr[3] = 25;
				buttons[0].setIcon(null);
				buttons[3].setIcon(BLACK_ROOK);
				bCastled = true; //Probs dont need this
				whiteMove = true;
			} else if (hasBlackKing == true && CheckPaths.kingPath(false, true, boardArr, replacer, i)){
				buttons[i].setIcon(BLACK_KING);
				boardArr[i] = 210;
				hasBlackKing = false;
				bKingMoved = true;
				resetButtonColour();
				buttons[replacer].setIcon(null);
				boardArr[replacer] = 0;
				whiteMove = true;
				//Castle
			}
			//PAWN END OF THE BOARD CONDITIONS
		}
			} else {
				
				
				//// AIIIIIII BABYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
					
					int[] klin = Board.bestBlackMove(3);
					int choice = klin[0];
					int newMove = klin[1];
					
							
					if (boardArr[choice] == 21) {
						buttons[newMove].setIcon(BLACK_PAWN);
						boardArr[newMove] = 21;
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = 0;
						whiteMove = true;
					} else if (boardArr[choice] == 203) {
						buttons[newMove].setIcon(BLACK_KNIGHT);
						boardArr[newMove] = 203;
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = 0;
						whiteMove = true;
					} else if (boardArr[choice] == 230) {
						buttons[newMove].setIcon(BLACK_BISHOP);
						boardArr[newMove] = 230;
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = 0;
						whiteMove = true;
					} else if (boardArr[choice] == 25) {
						buttons[newMove].setIcon(BLACK_ROOK);
						boardArr[newMove] = 25;
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						if (choice == 7) {
							bRookMovedR = true;
						} else if (choice == 0) {
							bRookMovedL = true;
						}
						buttons[choice].setIcon(null);
						boardArr[choice] = 0;
						whiteMove = true;
					}  else if (boardArr[choice] == 29) {
						buttons[newMove].setIcon(BLACK_QUEEN);
						boardArr[newMove] = 29;
						resetButtonColour();
						buttons[newMove].setBackground(new Color(180, 167, 0));
						buttons[choice].setBackground(new Color(180, 167, 0));
						buttons[choice].setIcon(null);
						boardArr[choice] = 0;
						whiteMove = true;
					} else if (boardArr[choice] == 210) {
						if (bKingMoved == false && bRookMovedR == false && bCastled == false && choice == 4 && newMove == 6) {
							buttons[newMove].setIcon(BLACK_KING);
							boardArr[newMove] = 210;
							resetButtonColour();
							buttons[choice].setIcon(null);
							boardArr[choice] = 0;
							
							boardArr[7] = 0;
							boardArr[5] = 25;
							buttons[7].setIcon(null);
							buttons[5].setIcon(BLACK_ROOK);
							bKingMoved = true;
							whiteMove = true;
						} else if (choice == 4 && newMove == 2) {
							buttons[newMove].setIcon(BLACK_KING);
							boardArr[newMove] = 210;
							resetButtonColour();
							buttons[choice].setIcon(null);
							boardArr[choice] = 0;
							boardArr[0] = 0;
							boardArr[3] = 25;
							buttons[0].setIcon(null);
							buttons[3].setIcon(BLACK_ROOK);
							bKingMoved = true;
							whiteMove = true;
						} else {
							buttons[newMove].setIcon(BLACK_KING);
							boardArr[newMove] = 210;
							resetButtonColour();
							buttons[newMove].setBackground(new Color(180, 167, 0));
							buttons[choice].setBackground(new Color(180, 167, 0));
							buttons[choice].setIcon(null);
							boardArr[choice] = 0;
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
