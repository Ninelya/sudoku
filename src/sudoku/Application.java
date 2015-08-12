package sudoku;

import sudoku.logic.Field;
import sudoku.view.MainFrame;
import sudoku.view.MainPanel;

class Application {
    public static void main(String[] args) {

        Field field = new Field();
        MainPanel panel = new MainPanel(field);
        MainFrame frame = new MainFrame(panel);
    }
}