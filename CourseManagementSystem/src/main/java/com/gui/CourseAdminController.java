package com.gui;

import com.models.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CourseAdminController {
    @FXML
    private Label welcomeBackMessage;
    @FXML
    private Label date;
    @FXML
    private Label time;

    @FXML
    public void initialize() {
        this.welcomeBackMessage.setText(String.format("Welcome back, %s", Session.user.getUsername()));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String[] strDays = new String[] { "Sunday", "Monday", "Tuesday",
                "Wednesday", "Thursday", "Friday", "Saturday" };
        String[] strMonths = new String[] { "January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        String dayOfWeek = strDays[calendar.get(Calendar.DAY_OF_WEEK) - 1];
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String month = strMonths[calendar.get(Calendar.MONTH) - 1];
        int year = calendar.get(Calendar.YEAR);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        this.date.setText(String.format("%s, %d %s, %d", dayOfWeek, dayOfMonth, month, year));
        this.time.setText(timeFormatter.format(date));
    }

    @FXML
    public void openManagementView(ActionEvent event) throws IOException {
        Navigator.navigateToCourseListView(event);
    }

    @FXML
    public void openPrintReportView(ActionEvent event) throws IOException {
        Navigator.navigateToPrintReportView(event);
    }
}
