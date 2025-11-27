package validator;

import model.SudokuBoard;
import task.ValidationResult;

public interface Validator {
    ValidationResult validate(SudokuBoard board);
}