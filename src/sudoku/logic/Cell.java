package sudoku.logic;

import sudoku.resources.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public List<Integer> possibleValues = new ArrayList<>();
    private int value;
    private int id;

    private int rowId;
    private int colId;
    private int blockId;
    private boolean isDefault;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColId() {
        return colId;
    }

    public int getBlockId() {
        return blockId;
    }

    public boolean isEvenBlock() {
        return (blockId & 1) == 0;
    }

    public Cell(int id) {

        value = 0;
        for (int i = 1; i <= Constants.NINE; i++)
            possibleValues.add(i);
        this.id = id;
        rowId = id / 10;
        colId = id % 10;
        blockId = ((rowId - 1) / 3) * 3 + ((colId - 1) / 3) + 1;
    }

    public String getStringValue() {
        return String.valueOf(value);
    }

    void setValue(int value) {
        if (isValid(value)) {
            this.value = value;

        } else
            ;//todo show wrong value

    }

    public boolean isValid(int value) {

        return true;//todo
    }

    @Override
    public String toString() {
        return "Cell{" +
                " value = " + value +
                " , id = " + id +
                " , rowId = " + rowId +
                " , colId = " + colId +
                " , blockId = " + blockId +
                " }\n";
    }
}