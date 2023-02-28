package lk.ijse.dep.service;
public class HumanPlayer extends Player{
    boolean isTrue;
    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }
    @Override
    public void movePiece(int col1) {
        isTrue=board.isLegalMoves(col1);
        if(isTrue){
            board.updateMove(col1,Piece.BLUE);
            board.getBoardUi().update(col1,isTrue);
            Winner winner=board.findWinner();
            if (winner.getWinningPiece()!=Piece.EMPTY){
                board.getBoardUi().notifyWinner(winner);
            }else {
                if (!board.exitLegalMoves()){
                    board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
                }
            }

        }
    }

}
