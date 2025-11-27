package validator;

import model.SudokuBoard;
import task.ValidationTask;
import task.ValidationResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequentialValidator implements Validator {
    @Override
    public ValidationResult validate(SudokuBoard board) {
        ValidationResult total = new ValidationResult();
        List<ValidationTask> tasks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            tasks.add(new ValidationTask(board, "ROW", i));
            tasks.add(new ValidationTask(board, "COL", i));
            tasks.add(new ValidationTask(board, "BOX", i));
        }

        for (ValidationTask task : tasks) {
            task.run();
            total.merge(task.getResult());
        }

        Collections.sort(total.errors);
        return total;
    }
}