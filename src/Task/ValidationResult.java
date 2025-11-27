package Task;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    public boolean isValid;
    public final List<String> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.errors.add(error);
        this.isValid = false;
    }

    public void merge(ValidationResult other) {
        this.errors.addAll(other.errors);
        if (!other.isValid) this.isValid = false;
    }
}