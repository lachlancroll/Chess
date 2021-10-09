
public class AI {
	
	public static int[] minimax(Square[] squares, int depth, Piece.Colour colour) {
		// constructing the tree
		Node rootNode = new Node(squares, 0, null, Piece.Colour.BLACK, 0, 0);
		//rootNode.createChildren();
		/*for (int i = 0; i < rootNode.getChildren().length; i++) {
			rootNode.getChildren()[i].createChildren();
			for (int j = 0; j < rootNode.getChildren()[i].getChildren().length; j++) {
				rootNode.getChildren()[i].getChildren()[j].createChildren();
			}
		}*/
		return rootNode.findBest(3, 0, null).getMoves();
	}
}
