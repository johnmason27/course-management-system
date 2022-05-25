package com.domains.admin;

import com.editors.CourseEditor;
import com.io.Input;
import com.loaders.CourseLoader;
import com.models.Course;
import com.models.Module;
import com.printers.CoursePrinter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Houses the CourseManageDomain where admins can create, edit and delete courses.
 */
public class CourseManagementDomain {
    private final CourseLoader courseLoader;
    private final CourseEditor courseEditor;

    /**
     * Initialize the CourseManagementDomain.
     * @param courseLoader Load courses
     * @param courseEditor Edit courses
     */
    public CourseManagementDomain(CourseLoader courseLoader, CourseEditor courseEditor) {
        this.courseLoader = courseLoader;
        this.courseEditor = courseEditor;
    }

    /**
     * Load the CourseManagementDomain.
     */
    public void load() {
        String[] options = {
                "1 - Add Courses",
                "2 - Edit Courses",
                "3 - Delete Courses",
                "4 - Go Back",
        };

        while (true) {
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                this.addCourses();
            } else if (option == 2) {
                this.editCourses();
            } else if (option == 3) {
                this.deleteCourses();
            } else if (option == 4) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private void addCourses() {
        System.out.println("Before you decide to add a course here are the existing courses.");
        CoursePrinter.printCourses(this.courseLoader.loadAll());
        String courseName;
        ArrayList<Module> courseModules = new ArrayList<>();
        boolean availability;
        // Get Course Name
        while (true) {
            System.out.println("Enter course name:");
            courseName = Input.readString();

            if (courseName.length() == 0) {
                System.err.println("Course name cannot be blank.");
            } else if (this.courseLoader.find(courseName) != null) {
                System.err.println("Course already exists with that name enter another.");
            } else {
                System.out.println("Course name valid.");
                break;
            }
        }
        // Add Modules
        while (true) {
            String[] options = {
                    "1 - Add Module",
                    "2 - Edit Module",
                    "3 - Remove Module",
                    "4 - Confirm Modules"
            };
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                this.addModule(courseModules);
            } else if (option == 2) {
                this.editModule(courseModules);
            } else if (option == 3) {
                this.removeModule(courseModules);
            } else if (option == 4) {
                System.out.println("Modules added are:");
                CoursePrinter.printModules(courseModules);
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
        // Set availability
        while (true) {
            String[] options = {
                    "1 - Available",
                    "2 - Unavailable"
            };
            for (String option : options) {
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
        // Create course
        Course newCourse = new Course(UUID.randomUUID(), courseName, availability, courseModules);
        this.courseEditor.add(newCourse);
        System.out.println("Created new course:");
        CoursePrinter.printCourse(newCourse);
    }

    private void editCourses() {
        ArrayList<Course> existingCourses = this.courseLoader.loadAll();
        if (existingCourses.size() == 0) {
            System.out.println("No courses to edit.");
            return;
        }

        System.out.println("Before you decide to edit a course here are the existing courses.");
        CoursePrinter.printCourses(existingCourses);

        while (true) {
            String[] options = {
                    "1 - Edit a course",
                    "2 - Go back"
            };
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                // Edit course
                System.out.println("Enter the id of the course to edit:");
                String id = Input.readString();
                UUID guidId;

                try {
                    guidId = UUID.fromString(id);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid Id, enter another.");
                    continue;
                }

                // Find the existing course
                Course existingCourse = existingCourses.stream()
                        .filter(c -> c.getId().equals(guidId))
                        .findAny()
                        .orElse(null);

                if (existingCourse == null) {
                    System.err.println("Course does not exist. Enter another id.");
                } else {
                    System.out.println("Found course to edit.");
                    String[] editOptions = {
                            "1 - Edit Name",
                            "2 - Edit Availability",
                            "3 - Edit Modules",
                            "4 - Go back"
                    };
                    for (String editOption : editOptions) {
                        System.out.println(editOption);
                    }
                    System.out.println("What would you like to do?");
                    int editOption = Input.readInt();

                    if (editOption == 1) {
                        // Edit name
                        System.out.printf("Existing name is '%s' enter new name:%n", existingCourse.getName());
                        String newName = Input.readString();

                        if (newName.length() == 0) {
                            System.err.println("Name cannot be blank.");
                        } else if (existingCourses.stream()
                                .filter(c -> c.getName().equals(newName))
                                .findAny()
                                .orElse(null) != null) {
                            System.err.println("Course already exists with that name, enter another.");
                        } else {
                            existingCourse.setName(newName);
                            this.courseEditor.update(existingCourse);
                            System.out.printf("Name changed to: '%s'.%n", newName);
                        }
                    } else if (editOption == 2) {
                        // Edit availability
                        System.out.printf("Existing availability is '%s'.%n", existingCourse.getAvailability() ? "Available" : "Unavailable");
                        String[] availabilityOptions = {
                                "1 - Set to available",
                                "2 - Set to unavailable",
                                "3 - Go back"
                        };
                        for (String availabilityOption : availabilityOptions) {
                            System.out.println(availabilityOption);
                        }
                        System.out.println("What would you like to do?");
                        int availabilityOption = Input.readInt();

                        if (availabilityOption == 1) {
                            existingCourse.setAvailability(true);
                            this.courseEditor.update(existingCourse);
                            System.out.println("Course availability set to available.");
                            break;
                        } else if (availabilityOption == 2) {
                            existingCourse.setAvailability(false);
                            this.courseEditor.update(existingCourse);
                            System.out.println("Course availability set to unavailable.");
                            break;
                        } else if (availabilityOption == 3) {
                            System.out.println("Going back...");
                            break;
                        } else {
                            System.err.println("Enter valid option.");
                        }
                    } else if (editOption == 3) {
                        // Edit modules
                        ArrayList<Module> existingModules = existingCourse.getModules();
                        if (existingModules.size() == 0) {
                            System.out.println("No existing modules to edit.");
                            while (true) {
                                String[] addModuleOptions = {
                                        "1 - Add module",
                                        "2 - Go back..."
                                };
                                for (String addModuleOption : addModuleOptions) {
                                    System.out.println(addModuleOption);
                                }
                                System.out.println("Would you like to add a new module?");
                                int addModuleOption = Input.readInt();

                                if (addModuleOption == 1) {
                                    this.addModule(existingModules);
                                    this.courseEditor.update(existingCourse);
                                    break;
                                } else if (addModuleOption == 2) {
                                    System.out.println("Going back...");
                                    break;
                                } else {
                                    System.err.println("Enter a valid option.");
                                }
                            }
                            return;
                        }

                        while (true) {
                            String[] editModulesOnCourseOptions = {
                                    "1 - Add modules",
                                    "2 - Edit existing modules",
                                    "3 - Go back"
                            };
                            for (String editModulesOnCourseOption : editModulesOnCourseOptions) {
                                System.out.println(editModulesOnCourseOption);
                            }
                            int editModulesOnCourseOption = Input.readInt();

                            if (editModulesOnCourseOption == 1) {
                                String[] addModuleOptions = {
                                        "1 - Add module",
                                        "2 - Go back..."
                                };
                                for (String addModuleOption : addModuleOptions) {
                                    System.out.println(addModuleOption);
                                }
                                System.out.println("Would you like to add a new module?");
                                int addModuleOption = Input.readInt();

                                if (addModuleOption == 1) {
                                    // Add module
                                    this.addModule(existingModules);
                                    this.courseEditor.update(existingCourse);
                                    return;
                                } else if (addModuleOption == 2) {
                                    System.out.println("Going back...");
                                    return;
                                } else {
                                    System.err.println("Enter a valid option.");
                                }
                            } else if (editModulesOnCourseOption == 2) {
                                while (true) {
                                    System.out.println("Existing modules:");
                                    CoursePrinter.printModules(existingModules);
                                    String[] chooseModuleOptions = {
                                            "1 - Choose module",
                                            "2 - Go back"
                                    };
                                    for (String chooseModuleOption : chooseModuleOptions) {
                                        System.out.println(chooseModuleOption);
                                    }
                                    System.out.println("What do you want to do?");

                                    int chooseModuleOption = Input.readInt();

                                    if (chooseModuleOption == 1) {
                                        System.out.println("Which module should we edit?");
                                        String moduleId = Input.readString();
                                        UUID moduleGuidId;

                                        try {
                                            moduleGuidId = UUID.fromString(moduleId);
                                        } catch (IllegalArgumentException e) {
                                            System.err.println("Invalid Id. Enter another.");
                                            continue;
                                        }

                                        // Check existing module exists
                                        Module existingModule = existingModules.stream()
                                                .filter(m -> m.getId().equals(moduleGuidId))
                                                .findAny()
                                                .orElse(null);

                                        if (existingModule == null) {
                                            System.err.println("No module with that Id. Try another.");
                                            return;
                                        } else {
                                            System.out.println("Module found.");
                                        }

                                        while (true) {
                                            String[] editModuleOptions = {
                                                    "1 - Edit Name",
                                                    "2 - Edit Availability",
                                                    "3 - Edit Level",
                                                    "4 - Edit optionality",
                                                    "5 - Remove",
                                                    "6 - Go back"
                                            };
                                            for (String editModuleOption : editModuleOptions) {
                                                System.out.println(editModuleOption);
                                            }
                                            System.out.println("What would you like to do?");
                                            int editModuleOption = Input.readInt();

                                            if (editModuleOption == 1) {
                                                // Edit name
                                                System.out.printf("Existing name is '%s', enter new name:%n", existingModule.getName());
                                                while (true) {
                                                    String newName = Input.readString();

                                                    if (newName.length() == 0) {
                                                        System.err.println("Name cannot be blank.");
                                                    } else if (existingModules.stream()
                                                            .filter(m -> m.getName().equals(newName))
                                                            .findAny()
                                                            .orElse(null) != null) {
                                                        System.err.println("Module already exists with that name.");
                                                    } else {
                                                        existingModule.setName(newName);
                                                        existingCourse.updateModule(existingModule);
                                                        this.courseEditor.update(existingCourse);
                                                        break;
                                                    }
                                                }
                                            } else if (editModuleOption == 2) {
                                                // Edit availability
                                                System.out.printf("Existing availability is %s:%n", existingModule.getAvailability() ? "Available" : "Unavailable");
                                                while (true) {
                                                    String[] availabilityOptions = {
                                                            "1 - Available",
                                                            "2 - Unavailable",
                                                            "3 - Go back"
                                                    };
                                                    for (String availabilityOption : availabilityOptions) {
                                                        System.out.println(availabilityOption);
                                                    }
                                                    System.out.println("What do you want to set the availability to?");
                                                    int availabilityOption = Input.readInt();

                                                    if (availabilityOption == 1) {
                                                        existingModule.setAvailability(true);
                                                        existingCourse.updateModule(existingModule);
                                                        this.courseEditor.update(existingCourse);
                                                        break;
                                                    } else if (availabilityOption == 2) {
                                                        existingModule.setAvailability(false);
                                                        existingCourse.updateModule(existingModule);
                                                        this.courseEditor.update(existingCourse);
                                                        break;
                                                    } else if (availabilityOption == 3) {
                                                        System.out.println("Going back...");
                                                        break;
                                                    } else {
                                                        System.err.println("Enter a valid option.");
                                                    }
                                                }
                                                break;
                                            } else if (editModuleOption == 3) {
                                                // Edit level
                                                System.out.printf("Existing module is level %d, enter new level 4, 5 or 6:", existingModule.getLevel());
                                                while (true) {
                                                    int level = Input.readInt();

                                                    if (level != 4 && level != 5 && level != 6) {
                                                        System.err.println("Module level should be either 4, 5 or 6. Try again.");
                                                    } else {
                                                        existingModule.setLevel(level);
                                                        existingCourse.updateModule(existingModule);
                                                        this.courseEditor.update(existingCourse);
                                                        System.out.println("Module level updated.");
                                                        break;
                                                    }
                                                }
                                            } else if (editModuleOption == 4) {
                                                // Edit optionality
                                                System.out.printf("Current module is: %s%n", existingModule.getOptional() ? "Optional" : "Required");
                                                while (true) {
                                                    String[] optionalOptions = {
                                                            "1 - Optional",
                                                            "2 - Required"
                                                    };
                                                    for (String optionalOption : optionalOptions) {
                                                        System.out.println(optionalOption);
                                                    }
                                                    int optionalOption = Input.readInt();

                                                    if (optionalOption == 1) {
                                                        existingModule.setOptional(true);
                                                        break;
                                                    } else if (optionalOption == 2) {
                                                        existingModule.setOptional(false);
                                                        break;
                                                    } else {
                                                        System.err.println("Enter a valid option!");
                                                    }
                                                }
                                                this.courseEditor.update(existingCourse);
                                                System.out.printf("Current module is now: %s%n", existingModule.getOptional() ? "Optional" : "Required");
                                            } else if (editModuleOption == 5) {
                                                // Remove module
                                                existingCourse.removeModule(existingModule);
                                                this.courseEditor.update(existingCourse);
                                                System.out.printf("Removed module: '%s' from course.%n", existingModule.getName());
                                            } else if (editModuleOption == 6) {
                                                System.out.println("Going back...");
                                                break;
                                            } else {
                                                System.err.println("Enter a valid option.");
                                            }
                                        }
                                    } else if (chooseModuleOption == 2) {
                                        System.out.println("Going back...");
                                        break;
                                    } else {
                                        System.err.println("Chose a valid option.");
                                    }
                                }
                            } else if (editModulesOnCourseOption == 3) {
                                System.out.println("Going back...");
                                break;
                            } else {
                                System.err.println("Enter a valid option!");
                            }
                        }
                    }
                }
            } else if (option == 2) {
    //          Go back
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private void deleteCourses() {
        ArrayList<Course> existingCourses = this.courseLoader.loadAll();
        if (existingCourses.size() == 0) {
            System.out.println("No courses to delete");
            return;
        }

        System.out.println("Existing courses:");
        CoursePrinter.printCourses(existingCourses);
        while (true) {
            String[] options = {
                    "1 - Delete Course",
                    "2 - Go back"
            };
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                while (true) {
                    System.out.println("Enter the id of the course to delete:");
                    String id = Input.readString();
                    UUID guidId;

                    try {
                        guidId = UUID.fromString(id);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid Id, enter another.");
                        continue;
                    }

                    // Check course trying to be removed exists
                    Course foundCourse = existingCourses.stream()
                            .filter(c -> c.getId().equals(guidId))
                            .findAny()
                            .orElse(null);
                    if (foundCourse == null) {
                        System.err.println("No course with that Id, enter another.");
                    } else {
                        // Delete course
                        this.courseEditor.delete(foundCourse.getId());
                        System.out.println("Course deleted.");
                        break;
                    }
                }
            } else if (option == 2) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private void addModule(ArrayList<Module> modules) {
        String name;
        boolean availability;
        int level;
        boolean optional = false;
        // Get name
        while (true) {
            System.out.println("Enter module name:");
            name = Input.readString();
            String finalName = name;
            Module existingModule = modules.stream()
                    .filter(m -> m.getName().equals(finalName))
                    .findAny()
                    .orElse(null);
            if (name.length() == 0) {
                System.err.println("Module name cannot be blank.");
            } else if (existingModule != null) {
                System.err.println("Module already exists on this course with that name enter another.");
            } else {
                System.out.println("Module name valid.");
                break;
            }
        }
        // Get availability
        String[] options = {
                "1 - Available",
                "2 - Unavailable"
        };
        while (true) {
            for (String option : options) {
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
        // Get level
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

        if (level == 6) {
            while (true) {
                System.out.println("1 - Optional");
                System.out.println("2 - Required");
                System.out.println("Is this module optional or required?");
                int optionalOption = Input.readInt();

                if (optionalOption == 1) {
                    optional = true;
                    break;
                } else if (optionalOption == 2) {
                    break;
                } else {
                    System.err.println("Enter a valid option.");
                }
            }
        }
        // Add module
        Module newModule = new Module(UUID.randomUUID(), name, availability, level, optional, null);
        modules.add(newModule);
        newModule.printModule();
        System.out.println("Module added.");
    }

    private void editModule(ArrayList<Module> modules) {
        // Check there are any to edit
        if (modules.size() == 0) {
            System.out.println("There are no modules to edit");
            return;
        }

        System.out.println("Existing Modules:");
        CoursePrinter.printModules(modules);

        Module existingModule;
        while (true) {
            // Get which module to edit
            System.out.println("Enter the Id of the Module to edit:");
            String id = Input.readString();
            UUID guidId;

            try {
                guidId = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Id, enter another.");
                continue;
            }

            existingModule = modules.stream()
                    .filter(m -> m.getId().equals(guidId))
                    .findAny()
                    .orElse(null);

            if (existingModule == null) {
                System.err.println("Module with that Id does not exist.");
            } else {
                break;
            }
        }
        // Choose which part to edit
        while (true) {
            String[] options = {
                    "1 - Edit Name",
                    "2 - Edit Availability",
                    "3 - Edit Level",
                    "4 - Edit optionality",
                    "5 - Go back"
            };

            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                // Edit name
                while (true) {
                    System.out.printf("Existing name is '%s' enter new Name:%n", existingModule.getName());
                    String newName = Input.readString();

                    if (newName.length() == 0) {
                        System.err.println("Name cannot be empty.");
                    } else if (modules.stream()
                            .filter(m -> m.getName().equals(newName))
                            .findAny()
                            .orElse(null) != null) {
                        System.err.println("Module already exists with that name, enter another.");
                    } else {
                        existingModule.setName(newName);
                        System.out.printf("Module renamed to '%s'.%n", newName);
                        break;
                    }
                }
            } else if (option == 2) {
                // Edit availability
                System.out.printf("Current availability is: '%s'%n", existingModule.getAvailability() ? "Available" : "Unavailable");
                while (true) {
                    String[] availabilityOptions = {
                            "1 - Set to Available",
                            "2 - Set to Unavailable",
                            "3 - Go back"
                    };
                    for (String availabilityOption : availabilityOptions) {
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
                // Edit level
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
                // Edit optionality
                System.out.printf("Current module is: %s%n", existingModule.getOptional() ? "Optional" : "Required");
                while (true) {
                    String[] optionalOptions = {
                            "1 - Optional",
                            "2 - Required"
                    };
                    for (String optionalOption : optionalOptions) {
                        System.out.println(optionalOption);
                    }
                    int optionalOption = Input.readInt();

                    if (optionalOption == 1) {
                        existingModule.setOptional(true);
                        break;
                    } else if (optionalOption == 2) {
                        existingModule.setOptional(false);
                        break;
                    } else {
                        System.err.println("Enter a valid option!");
                    }
                }
            } else if (option == 5) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }

    private void removeModule(ArrayList<Module> modules) {
        // Check there is any modules
        if (modules.size() == 0) {
            System.out.println("No modules to delete");
            return;
        }
        // Get the module and delete
        CoursePrinter.printModules(modules);
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

            Module existingModule = modules.stream()
                    .filter(m -> m.getId().equals(guidId))
                    .findAny()
                    .orElse(null);

            if (existingModule != null) {
                modules.remove(existingModule);
                System.out.println("Module removed. Remaining modules are:");
                CoursePrinter.printModules(modules);
                break;
            } else {
                System.err.println("Module cannot be found, enter another id.");
            }
        }
    }
}
