package factory;

import validator.*;

public class ValidatorFactory {
    public static Validator create(int mode) {
        return switch (mode) {
            case 0 -> new SequentialValidator();
            case 3 -> new ThreeThreadValidator();
            case 27 -> new TwentySevenThreadValidator();
            default -> throw new IllegalArgumentException("Mode must be 0, 3, or 27");
        };
    }
}