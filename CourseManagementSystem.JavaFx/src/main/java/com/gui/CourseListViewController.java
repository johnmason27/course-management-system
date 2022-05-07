package com.gui;

import com.models.Course;
import com.models.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class CourseListViewController {
    @FXML
    private TableView<Course> courseTable;

    @FXML
    public void openManagementView(ActionEvent event) throws IOException {
        Navigator.navigateToCourseListView(event);
    }

    @FXML
    public void openPrintReportView(ActionEvent event) throws IOException {
        Navigator.navigateToPrintReportView(event);
    }

    @FXML
    public void initialize() {
        this.setupTable();
    }

    @FXML
    public void addCourse() {
        Course course = AddCourseBox.display();
        if (course != null) {
            Courses.addCourse(course);
            this.courseTable.refresh();
        }
    }

    @FXML
    public void editCourse() {
        Course selectedItem = this.courseTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a course to edit!");
            return;
        }

        EditCourseBox.display(selectedItem);
        this.courseTable.refresh();
    }

    @FXML
    public void toggleRunningState() {
        Course selectedItem = this.courseTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a course to activate or deactivate!");
            return;
        }
        selectedItem.setIsCourseRunning(!selectedItem.getIsCourseRunning());
        Courses.updateCourse(selectedItem);
        this.courseTable.refresh();
    }

    @FXML
    public void deleteCourse() {
        Course selectedItem = this.courseTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Error", "Please select a course to delete!");
            return;
        }
        Courses.deleteCourse(selectedItem);
        this.courseTable.refresh();
    }

    private void setupTable() {
        ObservableList<Course> courses = FXCollections.observableList(Courses.getCourses());
        this.courseTable.setItems(courses);
    }
}