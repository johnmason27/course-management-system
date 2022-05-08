package com.domains;

import com.Session;
import com.models.Course;
import com.models.Courses;

public class StudentEnrolledDomain {
    public static void load() {
        System.out.println("Here you can view your enrolled course and it's modules.");
        Course enrolledCourse = Courses.findCourse(Session.student.getEnrolledCourseId());

        System.out.println("Your currently enrolled onto:");
        Course.printCourse(enrolledCourse);
//      Print modules depending on level of student
//      Add level onto student
    }
}
