package lk.ijse.dep.service;

public interface BoardUi {
    void update(int col, boolean isHuman);

    void notifyWinner(Winner winner);
}
