package csv;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvReader {
    public static int[][] readBoard(String path) throws Exception {
        int[][] board = new int[9][9];
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                String[] values = line.split(",");
                for (int col = 0; col < 9; col++) {
                    board[row][col] = Integer.parseInt(values[col].trim());
                }
                row++;
            }
        }
        return board;
    }
}