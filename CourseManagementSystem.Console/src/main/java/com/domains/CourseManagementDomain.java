package com.domains;

import com.io.Input;
import com.models.Course;
import com.models.Courses;
import com.models.Module;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class CourseManagementDomain {
    public static void load() {
        String[] options = {
                "1 - Add Courses",
                "2 - Edit Courses",
                "3 - Delete Courses",
                "4 - Go Back",
        };

        while (true) {
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                addCourses();
            } else if (option == 2) {
                editCourses();
            } else if (option == 3) {
                deleteCourses();
            } else if (option == 4) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private static void addCourses() {
        System.out.println("Before you decide to add a course here are the existing courses.");
        printCourses();
        String courseName;
        ArrayList<Module> courseModules = new ArrayList<>();
        boolean availability;
//      Get Course Name
        while (true) {
            System.out.println("Enter course name:");
            courseName = Input.readString();

            if (courseName.length() == 0) {
                System.err.println("Course name cannot be blank.");
            } else if (Courses.findCourse(courseName) != null) {
                System.err.println("Course already exists with that name enter another.");
            } else {
                System.out.println("Course name valid.");
                break;
            }
        }
//      Add Modules
        while (true) {
            String[] options = {
                    "1 - Add Module",
                    "2 - Edit Module",
                    "3 - Remove Module",
                    "4 - Confirm Modules"
            };
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                addModule(courseModules);
            } else if (option == 2) {
                editModule(courseModules);
            } else if (option == 3) {
                removeModule(courseModules);
            } else if (option == 4) {
                System.out.println("Modules added are:");
                printModules(courseModules);
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
//      Set availability
        while (true) {
            String[] options = {
                    "1 - Available",
                    "2 - Unavailable"
            };
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What availability should this course be set to?");
            int option = Input.readInt();

            if (option == 1) {
                availability = true;
                System.out.println("Availability set to Available.");
                break;
            } else if (option == 2) {
                availability = false;
                System.out.println("Availability set to Unavailable");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
//      Create course
        Course newCourse = new Course(UUID.randomUUID(), courseName, availability, courseModules);
        Courses.addCourse(newCourse);
        System.out.println("Created new course:");
        printCourse(newCourse);
    }

    private static void editCourses() {

    }

    private static void deleteCourses() {

    }

    private static void printCourses() {
        ArrayList<Course> existingCourses = Courses.getCourses();
        if (existingCourses.size() == 0) {
            System.out.println("Oh looks like there is no existing courses.");
            return;
        }

        AsciiTable coursesTable = new AsciiTable();
        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();

        for (Course course :
                existingCourses) {
            coursesTable.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
            coursesTable.addRule();
        }

        System.out.println(coursesTable.render());
    }

    private static void printCourse(Course course) {
        AsciiTable coursesTable = new AsciiTable();

        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();
        coursesTable.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
        coursesTable.addRule();

        System.out.println(coursesTable.render());
    }

    private static void printModules(ArrayList<Module> modules) {
        if (modules.size() == 0) {
            System.out.println("There are no modules.");
            return;
        }

        AsciiTable modulesTable = new AsciiTable();
        modulesTable.addRule();
        modulesTable.addRow("Id", "Name", "Level", "Availability");
        modulesTable.addRule();

        for (Module module :
                modules) {
            modulesTable.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable");
            modulesTable.addRule();
        }

        System.out.println(modulesTable.render());
    }

    private static void printModule(Module module) {
        AsciiTable modulesTable = new AsciiTable();

        modulesTable.addRule();
        modulesTable.addRow("Id", "Name", "Level", "Availability");
        modulesTable.addRule();
        modulesTable.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable");
        modulesTable.addRule();

        System.out.println(modulesTable.render());
    }

    private static void addModule(ArrayList<Module> modules) {
        String name;
        boolean availability;
        int level;
//      Get name
        while (true) {
            System.out.println("Enter module name:");
            name = Input.readString();
            String finalName = name;
            Module existingModule = modules.stream().filter(m -> m.getName().equals(finalName)).findAny().orElse(null);
            if (name.length() == 0) {
                System.err.println("Module name cannot be blank.");
            } else if (existingModule != null) {
                System.err.println("Module already exists on this course with that name enter another.");
            } else {
                System.out.println("Module name valid.");
                break;
            }
        }
//      Get availability
        String[] options = {
                "1 - Available",
                "2 - Unavailable"
        };
        while (true) {
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to set the availability to?");
            int option = Input.readInt();

            if (option == 1) {
                System.out.println("Set availability to 'Available'.");
                availability = true;
                break;
            } else if (option == 2) {
                System.out.println("Set availability to 'Unavailable'.");
                availability = false;
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
//      Get level
        while (true) {
            System.out.println("What level is this module: (4, 5 or 6)");
            level = Input.readInt();

            if (level != 4 && level != 5 && level != 6) {
                System.err.println("Level should be 4, 5 or 6.");
            } else {
                System.out.printf("Level set to %d.%n", level);
                break;
            }
        }
//      Add module
        Module newModule = new Module(UUID.randomUUID(), name, availability, level);
        modules.add(newModule);

        printModule(newModule);
        System.out.println("Module added.");
    }

    private static void editModule(ArrayList<Module> modules) {
//      Check there are any to edit
        if (modules.size() == 0) {
            System.out.println("There are no modules to edit");
            return;
        }

        System.out.println("Existing Modules:");
        printModules(modules);

        Module existingModule;
        while (true) {
//          Get which module to edit
            System.out.println("Enter the Id of the Module to edit:");
            String id = Input.readString();
            UUID guidId;

            try {
                guidId = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            existingModule = modules.stream().filter(m -> m.getId().equals(guidId)).findAny().orElse(null);

            if (existingModule == null) {
                System.err.println("Module with that Id does not exist.");
            } else {
                break;
            }
        }
//      Choose which part to edit
        while (true) {
            String[] options = {
                    "1 - Edit Name",
                    "2 - Edit Availability",
                    "3 - Edit Level",
                    "4 - Go back"
            };

            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
//              Edit name
                while (true) {
                    System.out.printf("Existing name is '%s' enter new Name:%n", existingModule.getName());
                    String newName = Input.readString();

                    if (newName.length() == 0) {
                        System.err.println("Name cannot be empty.");
                    } else if (modules.stream().filter(m -> m.getName().equals(newName)).findAny().orElse(null) != null) {
                        System.err.println("Module already exists with that name, enter another.");
                    } else {
                        existingModule.setName(newName);
                        System.out.printf("Module renamed to '%s'.%n", newName);
                        break;
                    }
                }
            } else if (option == 2) {
//              Edit availability
                System.out.printf("Current availability is: '%s'%n", existingModule.getAvailability() ? "Available" : "Unavailable");
                while (true) {
                    String[] availabilityOptions = {
                            "1 - Set to Available",
                            "2 - Set to Unavailable",
                            "3 - Go back"
                    };
                    for (String availabilityOption :
                            availabilityOptions) {
                        System.out.println(availabilityOption);
                    }
                    System.out.println("What do you want to do?");
                    int availabilityOption = Input.readInt();

                    if (availabilityOption == 1) {
                        existingModule.setAvailability(true);
                        System.out.println("Module set to available");
                        break;
                    } else if (availabilityOption == 2) {
                        existingModule.setAvailability(false);
                        System.out.println("Module set to unavailable");
                        break;
                    } else if (availabilityOption == 3) {
                        System.out.println("Going back...");
                        break;
                    } else {
                        System.err.println("Enter a valid option.");
                    }
                }
            } else if (option == 3) {
//              Edit level
                System.out.printf("Current level is: '%d'%n", existingModule.getLevel());
                while (true) {
                    System.out.println("What level do you want to set the module to? (4, 5 or 6)");
                    int level = Input.readInt();

                    if (level != 4 && level != 5 && level != 6) {
                        System.err.println("Enter a valid level, (4, 5 or 6).");
                    } else {
                        existingModule.setLevel(level);
                        System.out.printf("Updated module level to %d.%n", level);
                        break;
                    }
                }
            } else if (option == 4) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private static void removeModule(ArrayList<Module> modules) {
//      Check there is any modules
        if (modules.size() == 0) {
            System.out.println("No modules to delete");
            return;
        }
//      Get the module and delete
        printModules(modules);
        while (true) {
            System.out.println("Which module should we remove?");
            String id = Input.readString();
            UUID guidId;

            try {
                guidId = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            Module existingModule = modules.stream().filter(m -> m.getId().equals(guidId)).findAny().orElse(null);

            if (existingModule != null) {
                modules.remove(existingModule);
                System.out.println("Module removed. Remaining modules are:");
                printModules(modules);
                break;
            } else {
                System.err.println("Module cannot be found, enter another id.");
            }
        }
    }
}
