package com.domains.instructor;

import com.Session;
import com.domains.LoginDomain;
import com.editors.AdminEditor;
import com.editors.CourseEditor;
import com.editors.InstructorEditor;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.Grade;
import com.models.Instructor;
import com.models.Module;
import com.models.Student;
import com.printers.CoursePrinter;
import com.printers.StudentPrinter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Houses logic for the InstructorDomain.
 */
public class InstructorDomain extends LoginDomain {
    private final StudentLoader studentLoader;
    private final InstructorLoader instructorLoader;
    private final CourseLoader courseLoader;
    private final StudentEditor studentEditor;

    /**
     * Initialize the InstructorDomain.
     * @param adminLoader Load admins
     * @param studentLoader Load students
     * @param instructorLoader Load instructors
     * @param courseLoader Load courses
     * @param studentEditor Edit students
     * @param adminEditor Edit admins
     * @param instructorEditor Edit instructors
     * @param courseEditor Edit courses
     */
    public InstructorDomain(AdminLoader adminLoader, StudentLoader studentLoader, InstructorLoader instructorLoader, CourseLoader courseLoader, StudentEditor studentEditor, AdminEditor adminEditor, InstructorEditor instructorEditor, CourseEditor courseEditor) {
        super(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);
        this.instructorLoader = instructorLoader;
        this.studentLoader = studentLoader;
        this.studentEditor = studentEditor;
        this.courseLoader = courseLoader;
    }

    /**
     * Load the InstructorDomain.
     */
    @Override
    public void load() {
        Instructor currentUser = Session.getInstructor();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        while (true) {
            String[] options = {
                    "1 - View assigned modules",
                    "2 - Assign grades",
                    "3 - Logout"
            };

            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                UUID instructorId = Session.instructor.getId();
                Instructor instructor = this.instructorLoader.find(instructorId);
                ArrayList<Module> assignedModules = instructor.getAssignedModulesWithDetails(this.courseLoader);
                System.out.println("Here are your assigned modules:");
                CoursePrinter.printModules(assignedModules);

                while (true) {
                    String[] viewOptions = {
                            "1 - View Students on module",
                            "2 - Go back"
                    };
                    for (String viewOption:
                         viewOptions) {
                        System.out.println(viewOption);
                    }
                    System.out.println("Would you like to view the students on a module?");
                    int viewOption = Input.readInt();

                    if (viewOption == 1) {
                        while (true) {
                            // Get the Id of the module to print students from
                            System.out.println("Enter the id of the module:");
                            String id = Input.readString();
                            UUID convertedId;

                            try {
                                convertedId = UUID.fromString(id);
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid id, enter another.");
                                continue;
                            }

                            Module foundModule = assignedModules.stream()
                                    .filter(module -> module.getId().equals(convertedId))
                                    .findAny()
                                    .orElse(null);

                            if (foundModule == null) {
                                System.err.println("No module with that id enter another.");
                            } else {
                                // Print the students on the module
                                System.out.printf("Here are the students who are on the module: %s.%n", foundModule.getName());
                                StudentPrinter.printStudentsOnModule(foundModule.getId());
                                break;
                            }
                        }
                        break;
                    } else if (viewOption == 2) {
                        System.out.println("Going back...");
                        break;
                    } else {
                        System.err.println("Enter a valid option.");
                    }
                }
            } else if (option == 2) {
                // Give grades to students on their assigned modules
                Instructor currentInstructor = Session.getInstructor();
                ArrayList<Module> assignedModules = currentInstructor.getAssignedModulesWithDetails(this.courseLoader);

                if (assignedModules.size() == 0) {
                    System.out.println("You have no modules to give grades too.");
                    return;
                }

                // Get module to assign grades from
                UUID moduleId;
                while (true) {
                    CoursePrinter.printModules(assignedModules);
                    System.out.println("Enter the id of the module to give grades on:");
                    String id = Input.readString();

                    try {
                        moduleId = UUID.fromString(id);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid id, enter another.");
                        continue;
                    }

                    UUID finalModuleId = moduleId;
                    Module existingModule = assignedModules.stream()
                            .filter(m -> m.getId().equals(finalModuleId))
                            .findAny()
                            .orElse(null);

                    if (existingModule == null) {
                        System.err.println("Module does not exist with that id.");
                    } else {
                        System.out.println("Found module.");
                        break;
                    }
                }

                if (this.studentLoader.loadFromModule(moduleId).size() == 0) {
                    System.out.println("There are no students on this module");
                    return;
                }

                // Get the student to add grades too
                UUID studentId;
                Student chosenStudent;
                while (true) {
                    StudentPrinter.printStudentsOnModule(moduleId);
                    System.out.println("Enter the id of the student you want to grade:");
                    String id = Input.readString();

                    try {
                        studentId = UUID.fromString(id);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid id, enter another.");
                        continue;
                    }

                    UUID finalStudentId = studentId;
                    chosenStudent = this.studentLoader.loadFromModule(moduleId).stream()
                            .filter(s -> s.getId().equals(finalStudentId))
                            .findAny()
                            .orElse(null);

                    if (chosenStudent == null) {
                        System.err.println("Student does not exist with that id.");
                    } else {
                        System.out.println("Found student.");
                        break;
                    }
                }

                // Get the grade and save to student
                int grade;
                while (true) {
                    System.out.println("Enter the grade you'd like to give:");
                    System.out.println("This should be a percentage out of 100% with 40% a passing grade.");
                    grade = Input.readInt();

                    if (grade < 0 || grade > 100) {
                        System.err.println("Enter a valid grade.");
                    } else {
                        System.out.printf("You marked %s with %d%% for the module.%n", chosenStudent.getUsername(), grade);
                        break;
                    }
                }

                Grade newGrade = new Grade(UUID.randomUUID(), moduleId, grade);
                chosenStudent.addGrade(newGrade);
                chosenStudent.addCompletedModule(moduleId);
                chosenStudent.removeEnrolledModule(moduleId);
                this.studentEditor.update(chosenStudent);

                System.out.println("Grade given to student.");
            } else if (option == 3) {
                Session.setInstructor(null);
                System.out.println("Logged out!");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
