package com;

import com.domains.CreateAccountDomain;
import com.domains.ForgotPasswordDomain;
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

public class Main {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final InstructorLoader instructorLoader = new InstructorLoader();
    private static final AdminLoader adminLoader = new AdminLoader();
    private static final CourseLoader courseLoader = new CourseLoader();
    private static final StudentEditor studentEditor = new StudentEditor();
    private static final AdminEditor adminEditor = new AdminEditor();
    private static final InstructorEditor instructorEditor = new InstructorEditor();
    private static final CourseEditor courseEditor = new CourseEditor();

    public static void main(String[] args) {
        String[] options = {
                "1 - Login",
                "2 - Create Account",
                "3 - Forgot Password",
                "4 - Exit"
        };

        int option;

        while (true) {
            printOptions(options);
            option = Input.readInt();

            switch (option) {
                case 1 -> new LoginDomain(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor).load();
                case 2 -> new CreateAccountDomain(studentLoader, instructorLoader, adminLoader, studentEditor, instructorEditor, adminEditor).load();
                case 3 -> new ForgotPasswordDomain(studentLoader, instructorLoader, adminLoader, studentEditor, instructorEditor, adminEditor).load();
                case 4 -> System.exit(0);
            }
        }
    }

    private static void printOptions(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("What would you like to do?");
    }
}
