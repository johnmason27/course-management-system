package com.gui;

import com.models.Course;
import com.models.Courses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CourseListViewController {
    @FXML
    public void openManagementView(ActionEvent event) throws IOException {
        Navigator.navigateToCourseListView(event);
    }

    @FXML
    public void openPrintReportView(ActionEvent event) throws IOException {
        Navigator.navigateToPrintReportView(event);
    }

    @FXML
    public void addCourse() {
        Course course = AddCourseBox.display();
        if (course != null) {
            Courses.addCourse(course);
            Courses.saveCourses();
        }
    }
}