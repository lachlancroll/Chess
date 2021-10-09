import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class NewBoard extends JFrame implements ActionListener{
	
	private Square[] squares = new Square[64];
	private Square currentSquare;
	public static ImageIcon whitePawn = new ImageIcon("white pawn.png");
	public static ImageIcon blackPawn = new ImageIcon("black pawn.png");
	public static ImageIcon whiteKnight = new ImageIcon("white knight.png");
	public static ImageIcon blackKnight = new ImageIcon("black knight.png");
	public static ImageIcon whiteBishop = new ImageIcon("white bishop.png");
	public static ImageIcon blackBishop = new ImageIcon("black bishop.png");
	public static ImageIcon whiteRook = new ImageIcon("white rook.png");
	public static ImageIcon blackRook = new ImageIcon("black rook.png");
	public static ImageIcon whiteQueen = new ImageIcon("white queen.png");
	public static ImageIcon blackQueen = new ImageIcon("black queen.png");
	public static ImageIcon whiteKing = new ImageIcon("white king.png");
	public static ImageIcon blackKing = new ImageIcon("black king.png");
	
	Piece.Colour currentMove = Piece.Colour.WHITE;
	
	public static boolean whiteKingMoved = false;
	public static boolean blackKingMoved = false;
	public static boolean whiteLRookMoved = false;
	public static boolean whiteRRookMoved = false;
	public static boolean blackLRookMoved = false;
	public static boolean blackRRookMoved = false;
	
	public static boolean p1 = false;

	
	NewBoard(){
		//ChooseComp choose = new ChooseComp();
		this.setVisible(true);
		this.setTitle("Chess");
		this.setSize(640, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(8,8));
		this.setResizable(false);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int num = (i * 8) + j;
				if (num == 0 || num == 7) {
					squares[num] = new Square(new Rook(Piece.Colour.BLACK), num);
				} else if (num == 1 || num == 6) {
					squares[num] = new Square(new Knight(Piece.Colour.BLACK), num);
				} else if (num == 2 || num == 5) {
					squares[num] = new Square(new Bishop(Piece.Colour.BLACK), num);
				} else if (num == 3) {
					squares[num] = new Square(new Queen(Piece.Colour.BLACK), num);
				} else if (num == 4) {
					squares[num] = new Square(new King(Piece.Colour.BLACK), num);
				} else if (num > 7 && num < 16) {
					squares[num] = new Square(new Pawn(Piece.Colour.BLACK), num);
				} else if (num > 47 && num < 56) {
					squares[num] = new Square(new Pawn(Piece.Colour.WHITE), num);
				} else if (num == 56 || num == 63) {
					squares[num] = new Square(new Rook(Piece.Colour.WHITE), num);
				} else if (num == 57 || num == 62) {
					squares[num] = new Square(new Knight(Piece.Colour.WHITE), num);
				} else if (num == 58 || num == 61) {
					squares[num] = new Square(new Bishop(Piece.Colour.WHITE), num);
				} else if (num == 59) {
					squares[num] = new Square(new Queen(Piece.Colour.WHITE), num);
				} else if (num == 60) {
					squares[num] = new Square(new King(Piece.Colour.WHITE), num);
				} else {
					squares[num] = new Square(num);
				}
				
				if ((j+i)%2 == 0) {
					squares[num].setColour(Square.SquareColour.LIGHT);
				} else {
					squares[num].setColour(Square.SquareColour.DARK);
				}
				
				squares[num].addActionListener(this);
				this.add(squares[num]);
			}
		}
		//choose.toFront();
	}
	
	public void reColour() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((j+i)%2 == 0) {
					squares[(i * 8) + j].setColour(Square.SquareColour.LIGHT);
				} else {
					squares[(i * 8)+ j].setColour(Square.SquareColour.DARK);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 64; i++) {
			if (e.getSource() == squares[i]) {
				if (currentSquare == null && squares[i].getPiece() != null && squares[i].getPiece().getColour() == currentMove) {
					currentSquare = squares[i];
					reColour();
					for (int f = 0; f < 64; f++) {
						if (currentSquare.getPiece() != null && currentSquare.canMove(f, squares, true)) {
							squares[f].light();
						}
					}
				} else if (currentSquare != null && squares[i].getPiece() != null && squares[i].getPiece().getColour() == currentMove) {
					currentSquare = squares[i];
					reColour();
					for (int f = 0; f < 64; f++) {
						if (currentSquare.canMove(f, squares, true)) {
							squares[f].light();
						}
					}
				} else if (currentSquare != null && currentSquare.canMove(i, squares, true)) {
					boolean isKing = currentSquare.checkKing();
					boolean isRook = currentSquare.getPiece().isRook();
					if (isRook && currentSquare.getNum() == 63) {
						whiteRRookMoved = true;
					} else if (isRook && currentSquare.getNum() == 7) {
						blackRRookMoved = true;
					} else if (isRook && currentSquare.getNum() == 0) {
						blackLRookMoved = true;
					} else if (isRook && currentSquare.getNum() == 56) {
						whiteLRookMoved = true;
					}
					
					if (squares[60].checkKing() && isKing && i == 62) {
						squares[61].setPiece(new Rook(Piece.Colour.WHITE));
						squares[63].setPiece(null);
					}
					if (squares[60].checkKing() && isKing && i == 58) {
						squares[59].setPiece(new Rook(Piece.Colour.WHITE));
						squares[56].setPiece(null);
					}
					if (squares[4].checkKing() && isKing && i == 2) {
						squares[3].setPiece(new Rook(Piece.Colour.BLACK));
						squares[0].setPiece(null);
					}
					if (squares[4].checkKing() && isKing && i == 6) {
						squares[5].setPiece(new Rook(Piece.Colour.BLACK));
						squares[7].setPiece(null);
					}
					squares[i].setPiece(currentSquare.getPiece());
					currentSquare.setPiece(null);
					squares[i].getPiece().hasMoved = true;
					reColour();
					if (currentMove == Piece.Colour.BLACK && p1 == false) {
						Check.checkMate(squares, Piece.Colour.WHITE);
						currentMove = Piece.Colour.WHITE;
					} else if (p1 == false){
						Check.checkMate(squares, Piece.Colour.BLACK);
						currentMove = Piece.Colour.BLACK;
					} else {
						final long startTime = System.currentTimeMillis();
						//System.out.println("alpha " + arr[3]);
						//System.out.println("beta " + arr[4]);
						int[] arr = AI.minimax(squares, 0, Piece.Colour.WHITE);
						//System.out.println(AI.numOfMoves);
						//System.out.println(AI.bestMinus);
						final long endTime = System.currentTimeMillis();
						System.out.println((endTime - startTime) / 1000.00);
						//System.out.println(Node.numOfMoves);
						//System.out.println(AI.worstMove);
						squares[arr[1]].setPiece(squares[arr[0]].getPiece());
						squares[arr[1]].getPiece().hasMoved = true;
						squares[arr[0]].setPiece(null);
					}
					currentSquare = null;
				}
			}
		}
	}
}
