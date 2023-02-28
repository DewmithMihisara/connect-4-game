package lk.ijse.dep.service;

public class BoardImpl implements Board {
    Piece winningPiece = Piece.EMPTY;
    int col1, col2, row1, row2;
    private final Piece[][] pices;
    private final BoardUi boardController;
    public BoardImpl(BoardUi boardController) {
        pices = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardController = boardController;

        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pices[i][j] = Piece.EMPTY;
            }
        }
    }
    @Override
    public BoardUi getBoardUi() {
        return boardController;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        int temp = -1;
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pices[col][i] == Piece.EMPTY) {
                temp = i;
                break;
            }
        }
        return temp;
    }
    @Override
    public boolean isLegalMoves(int col) {
        int isLegal = findNextAvailableSpot(col);
        return isLegal != (-1);
    }
    @Override
    public boolean exitLegalMoves() {
        boolean temp = false;
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (pices[i][j] == Piece.EMPTY) {
                    temp = true;
                    break;
                }
            }
        }
        return temp;
    }
    @Override
    public void updateMove(int col, Piece move) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pices[col][i] == Piece.EMPTY) {
                pices[col][i] = move;
                break;
            }
        }
    }
    @Override
    public void updateMove(int col, int row, Piece move){
        pices[col][row]=move;
    }
    @Override
    public Winner findWinner() {
        Piece winningPiece ;

        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int k = 0; k < 2; k++) {
                if (pices[i][k] == pices[i][k + 1] && pices[i][k + 1] == pices[i][k + 2] && pices[i][k + 2] == pices[i][k + 3]) {
                    if(pices[i][k] !=Piece.EMPTY) {
                        winningPiece = pices[i][k];
                        col1 = i;
                        col2 = i;
                        row1 = k;
                        row2 = k + 3;
                        return new Winner(winningPiece, col1, row1, col2, row2);
                    }
                }
            }
        }
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int k = 0; k < 3; k++) {
                if (pices[k][i] == pices[k + 1][i] && pices[k + 1][i] == pices[k + 2][i] && pices[k + 2][i] == pices[k + 3][i]) {
                    if(pices[k][i] !=Piece.EMPTY) {
                        winningPiece = pices[k][i];
                        col1 = k;
                        col2 = k + 3;
                        row1 = i;
                        row2 = i;
                        return new Winner(winningPiece, col1, row1, col2, row2);
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
