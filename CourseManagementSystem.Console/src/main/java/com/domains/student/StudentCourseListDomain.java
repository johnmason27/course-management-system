package com.domains.student;

import com.Session;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.CourseLoader;
import com.models.*;
import com.printers.CoursePrinter;

import java.util.ArrayList;
import java.util.UUID;

public class StudentCourseListDomain {
    private final CourseLoader courseLoader;
    private final StudentEditor studentEditor;

    public StudentCourseListDomain(CourseLoader courseLoader, StudentEditor studentEditor) {
        this.courseLoader = courseLoader;
        this.studentEditor = studentEditor;
    }

    public void load() {
        System.out.println("Welcome, here you can enroll onto courses.");
        String[] options = {
                "1 - View Courses to enroll on",
                "2 - Go back"
        };
        while (true) {
            if (Session.student.getEnrolledCourseId() != null) {
                System.err.println("Your already enrolled onto a course. Going back.");
                break;
            }
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                System.out.println("Available courses:");
                ArrayList<Course> availableCourses = this.courseLoader.findAvailable();
                if (availableCourses.size() == 0) {
                    return;
                }
                CoursePrinter.printCourses(availableCourses);
                while (true) {
                    if (Session.student.getEnrolledCourseId() != null) {
                        break;
                    }
                    String[] availableCoursesOptions = {
                            "1 - Enroll onto a course",
                            "2 - Go back"
                    };
                    for (String availableCoursesOption : availableCoursesOptions) {
                        System.out.println(availableCoursesOption);
                    }
                    System.out.println("What would you like to do?");
                    int availableCoursesOption = Input.readInt();

                    if (availableCoursesOption == 1) {
                        while (true) {
                            System.out.println("Enter the id of the course you'd like to enroll on:");
                            String id = Input.readString();
                            UUID guidId;

                            try {
                                guidId = UUID.fromString(id);
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid Id, enter another.");
                                continue;
                            }

                            Course existingCourse = availableCourses.stream()
                                    .filter(c -> c.getId().equals(guidId))
                                    .findAny()
                                    .orElse(null);

                            if (existingCourse != null) {
                                while (true) {
                                    String[] confirmOptions = {
                                            "1 - Yes",
                                            "2 - No"
                                    };
                                    for (String confirmOption : confirmOptions) {
                                        System.out.println(confirmOption);
                                    }
                                    System.out.printf("Are you sure you want to enroll on the course: '%s'?%n", existingCourse.getName());
                                    int confirmOption = Input.readInt();

                                    if (confirmOption == 1) {
                                        Student activeStudent = Session.getStudent();
                                        activeStudent.setEnrolledCourseId(existingCourse.getId());
                                        this.studentEditor.update(activeStudent);
                                        System.out.printf("Enrolled onto course: '%s'.%n", existingCourse.getName());
                                        break;
                                    } else if (confirmOption == 2) {
                                        System.out.println("Going back...");
                                        break;
                                    } else {
                                        System.err.println("Enter a valid option.");
                                    }
                                }
                                break;
                            } else {
                                System.err.println("No course with that id. Enter another.");
                            }
                        }
                    } else if (availableCoursesOption == 2) {
                        System.out.println("Going back");
                        break;
                    } else {
                        System.err.println("Enter a valid option.");
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
}
