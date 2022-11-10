
import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> studentList;

    public StudentManager() {
        studentList = new ArrayList<>();
    }

    public boolean checkExistStudentId(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Scanner scanner) {
        try {
            System.out.println("Enter student Id:");
            String studentId = scanner.nextLine();
            if (!checkExistStudentId(studentId)) {
                System.out.println("Enter student full name:");
                String name = scanner.nextLine();
                System.out.println("Enter student age:");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter student gender:");
                String gender = scanner.nextLine();
                System.out.println("Enter student address:");
                String address = scanner.nextLine();
                System.out.println("Enter student average score:");
                double avgScore = Double.parseDouble(scanner.nextLine());
                studentList.add(new Student(studentId, name, age, gender, address, avgScore));
                System.out.println("Add Completed!");
            } else {
                System.out.println("Student Id exist! Enter new student id.");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println("Format input error!");
        }
    }

    public void updateStudent(Scanner scanner) {
        try {
            boolean flagId = false;
            System.out.println("Enter student id want to update:");
            String studentId = scanner.nextLine();
            if (studentId.equals("")) {
                throw new InputMismatchException();
            }
            for (Student student : studentList) {
                if (student.getStudentId().equals(studentId)) {
                    flagId = true;
                    System.out.println("Enter update id from student id " + studentId + ":");
                    String newStudentId = scanner.nextLine();
                    if (!newStudentId.equals("")) {
                        student.setStudentId(newStudentId);
                    }
                    System.out.println("Enter update name:");
                    String name = scanner.nextLine();
                    if (!name.equals("")) {
                        student.setName(name);
                    }
                    System.out.println("Enter update age:");
                    String age = scanner.nextLine();
                    if (!age.equals("")) {
                        student.setAge(Integer.parseInt(age));
                    }
                    System.out.println("Enter update gender:");
                    String gender = scanner.nextLine();
                    if (!gender.equals("")) {
                        student.setGender(gender);
                    }
                    System.out.println("Enter update address:");
                    String address = scanner.nextLine();
                    if (!address.equals("")) {
                        student.setAddress(address);
                    }
                    System.out.println("Enter update average score:");
                    String avgScore = scanner.nextLine();
                    if (!avgScore.equals("")) {
                        student.setAverageScore(Double.parseDouble(avgScore));
                    }
                    System.out.println("Update completed!");
                }
            }
            if (!flagId) {
                System.err.println("Student id not found!");
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println("Format input error!");
        }
    }

    public void removeStudent(Scanner scanner) {
        try {
            boolean flagId = false;
            if (studentList.isEmpty()) {
                System.out.println("No student in list!");
            } else {
                System.out.println("Enter student id remove:");
                String studentId = scanner.nextLine();
                if (studentId.equals("")) {
                    throw new InputMismatchException();
                }
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getStudentId().equals(studentId)) {
                        flagId = true;
                        System.out.println("Do you want to remove?");
                        System.out.println("Type Y to remove");
                        System.out.println("Type other to do nothing");
                        String choice = scanner.nextLine();
                        if (choice.equals("Y")) {
                            studentList.remove(studentList.get(i));
                            System.out.println("Remove Completed!");
                        } else {
                            System.out.println("No remove!");
                            break;
                        }
                    }
                }
                if (!flagId) {
                    System.err.println("Student id not found!");
                }
            }
        } catch (InputMismatchException | NumberFormatException e){
            System.err.println("Format input error!");
        }
    }

    public void display() {
        if (studentList.isEmpty()) {
            System.out.println("No student in list!");
        } else {
            for (Student student: studentList) {
                System.out.println(student);
            }
        }
    }

    public void sortAvgScoreAscending() {
        AvgScoreComparatorAscending avgScoreComparator = new AvgScoreComparatorAscending();
        System.out.println("Sort ascending average score:");
        studentList.sort(avgScoreComparator);
        display();
    }

    public void sortAvgScoreDescending() {
        AvgScoreComparatorDescending avgScoreComparator = new AvgScoreComparatorDescending();
        System.out.println("Sort descending average score:");
        studentList.sort(avgScoreComparator);
        display();
    }

    public void writeFileCSV() {
        try {
            File file = new File("src/data/students.csv");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (!file.exists()) {
                file.createNewFile();
            }
            for (Student student: studentList) {
                bufferedWriter.write(student.getStudentId() + "," + student.getName() + ","
                + student.getAge() + "," + student.getGender() + "," + student.getAddress() + ","
                + student.getAverageScore() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println();
        }
    }

    public void readCSVFile() {
        try {
            File file = new File("src/data/students.csv");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                String[] strings = string.split(",");
                String id = strings[0];
                String name = strings[1];
                int age = Integer.parseInt(strings[2]);
                String gender = strings[3];
                String address = strings[4];
                double avgScore = Double.parseDouble((strings[5]));
                Student student = new Student(id, name, age, gender, address, avgScore);
                studentList.add(student);
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
