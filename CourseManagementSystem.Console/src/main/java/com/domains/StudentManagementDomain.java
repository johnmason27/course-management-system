package com.domains;

import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.StudentLoader;
import com.models.CompletedModuleWithGrade;
import com.models.Student;
import com.printers.StudentPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentManagementDomain {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final StudentEditor studentEditor = new StudentEditor();

    public static void load() {
        String[] options = {
                "1 - Increase student level",
                "2 - Print student result slip",
                "3 - Go back"
        };
        while (true) {
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                List<Student> filteredStudentList = studentLoader.loadAll().stream()
                        .filter(s -> s.getLevel() == 4 || s.getLevel() == 5 || s.getLevel() == 6)
                        .toList();
                ArrayList<Student> students = new ArrayList<>(filteredStudentList);

                if (students.size() == 0) {
                    System.out.println("No students to manage, going back!");
                    return;
                }

                Student selectedStudent;
                while (true) {
                    StudentPrinter.printAllStudents(students);
                    System.out.println("Enter the id of the student to manage:");
                    String id = Input.readString();
                    UUID studentId;

                    try {
                        studentId = UUID.fromString(id);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid id, enter another!");
                        continue;
                    }

                    selectedStudent = studentLoader.find(studentId);

                    if (selectedStudent == null) {
                        System.err.println("Student does not exist with that id, enter another.");
                    } else {
                        System.out.println("Student found!");
                        break;
                    }
                }

                upgradeStudentLevel(selectedStudent);
            } else if (option == 2) {
                System.out.println("Student is bad!");
                break;
            } else if (option == 3) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option!");
            }
        }
    }

    private static void upgradeStudentLevel(Student student) {
        int level = student.getLevel();

        if (level == 4) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(4);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            if (completedModulesWithGrade.size() < 4) {
                System.err.println("Student hasn't passed at least 4, level 4 modules yet. They cannot progress to the next level.");
            } else {
                System.out.println("Student can progress to the next level!");
                student.setLevel(5);
                studentEditor.update(student);
                System.out.println("Student is now level 5!");
            }
        } else if (level == 5) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(5);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            if (completedModulesWithGrade.size() < 4) {
                System.err.println("Student hasn't passed at least 4, level 5 modules yet. They cannot progress to the next level.");
            } else {
                System.out.println("Student can progress to the next level!");
                student.setLevel(6);
                studentEditor.update(student);
                System.out.println("Student is now level 6!");
            }
        } else if (level == 6) {
            ArrayList<CompletedModuleWithGrade> completedModulesWithGrade = student.getCompletedModulesWithGrade(6);
            StudentPrinter.printCompletedModulesWithGrade(completedModulesWithGrade);

            if (completedModulesWithGrade.size() < 4) {
                System.err.println("Student hasn't passed at least 4, level 6 modules yet. They cannot graduate.");
            } else {
                student.setLevel(7);
                studentEditor.update(student);
                System.out.println("Student is now graduated!");
            }
        }
    }
}
