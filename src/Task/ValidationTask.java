package Task;

import model.SudokuBoard;

import java.util.*;

public class ValidationTask implements Runnable {
    private final SudokuBoard board;
    private final String type; // ROW, COL, BOX
    private final int index;   // 0-8
    private ValidationResult result;

    public ValidationTask(SudokuBoard board, String type, int index) {
        this.board = board;
        this.type = type;
        this.index = index;
    }

    @Override
    public void run() {
        result = new ValidationResult();
        Map<Integer, List<Integer>> positions = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            int value;
            int row = 0, col = 0;

            if (type.equals("ROW")) {
                row = index; col = i;
            } else if (type.equals("COL")) {
                row = i; col = index;
            } else { // BOX
                row = 3 * (index / 3) + (i / 3);
                col = 3 * (index % 3) + (i % 3);
            }
            value = board.get(row, col);
            positions.computeIfAbsent(value, k -> new ArrayList<>()).add(i + 1);
        }

        for (Map.Entry<Integer, List<Integer>> entry : positions.entrySet()) {
            if (entry.getValue().size() > 1) {
                List<Integer> pos = entry.getValue();
                Collections.sort(pos);
                String posStr = pos.stream()
                        .map(Object::toString)
                        .collect(java.util.stream.Collectors.joining(", ", "[", "]"));
                String error = type + " " + (index + 1) + ", #" + entry.getKey() + ", " + posStr;
                result.addError(error);
            }
        }
    }

    public ValidationResult getResult() {
        return result;
    }
}