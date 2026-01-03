import java.util.*;
import java.util.Map.Entry;

public class GradeManager {
    private static final HashMap<String, Integer> studentData = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;

        System.out.println("--- Welcome to the Teacher's Grade Portal ---");

        while (isRunning) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a Student");
            System.out.println("2. View All Grades (Unsorted)");
            System.out.println("3. Generate Report (Sorted High to Low)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllGrades();
                        break;
                    case 3:
                        generateSortedReport();
                        break;
                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try 1-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: No text, please enter a number .");
                scanner.nextLine();
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Grade (0-100): ");
        try {
            int grade = scanner.nextInt();

            if (grade < 0 || grade > 100) {
                System.out.println("Please enter a valid grade between 0 and 100.");
            } else {
                studentData.put(name, grade);
                System.out.println("Saved: " + name + " -> " + grade);
            }
        } catch (Exception e) {
            System.out.println("Invalid grade input.");
            scanner.nextLine(); // clear buffer
        }
    }

    private static void viewAllGrades() {
        if (studentData.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("\n--- Class Register ---");
        for (String name : studentData.keySet()) {
            System.out.println(name + ": " + studentData.get(name));
        }
    }

    private static void generateSortedReport() {
        if (studentData.isEmpty()) {
            System.out.println("No data to report.");
            return;
        }

        List<Entry<String, Integer>> list = new ArrayList<>(studentData.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("\n--- Final Performance Report (Top to Bottom) ---");
        int rank = 1;
        for (Entry<String, Integer> entry : list) {
            System.out.println("Rank " + rank + ": " + entry.getKey() + " | Grade: " + entry.getValue());
            rank++;
        }
    }
}

