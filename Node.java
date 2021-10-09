import java.util.LinkedList;

public class Node {
	Node[] children;
	LinkedList<Node> childrenLinked;
	Node parentNode;
	int position;
	Square[] board;
	Piece.Colour colour;
	int oldSquare;
	int newSquare;
	public static int numOfMoves = 0;
	
	// when you code it today cut time by getting rid of the clone method
	// instead what you want to do is have each node simply store old square
	// and new square, when you want to make children change the board to equal
	// the oldSquare, then convert it back at the end. this will save so much time
	
	// alternatively if this doesn't work use hash codes or something, figure out
	// what the fuck this is
	
	// another way that might dramatically speed it up is insted of creating nodes
	// and then checking them, create nodes as you go along, infact this actually
	// will have to be coded in so I should probably figure out a way to do this
	// tomorrow
	
	Node(Square[] board, int position, Node parentNode, Piece.Colour colour, int oldSquare, int newSquare){
		this.colour = colour;
		this.position = position;
		this.parentNode = parentNode;
		this.board = board;
		this.oldSquare = oldSquare;
		this.newSquare = newSquare;
	}
	
	public Square[] getSquares() {
		return this.board;
	}
	
	public void createChildren() {
		Piece.Colour newColour;
		
		if (this.colour == Piece.Colour.WHITE) {
			newColour = Piece.Colour.BLACK;
		} else {
			newColour = Piece.Colour.WHITE;
		}
		
		int piecesSize = 0;
		int nonPiecesSize = 0;
		for (int i = 0; i < 64; i++) {
			if (board[i].getColour() == colour) {
				piecesSize++;
			} else {
				nonPiecesSize++;
			}
		}
		int piecesCount = 0;
		int nonPiecesCount = 0;
		int[] pieces = new int[piecesSize];
		int[] nonPieces = new int[nonPiecesSize];
		for (int i = 0; i < 64; i++) {
			if (board[i].getColour() == colour) {
				pieces[piecesCount++] = i;
			} else {
				nonPieces[nonPiecesCount++] = i;
			}
		}
		
		int childrenLength = 0;
		for (int i = 0 ; i < pieces.length; i++) {
			if (this.board[pieces[i]].getColour() == this.colour) {
				for (int j = 0 ; j < nonPieces.length; j++) {
					if (this.board[pieces[i]].canMove(nonPieces[j], this.board, true)) {
						childrenLength++;
					}
				}
			}
		}
		Node[] children = new Node[childrenLength];
		int counter = 0;
		for (int i = 0 ; i < pieces.length; i++) {
			if (this.board[pieces[i]].getColour() == colour) {
				//Square[] newBoard = new Square[64];
				//newBoard[i] = new Square(this.board[i].getPiece(), i);
				for (int j = 0 ; j < nonPieces.length; j++) {
					//newBoard[j] = new Square(this.board[j].getPiece(), j);
					if (this.board[pieces[i]].getPiece() != null && this.board[pieces[i]].canMove(nonPieces[j], this.board, true)) {
						Square[] newBoard = cloneBoard();
						newBoard[nonPieces[j]].setPiece(newBoard[pieces[i]].getPiece());
						newBoard[pieces[i]].setPiece(null);
						children[counter++] = new Node(newBoard, this.position + 1, this, newColour, pieces[i], nonPieces[j]);
					}
				}
			}
		}
		this.children = children;
	}
	
	public Node[] getChildren(){
		return this.children;
	}
	
	/*public Node findBest(int depth, int oldBestMove) {
		int bestMove;
		Node bestNode = this;
		int currentMove = 0;
		if (this.colour == Piece.Colour.BLACK) {
			bestMove = Integer.MIN_VALUE;
			for (int i = 0; i < this.children.length; i++) {
				numOfMoves++;
				if (this.children[i].getChildren() == null) {
					currentMove = this.children[i].staticEvaluation();
				} else {
					currentMove = this.children[i].findBest(0, bestMove).staticEvaluation();
				}
				if (currentMove >= oldBestMove) {
					return this.children[i];
				}
				if (currentMove > bestMove) {
					bestMove = currentMove;
					bestNode = this.children[i];
				}
			}
			return bestNode;
		} else {
			bestMove = Integer.MAX_VALUE;
			for (int i = 0; i < this.children.length; i++) {
				numOfMoves++;
				if (this.children[i].getChildren() == null) {
					currentMove = this.children[i].staticEvaluation();
				} else {
					currentMove = this.children[i].findBest(0, bestMove).staticEvaluation();
				}
				if (currentMove <= oldBestMove) {
					return this.children[i];
				}
				if (currentMove < bestMove) {
					bestMove = currentMove;
					bestNode = this.children[i];
				}
			}
			return bestNode;
		}
	}*/
	
	public Node findBest(int depth, int oldBestMove, Piece.Colour changeColour) {
		//int bestMove;
		// for each layer creat a bunch of null children and then fill them in
		if (changeColour == null) {
			changeColour = this.colour;
		}
		Node bestNode = this;
		Piece.Colour newColour;
		//int currentMove = 0;
		if (changeColour == Piece.Colour.BLACK) {
			newColour = Piece.Colour.WHITE;
			if (depth == 0) {
				int bestMove = Integer.MIN_VALUE;
				int currentMove;
				for (int i = 0 ; i < 64; i++) {
					if (this.board[i].getColour() == changeColour) {
						for (int j = 0 ; j < 64; j++) {
							if (this.board[i].canMove(j, this.board, true)) {
								Piece oldConverter = board[i].getPiece();
								Piece newConverter = board[j].getPiece();
								board[j].setPieceInvisible(board[i].getPiece());
								board[i].setPieceInvisible(null);
								currentMove = this.staticEvaluation();
								
								if (currentMove >= oldBestMove) {	
									Node newNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
									return newNode;
								}
								
								if (currentMove > bestMove) {
									bestMove = currentMove;
									Node newNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									bestNode = newNode;
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								} else {
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								}
							}
						}
					}
				}
				
				// if depth is not 0
			} else {
				/////////////////////////// if depth == 1
				int bestMove = Integer.MIN_VALUE;
				int currentMove;
				for (int i = 0 ; i < 64; i++) {
					if (this.board[i].getColour() == changeColour) {
						for (int j = 0 ; j < 64; j++) {
							if (this.board[i].canMove(j, this.board, true)) {
								Piece oldConverter = board[i].getPiece();
								Piece newConverter = board[j].getPiece();
								board[j].setPieceInvisible(board[i].getPiece());
								board[i].setPieceInvisible(null);
								// if (bestMove == Integer.MIN_VALUE) {} might need to do this
								currentMove = this.findBest(depth - 1, bestMove, newColour).staticEvaluation();
								
								if (currentMove >= oldBestMove) {	
									Node newNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
									return newNode;
								}
								
								if (currentMove > bestMove) {
									bestMove = currentMove;
									bestNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								} else {
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								}
							}
						}
					}
				}
				////////////////////
			}
		} else {
			newColour = Piece.Colour.BLACK;
			if (depth == 0) {
				int bestMove = Integer.MAX_VALUE;
				
				int currentMove;
				for (int i = 0 ; i < 64; i++) {
					if (this.board[i].getColour() == changeColour) {
						for (int j = 0 ; j < 64; j++) {
							if (this.board[i].canMove(j, this.board, true)) {
								Piece oldConverter = board[i].getPiece();
								Piece newConverter = board[j].getPiece();
								board[j].setPieceInvisible(board[i].getPiece());
								board[i].setPieceInvisible(null);
								currentMove = this.staticEvaluation();
								
								if (currentMove <= oldBestMove) {	
									Node newNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
									return newNode;
								}
								
								if (currentMove < bestMove) {
									bestMove = currentMove;
									bestNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);;
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								} else {
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								}
							}
						}
					}
				}
				
				
			} else {
				int bestMove = Integer.MAX_VALUE;
				int currentMove;
				for (int i = 0 ; i < 64; i++) {
					if (this.board[i].getColour() == changeColour) {
						for (int j = 0 ; j < 64; j++) {
							if (this.board[i].canMove(j, this.board, true)) {
								Piece oldConverter = board[i].getPiece();
								Piece newConverter = board[j].getPiece();
								board[j].setPieceInvisible(board[i].getPiece());
								board[i].setPieceInvisible(null);
								currentMove = this.findBest(depth - 1, bestMove, newColour).staticEvaluation();
								
								if (currentMove <= oldBestMove) {	
									Node newNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
									return newNode;
								}
								
								if (currentMove < bestMove) {
									bestMove = currentMove;
									bestNode = new Node(this.cloneBoard(), this.position + 1, this, newColour, i, j);
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								} else {
									board[i].setPiece(oldConverter);
									board[j].setPiece(newConverter);
								}
							}
						}
					}
				}
			}
		}
		return bestNode;
	}
	
	public void string() {
		if (this.children != null) {
			System.out.println(this.position + " " + this.children.length);
		} else {
			System.out.println(this.position + " " + 0);
		}
	}

	public int[] getMoves() {
		return new int[] {this.oldSquare, this.newSquare};
	}
	
	// maybe create hashtable of board and then each node stores that value
	//
	// private hashtable convertBoard() {}
	//
	// private Square converter() {} // method checks hashtable value and returns a square
	
	private Square[] cloneBoard() {
		Square[] newSquares = new Square[64];
		for (int i = 0; i < 64; i++) {
			newSquares[i] = new Square(this.board[i].getPiece(), i);
		}
		return newSquares;
	}
	
	private int staticEvaluation() {
		int totalBlack = 0;
		int totalWhite = 0;
		for (int i = 0; i < 64; i++) {
			if (this.board[i].getColour() == Piece.Colour.WHITE) {
				totalWhite += this.board[i].score();
			} else if (this.board[i].getColour() == Piece.Colour.BLACK) {
				totalBlack += this.board[i].score();
			}
		}
		return totalBlack - totalWhite;
	}

}
