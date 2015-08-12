package sudoku.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import sudoku.logic.Cell;
import sudoku.resources.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PanelNumber extends JPanel {
    private JPanel panelHint;
    private JLabel labelNumber;
    private CardLayout cardLayout = new CardLayout();
    private boolean isSelected = false;
    private Cell cell;
    public Map<Integer, JLabel> labels = new HashMap<>();
    private int number;

    public PanelNumber(Cell cell) {
        panelHint = new JPanel();
        this.cell = cell;
        number = cell.getId();

        //======== this ========
        {
            setLayout(cardLayout);

            //======== panelHint ========
            {
                panelHint.setBackground(cell.getBlockId() % 2 == 0 ? Constants.COLOR_GREEN_EVEN : Constants.COLOR_GREEN_ODD);
                panelHint.setLayout(new GridLayoutManager(Constants.THREE, Constants.THREE, new Insets(0, 0, 0, 0), 0, 0));

                JLabel label;
                int number;
                for (int i = 0; i < Constants.THREE; i++) {
                    for (int j = 0; j < Constants.THREE; j++) {
                        number = i * Constants.THREE + j + 1;
                        int hint = cell.possibleValues.get(number - 1);
                        label = new JLabel(hint > 0 ? String.valueOf(hint) : "");
                        labels.put(number, label);
                        panelHint.add(label, new GridConstraints(i, j, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                    }
                }
            }
            add(panelHint, "cardHint");

            if(cell.getId() == 55)
                isSelected = true;

            //---- labelNumber ----
            labelNumber = new JLabel(cell.getStringValue());
            labelNumber.setFont(Constants.FONT_LABEL);
            labelNumber.setHorizontalAlignment(SwingConstants.CENTER);
            labelNumber.setBackground(cell.getBlockId() % 2 == 0 ? Constants.COLOR_GREEN_EVEN : Constants.COLOR_GREEN_ODD);
            labelNumber.setOpaque(true);
            setBorder();
            add(labelNumber, "cardValue");
        }
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public void setBorder(){
        if(cell.isDefault()) {
            labelNumber.setBorder(Constants.BORDER_DEFAULT);
        } else {
            if(cell.isEvenBlock()) {
                labelNumber.setBorder(Constants.BORDER_EVEN);
                panelHint.setBorder(Constants.BORDER_EVEN);
            } else {
                labelNumber.setBorder(Constants.BORDER_ODD);
                panelHint.setBorder(Constants.BORDER_ODD);
            }
        }
        if(isSelected){
            labelNumber.setBorder(Constants.BORDER_SELECTED);
            panelHint.setBorder(Constants.BORDER_SELECTED);
        }
    }

    public int getNumber() {
        return number;
    }

    public void showValuePanel(){
        cardLayout.show(this, "cardValue");
    }

    public void showHintsPanel(){
        cardLayout.show(this, "cardHint");
    }

    public void setPanelHintByNumber(int number) {
        labels.get(number).setText(String.valueOf(number));
    }

    public void setLabelNumber(String text) {
        labelNumber.setText(text);
        cardLayout.show(this, "cardValue");
    }

    public JPanel getPanelHint() {
        return panelHint;
    }

    public JLabel getLabelHintByNumber(int number) {
        return labels.get(number);
    }

    public JLabel getLabelNumber() {
        return labelNumber;
    }
}