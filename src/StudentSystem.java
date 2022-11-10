import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentSystem {
    private final Scanner scanner = new Scanner(System.in);
    StudentManager studentManager = new StudentManager();
    public void menuStudentManager() {
        do {
            try {
                System.out.println("---- STUDENT MANAGER PROGRAM ----");
                System.out.println("Choose function by number (to continue)");
                System.out.println("1. Display student list");
                System.out.println("2. Add student");
                System.out.println("3. Update student");
                System.out.println("4. Delete");
                System.out.println("5. Sort");
                System.out.println("6. Read file CSV");
                System.out.println("7. Write file CSV");
                System.out.println("8. Exit");
                System.out.println("Choose a function:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentManager.display();
                        break;
                    case 2:
                        studentManager.addStudent(scanner);
                        break;
                    case 3:
                        studentManager.updateStudent(scanner);
                        break;
                    case 4:
                        studentManager.removeStudent(scanner);
                        break;
                    case 5:
                        menuSort();
                        break;
                    case 6:
                        studentManager.readCSVFile();
                        break;
                    case 7:
                        studentManager.writeFileCSV();
                        break;
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Wrong Number!");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.err.println("Try again!");
            }
        } while (true);
    }

    private void menuSort() {
        do {
            try {
                System.out.println("---- Sort student average score ----");
                System.out.println("Choose a function by number (to continue)");
                System.out.println("1. Sort average score ascending");
                System.out.println("2. Sort average score descending");
                System.out.println("3. Back");
                System.out.println("Choose a function");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        studentManager.sortAvgScoreAscending();
                        break;
                    case 2:
                        studentManager.sortAvgScoreDescending();
                        break;
                    default:
                        System.out.println("Wrong Number!");
                }
                if (choice == 3) {
                    break;
                }
            } catch(InputMismatchException | NumberFormatException e) {
                    System.err.println("Try again!");
            }
            } while (true) ;
        }
    }
