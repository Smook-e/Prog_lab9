package validator;

import model.SudokuBoard;
import task.ValidationTask;
import task.ValidationResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class TwentySevenThreadValidator implements Validator {
    @Override
    public ValidationResult validate(SudokuBoard board) {
        ValidationResult total = new ValidationResult();
        ExecutorService pool = Executors.newFixedThreadPool(27);
        List<ValidationTask> tasks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            tasks.add(new ValidationTask(board, "ROW", i));
            tasks.add(new ValidationTask(board, "COL", i));
            tasks.add(new ValidationTask(board, "BOX", i));
        }

        List<Future<ValidationResult>> futures = new ArrayList<>();
        for (ValidationTask task : tasks) {
            futures.add(pool.submit(() -> {
                task.run();
                return task.getResult();
            }));
        }

        for (Future<ValidationResult> f : futures) {
            try {
                total.merge(f.get());
            } catch (Exception ignored) {}
        }

        pool.shutdown();
        Collections.sort(total.errors);
        return total;
    }
}