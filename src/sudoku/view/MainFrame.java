package sudoku.view;

import sudoku.resources.Constants;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(MainPanel panel) {
        super(Constants.TITLE_NAME);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }
}
