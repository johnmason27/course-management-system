package com.domains;

import com.Session;
import com.io.Input;
import com.models.*;
import com.models.Module;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.UUID;

public class InstructorDomain {
    public static void load() {
        User currentUser = Session.getUser();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        while (true) {
            String[] options = {
                    "1 - View assigned modules",
                    "2 - Assign grades",
                    "3 - Logout"
            };

            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                UUID instructorId = Session.instructor.getId();
                ArrayList<Module> assignedModules = Instructors.getAssignedModules(instructorId);
                System.out.println("Here are your assigned modules:");
                Courses.printModules(assignedModules);

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
                                System.out.printf("Here are the students who are on the module: %s.%n", foundModule.getName());
                                Students.printStudents(foundModule.getId());
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
                throw new NotImplementedException("");
            } else if (option == 3) {
                Session.setUser(null);
                Session.setInstructor(null);
                System.out.println("Logged out!");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
