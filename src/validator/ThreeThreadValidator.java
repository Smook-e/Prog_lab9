package validator;

import model.SudokuBoard;
import task.ValidationResult;
import task.ValidationTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ThreeThreadValidator implements Validator {
    @Override
    public ValidationResult validate(SudokuBoard board) {
        ValidationResult total = new ValidationResult();
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Callable<ValidationResult>> callables = new ArrayList<>();

        callables.add(() -> checkAll(board, "ROW"));
        callables.add(() -> checkAll(board, "COL"));
        callables.add(() -> checkAll(board, "BOX"));

        try {
            List<Future<ValidationResult>> futures = pool.invokeAll(callables);
            for (Future<ValidationResult> f : futures) {
                total.merge(f.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

        Collections.sort(total.errors);
        return total;
    }

    private ValidationResult checkAll(SudokuBoard board, String type) {
        ValidationResult result = new ValidationResult();
        for (int i = 0; i < 9; i++) {
            ValidationTask task = new ValidationTask(board, type, i);
            task.run();
            result.merge(task.getResult());
        }
        return result;
    }
}