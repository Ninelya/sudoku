package sudoku.logic;


import sudoku.resources.Constants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {


    public List<Cell> cells = new ArrayList<>();
    public Map<Integer, Boolean> hasValue = new HashMap<>();

    public Field() {
        init("test.ss");
    }

    void init(String fileName) {
        Cell cell;
        for (int i = 1; i <= Constants.NINE; i++)
            for (int j = 1; j <= Constants.NINE; j++) {
                cell = new Cell(i * 10 + j);
                cells.add(cell);
                hasValue.put(i * 10 + j, false);
            }

        String resource = read(fileName);
        int a = 0;
        int i = 1;
        int j = 1;
        char ch = '0';

        while (a < resource.length()) {
            switch (resource.charAt(a)) {
                case '|':
                case '-':
                    break;
                case '.':
                    j++;
                    break;
                case '\n':
                    if (resource.charAt(a - 1) != '-')
                        i++;
                    j = 1;
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    ch = resource.charAt(a);
                    cell = getCellById(i * 10 + j);
                    cell.setValue(Character.getNumericValue(ch));
                    cell.setDefault(true);
                    int b = i * 10 + j;
                    int m = Character.getNumericValue(ch);
                    modifyPossibleValues(b, m);
                    j++;
                    break;
            }
            a++;
        }
    }

    public void modifyPossibleValues(int cellId, int value) {
        Cell oldCell = getCellById(cellId);
        int rowId = oldCell.getRowId();
        int colId = oldCell.getColId();
        int blockId = oldCell.getBlockId();

        for (Cell cell : cells)
            if (cell.getColId() == colId || cell.getRowId() == rowId || cell.getBlockId() == blockId)
                cell.possibleValues.set(value - 1, 0);
    }

    public Cell getCellById(int id) {
        for (Cell cell : cells)
            if (cell.getId() == id)
                return cell;
        return null;
    }

    public String getCellStringValueById(int id) {
        for (Cell cell : cells)
            if (cell.getId() == id)
                return String.valueOf(cell.getValue());
        return "";
    }

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}