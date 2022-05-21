package com.domains.admin;

import com.editors.CourseEditor;
import com.editors.InstructorEditor;
import com.io.Input;
import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.models.*;
import com.models.Module;
import com.printers.CoursePrinter;
import com.printers.InstructorPrinter;

import java.util.ArrayList;
import java.util.UUID;

public class InstructorManagementDomain {
    private final InstructorLoader instructorLoader;
    private final InstructorEditor instructorEditor;
    private final CourseLoader courseLoader;
    private final CourseEditor courseEditor;

    public InstructorManagementDomain(InstructorLoader instructorLoader, InstructorEditor instructorEditor, CourseLoader courseLoader, CourseEditor courseEditor) {
        this.instructorLoader = instructorLoader;
        this.instructorEditor = instructorEditor;
        this.courseLoader = courseLoader;
        this.courseEditor = courseEditor;
    }

    public void load() {
        System.out.println("Here you can manage your instructors by adding/removing them onto modules to teach");

        while (true) {
            String[] options = {
                    "1 - Assign instructor to module",
                    "2 - Remove instructor from module",
                    "3 - Go back"
            };
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                Course chosenCourse = this.chooseCourse("Enter the id of the course to assign somebody too?");
                ArrayList<Module> availableModules = chosenCourse.getUnassignedModules();
                Module chosenModule = this.chooseModule(availableModules, "Enter the id of the module to assign somebody too?");

                ArrayList<Instructor> availableInstructors = this.instructorLoader.findAvailable();
                Instructor chosenInstructor = this.chooseInstructor(availableInstructors);

                chosenModule.setInstructor(chosenInstructor.getId());
                chosenInstructor.addAssignedModule(chosenModule.getId());
                this.instructorEditor.update(chosenInstructor);
                this.courseEditor.update(chosenCourse);
                System.out.printf("Added your chosen instructor onto the module: %s.%n", chosenModule.getName());
                break;
            } else if (option == 2) {
                Course chosenCourse = this.chooseCourse("Enter the id of the course to remove somebody from?");
                ArrayList<Module> availableModules = chosenCourse.getAssignedModules();
                if (availableModules.size() == 0) {
                    System.out.println("No instructors on this course assigned to modules.");
                    break;
                }

                Module chosenModule = this.chooseModule(availableModules, "Enter the id of the module to remove the instructor?");

                UUID instructorId = chosenModule.getInstructor();
                Instructor moduleInstructor = this.instructorLoader.find(instructorId);
                moduleInstructor.removeAssignedModule(chosenModule.getId());
                chosenModule.setInstructor(null);
                this.courseEditor.update(chosenCourse);
                this.instructorEditor.update(moduleInstructor);
                System.out.printf("Removed the chosen instructor from the module: %s.%n", chosenModule.getName());
                break;
            } else if (option == 3) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private Course chooseCourse(String choiceMessage) {
        while (true) {
            ArrayList<Course> availableCourses = this.courseLoader.findAvailable();
            CoursePrinter.printCourses(availableCourses);
            System.out.println(choiceMessage);

            String choice = Input.readString();
            UUID convertedId;

            try {
                convertedId = UUID.fromString(choice);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            Course foundCourse = availableCourses.stream()
                    .filter(c -> c.getId().equals(convertedId))
                    .findAny()
                    .orElse(null);

            if (foundCourse == null) {
                System.err.println("Course doesn't exist with that Id.");
            } else {
                System.out.println("Found course.");
                return foundCourse;
            }
        }
    }

    private Module chooseModule(ArrayList<Module> availableModules, String choiceMessage) {
        while (true) {
            CoursePrinter.printModules(availableModules);
            System.out.println(choiceMessage);

            String choice = Input.readString();
            UUID convertedId;

            try {
                convertedId = UUID.fromString(choice);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            Module foundModule = availableModules.stream()
                    .filter(m -> m.getId().equals(convertedId))
                    .findAny()
                    .orElse(null);

            if (foundModule == null) {
                System.err.println("Module doesn't exist with that Id.");
            } else {
                System.out.println("Found module.");
                return foundModule;
            }
        }
    }

    private Instructor chooseInstructor(ArrayList<Instructor> instructors) {
        while (true) {
            InstructorPrinter.printInstructors(instructors);
            System.out.println("Enter the id of the instructor to add to the module?");

            String choice = Input.readString();
            UUID convertedId;

            try {
                convertedId = UUID.fromString(choice);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            Instructor foundInstructor = instructors.stream()
                    .filter(i -> i.getId().equals(convertedId))
                    .findAny()
                    .orElse(null);

            if (foundInstructor == null) {
                System.err.println("Instructor doesn't exist with that Id.");
            } else {
                System.out.println("Found instructor.");
                return foundInstructor;
            }
        }
    }
}
