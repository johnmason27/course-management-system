package com.domains;

import com.editors.StudentEditor;
import com.io.IOManager;
import com.io.Input;
import com.loaders.StudentLoader;
import com.models.CompletedModuleWithGrade;
import com.models.Student;
import com.printers.StudentPrinter;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class StudentManagementDomain {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final StudentEditor studentEditor = new StudentEditor();
    private static final IOManager ioManager = new IOManager();

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
                ArrayList<Student> students = studentLoader.loadPreGraduates();

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
                ArrayList<Student> students = studentLoader.loadAll();

                if (students.size() == 0) {
                    System.out.println("No students to print, going back!");
                    return;
                }

                Student selectedStudent;
                while (true) {
                    StudentPrinter.printAllStudents(students);
                    System.out.println("Enter the id of the student to print results for:");
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

                String report = StudentPrinter.printReport(selectedStudent);
                System.out.println(report);

                File firstAvailableDrive = File.listRoots()[0];
                String stringSaveDirectory = firstAvailableDrive.getPath() + "\\results";
                File saveDirectory = new File(stringSaveDirectory);

                if (saveDirectory.mkdir()) {
                    System.out.println("Created the save directory for report at: " + saveDirectory.getPath());
                }

                String reportFilePath = String.format("%s\\%s_report.txt", saveDirectory.getPath(), selectedStudent.getUsername());

                ioManager.writeFile(reportFilePath, report);
                System.out.println("Saved report at location: ");
                System.out.println(reportFilePath);
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

            int optionalModuleCount = 0;
            int requiredModuleCount = 0;
            for (CompletedModuleWithGrade c: completedModulesWithGrade) {
                if (c.getModule().getOptional()) {
                    optionalModuleCount += 1;
                } else {
                    requiredModuleCount += 1;
                }
            }

            if (optionalModuleCount < 2 || requiredModuleCount < 2) {
                System.err.println("Student hasn't passed at least 2 optional and 2 required, level 6 modules yet. They cannot graduate.");
            } else {
                student.setLevel(7);
                studentEditor.update(student);
                System.out.println("Student is now graduated!");
            }
        }
    }
}
