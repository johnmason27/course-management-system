package com.domains;

import com.Session;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.CourseLoader;
import com.models.*;
import com.models.Module;
import com.printers.CoursePrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentEnrolledDomain {
    private static final CourseLoader courseLoader = new CourseLoader();
    private static final StudentEditor studentEditor = new StudentEditor();

    public static void load() {
        if (Session.student.getLevel() == 7) {
            System.out.println("You have already graduated and have no active modules!");
            return;
        }

        Course enrolledCourse = courseLoader.find(Session.student.getEnrolledCourseId());
        System.out.println("Your currently enrolled onto:");
        CoursePrinter.printCourse(enrolledCourse);

        while (true) {
            String[] options = {
                    "1 - View enrolled modules",
                    "2 - Enroll onto modules",
                    "3 - Go back"
            };
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("Would you like to enroll onto modules for your course or view modules your enrolled onto?");
            int option = Input.readInt();
            Student activeStudent = Session.getStudent();

            if (option == 1) {
                System.out.println("Your enrolled modules are:");
                activeStudent.printEnrolledModules();
                break;
            } else if (option == 2) {
                int studentLevel = Session.student.getLevel();
                System.out.printf("You are currently level %d, printing modules for your level or previous levels.%n", studentLevel);

                List<Module> availableLevelModules = enrolledCourse.getModules().stream()
                        .filter(m -> m.getLevel() <= studentLevel && m.getAvailability())
                        .toList();
                ArrayList<Module> availableModules = new ArrayList<>(availableLevelModules);
                ArrayList<UUID> completedModules = activeStudent.getCompletedModules();
                ArrayList<UUID> enrolledModules = activeStudent.getEnrolledModules();

                for (Module availableLevelModule : availableLevelModules) {
                    for (UUID id : enrolledModules) {
                        if (availableLevelModule.getId().equals(id)) {
                            availableModules.remove(availableLevelModule);
                        }
                    }
                }

                for (Module availableLevelModule : availableLevelModules) {
                    for (UUID id : completedModules) {
                        if (availableLevelModule.getId().equals(id)) {
                            availableModules.remove(availableLevelModule);
                        }
                    }
                }

                if (availableModules.size() == 0) {
                    System.out.println("Oops there are no modules to enroll onto.");
                    break;
                }

                enrollOntoModule(availableModules);

                break;
            } else if (option == 3) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private static void enrollOntoModule(ArrayList<Module> availableModules) {
        Student activeStudent = Session.getStudent();
        while (true) {
            CoursePrinter.printModules(new ArrayList<>(availableModules));
            String[] choseToEnrollOptions = {
                    "1 - Yes",
                    "2 - No"
            };
            for (String chooseTooEnrollOption : choseToEnrollOptions) {
                System.out.println(chooseTooEnrollOption);
            }
            System.out.println("Would you like to enroll onto any?");
            int chooseTooEnrollOption = Input.readInt();

            if (chooseTooEnrollOption == 1) {
                while (true) {
                    System.out.println("Enter the module id:");
                    String id = Input.readString();
                    UUID convertedId;

                    try {
                        convertedId = UUID.fromString(id);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid Id, enter another.");
                        continue;
                    }

                    Module foundModule = availableModules.stream()
                            .filter(m -> m.getId().equals(convertedId))
                            .findAny()
                            .orElse(null);

                    if (foundModule == null) {
                        System.err.println("Module does not exist with that id, enter another.");
                    } else {
                        activeStudent.addEnrolledModule(foundModule.getId());
                        studentEditor.update(activeStudent);
                        System.out.println("Enrolled onto module.");
                        break;
                    }
                }
            } else if (chooseTooEnrollOption == 2) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option!");
            }
        }
    }
}
