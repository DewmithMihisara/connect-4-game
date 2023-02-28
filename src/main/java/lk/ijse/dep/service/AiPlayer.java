package lk.ijse.dep.service;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {
    Winner winner;
    int randomNum;
    boolean isTrue;
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col1) {
        randomNum = bestMove();
        board.updateMove(randomNum, Piece.GREEN);
        board.getBoardUi().update(randomNum, isTrue);
        winner = board.findWinner();
        if (winner.getWinningPiece() != Piece.EMPTY) {
            board.getBoardUi().notifyWinner(winner);
        } else {
            if (!board.exitLegalMoves()) {
                board.getBoardUi().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
    private int bestMove() {
        boolean isUserWinning = false;
        int winningCol = 0;
        for (int i = 0; i < 6; ++i) {
            if (board.isLegalMoves(i)) {
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                board.updateMove(i, row, Piece.EMPTY);
                if (heuristicVal == 1) {
                    return i;
                } else if (heuristicVal == -1) {
                    isUserWinning = true;
                } else {
                    winningCol = i;
                }
            }
        }
        if (isUserWinning && board.isLegalMoves(winningCol)) {
            return winningCol;
        } else {
            int j;
            do {
                j = (int) ((Math.random() * ((5 - 0) + 1)) + 0);
            } while (!board.isLegalMoves(j));

            return j;
        }
    }
    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {//
            return -1;
        } else if (board.exitLegalMoves() && depth != 5) {
            int heuristicVal;
            if (!maximizingPlayer) {
                int minEva= 1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMoves(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = minimax(depth + 1, true);
                        minEva= min(minEva,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return minEva;
                        }
                    }
                }
            } else {
                int maxEvA = -1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMoves(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = minimax(depth + 1, false);
                        maxEvA = max(maxEvA,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return maxEvA;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

}
