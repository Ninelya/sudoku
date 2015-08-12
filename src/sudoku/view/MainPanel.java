package sudoku.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import sudoku.logic.Cell;
import sudoku.logic.Field;
import sudoku.resources.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {

    private JPanel panelMenu;
    private JPanel panelField;
    private PanelNumber selectedPanel;
    private Selected selected;
    private Map<Integer, PanelNumber> panels = new HashMap<>();

    public PanelNumber getPanelByNumber(int number) {
        return panels.get(number);
    }

    public void setPanelValueByNumber(int number, String value) {
        panels.get(number).setLabelNumber(value);
    }

    public MainPanel(Field field) {
        panelMenu = new JPanel();
        panelField = new JPanel();

        //======== this ========
        {
            setPreferredSize(Constants.PREFERRED_SIZE);
            setLayout(new BorderLayout());

            //======== panelMenu ========
            {
                panelMenu.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
            }
            add(panelMenu, BorderLayout.NORTH);

            //======== panelField ========
            {
                panelField.setBackground(Constants.COLOR_SEPARATE);
                panelField.setLayout(new GridLayoutManager(Constants.NINE, Constants.NINE, new Insets(0, 0, 0, 0), 5, 5));

                PanelNumber panel;
                Cell cell;
                int number;
                for (int i = 1; i <= Constants.NINE; i++) {
                    for (int j = 1; j <= Constants.NINE; j++) {
                        number = i * 10 + j;
                        cell = field.getCellById(number);
                        panel = new PanelNumber(cell);
                        panels.put(number, panel);
                        if(cell.getValue() > 0) {
                            panel.showValuePanel();
                        } else {
                            panel.showHintsPanel();
                        }
                        panel.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseEntered(MouseEvent e) {}

                            @Override
                            public void mouseExited(MouseEvent e) {}

                            @Override
                            public void mousePressed(MouseEvent e) {
                                if(e.isShiftDown())
                                    selected.doShiftSelection(((PanelNumber) e.getComponent()).getNumber());
                                else if(e.isControlDown())
                                    selected.doControlSelection(((PanelNumber) e.getComponent()).getNumber());
                                else
                                    selected.doSelection(((PanelNumber) e.getComponent()).getNumber());
                                doSelection();
                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {}

                            @Override
                            public void mouseClicked(MouseEvent e) {}
                        });

                        panelField.add(panel, new GridConstraints(i - 1, j - 1, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                    }
                }
            }
            add(panelField, BorderLayout.CENTER);
        }
        selected = new Selected(55);
    }

    private void doSelection(){
        for (PanelNumber panel : panels.values()){
            panel.setSelected(selected.panels.contains(panel.getNumber()));
            panel.setBorder();
        }
    }
}