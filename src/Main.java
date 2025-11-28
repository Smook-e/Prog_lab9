import factory.ValidatorFactory;
import model.SudokuBoard;
import task.ValidationResult;
import csv.CsvReader;
import validator.Validator;

/*public class Main {
    public static void main(String[] args) {
        // AUTOMATIC FULL TEST - JUST RUN THIS FILE!
        System.out.println("SUDOKU SOLUTION VERIFIER - FULL AUTOMATIC TEST\n");

        runAllTests("src\\files\\test.csv");
        runAllTests("src\\files\\invalid.csv");

        System.out.println("ALL TESTS COMPLETED!");
    }

    private static void runAllTests(String filename) {
        System.out.println("=".repeat(60));
        System.out.println("Testing: " + filename);
        System.out.println("=".repeat(60));

        int[] modes = {0, 3, 27};
        for (int mode : modes) {
            System.out.println("\n--- Mode " + mode + " ---");
            try {
                int[][] grid = CsvReader.readBoard(filename);
                SudokuBoard board = new SudokuBoard(grid);
                Validator validator = ValidatorFactory.create(mode);
                ValidationResult result = validator.validate(board);

                if (result.isValid) {
                    System.out.println("VALID");
                } else {
                    System.out.println("INVALID");
                    result.errors.forEach(System.out::println);
                }
            } catch (Exception e) {
                System.out.println("ERROR: Could not read " + filename);
                System.out.println("Make sure the file is in the project root folder!");
            }
        }
        System.out.println("\n" + "=".repeat(60));
    }
}*/
public class Main{
    public static void main(String[]args)
    {
        if(args.length!=0)
        {
            System.out.println("java -jar SudokuValidator.jar <csv filepath> <mode>");
            System.out.println("Modes: 0=sequential,3=three-thread,27=27-thread");
            return;
        }
        String path=args[0];
        int mode;
        try{
            mode=Integer.parseInt(args[1]);
        }catch(Exception e)
        {
            System.out.println("Mode must be 0,3 or 27");
            return;
        }
        try {
                int[][] grid = CsvReader.readBoard(path);
                SudokuBoard board = new SudokuBoard(grid);
                Validator validator = ValidatorFactory.create(mode);
                ValidationResult result = validator.validate(board);

                if (result.isValid) {
                    System.out.println("VALID");
                } else {
                    System.out.println("INVALID");
                    result.errors.forEach(System.out::println);
                }
            } catch (Exception e) {
                System.out.println("Error reading file: "+path);
                System.out.println(e.getMessage());
            }  
    }
}