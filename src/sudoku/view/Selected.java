package sudoku.view;

import java.util.ArrayList;
import java.util.List;

public class Selected {
    public List<Integer> panels = new ArrayList<>();
    private int lastClicked;

    public Selected(int numberOfPanel) {
        panels.add(numberOfPanel);
        lastClicked = numberOfPanel;
    }

    public void doSelection(int numberOfPanel) { //без кнопок
        if(lastClicked == numberOfPanel) {  //нажали там же
            if (panels.size() > 1) {        //было выделено больше одной, оставляем одну
                panels.clear();
                panels.add(numberOfPanel);
            }

        } else {                            //нажали в другом месте
            panels.clear();
            panels.add(numberOfPanel);
            lastClicked = numberOfPanel;
        }
    }

    public void doShiftSelection(int numberOfPanel) {
        int minX;
        int minY;
        int maxX;
        int maxY;
        if(lastClicked != numberOfPanel){
            if(numberOfPanel < lastClicked) {
                minY = numberOfPanel / 10;
                maxY = lastClicked / 10;
            } else {
                minY = lastClicked / 10;
                maxY = numberOfPanel / 10;
            }
            if(numberOfPanel % 10 < lastClicked % 10){
                minX = numberOfPanel % 10;
                maxX = lastClicked % 10;
            } else {
                minX = lastClicked % 10;
                maxX = numberOfPanel % 10;
            }
            lastClicked = numberOfPanel;
            panels.clear();
            for (int i = minY; i <= maxY; i++)
                for (int j = minX; j <= maxX; j++) {
                    panels.add(i * 10 + j);
                }
        }
    }

    public void doControlSelection(int number) {
        if(panels.contains(number))
            panels.remove(panels.indexOf(number));
        else
            panels.add(number);
        lastClicked = number;

    }
}
