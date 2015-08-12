package sudoku.resources;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public interface Constants {
    Color COLOR_SEPARATE = new Color(133, 249, 150);
    Color COLOR_GREEN_EVEN = new Color(204, 255, 204);
    Color COLOR_GREEN_ODD = new Color(171, 252, 177);
    Color COLOR_BORDER = new Color(0, 212, 20);
    Color COLOR_SELECTED = Color.red;

    Dimension PREFERRED_SIZE = new Dimension(600, 600);

    String TITLE_NAME = "Sudoku";

    Font FONT_LABEL = new Font("Tahoma", Font.PLAIN, 48);

    int NINE = 9;
    int THREE = 3;

    LineBorder BORDER_DEFAULT = (LineBorder) BorderFactory.createLineBorder(COLOR_BORDER, 2);
    LineBorder BORDER_ODD = (LineBorder) BorderFactory.createLineBorder(COLOR_GREEN_ODD, 2);
    LineBorder BORDER_EVEN = (LineBorder) BorderFactory.createLineBorder(COLOR_GREEN_EVEN, 2);
    LineBorder BORDER_SELECTED = (LineBorder) BorderFactory.createLineBorder(COLOR_SELECTED, 2);
}
