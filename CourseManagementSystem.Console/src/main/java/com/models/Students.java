package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import de.vandermeer.asciitable.AsciiTable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class Students {
    private static IOManager ioManager;
    private static ArrayList<Student> students;

    public Students() {
        ioManager = new IOManager();
        String studentsFileContents = ioManager.readFile(FileNames.Students);
        Gson gson = new Gson();
        Type studentListType = new TypeToken<ArrayList<Student>>(){}.getType();
        students = gson.fromJson(studentsFileContents, studentListType);
    }

    public static void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static Student getStudent(UUID id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public static void updateStudent(Student student) {
        int index = 0;
        UUID studentId = student.getId();

        for (int i = 0; i < students.size(); i ++) {
            if (students.get(i).getId().equals(studentId)) {
                index = i;
                break;
            }
        }

        students.set(index, student);
        saveStudents();
    }

    public static void printStudents(UUID moduleId) {
        ArrayList<User> usersOnModule = new ArrayList<>();
        for (Student student :
                students) {
            Course enrolledCourse = Courses.findCourse(student.getEnrolledCourseId());
            ArrayList<Module> enrolledModules = enrolledCourse.getModules();
            for (Module module :
                    enrolledModules) {
                if (module.getId().equals(moduleId)) {
                    usersOnModule.add(Users.findStudent(student.getId()));
                }
            }
        }

        if (usersOnModule.size() == 0) {
            System.out.println("Oh looks like there are no students on the module.");
            return;
        }

        AsciiTable usersTable = new AsciiTable();
        usersTable.addRule();
        usersTable.addRow("Id", "Username");
        usersTable.addRule();

        for (User user :
                usersOnModule) {
            usersTable.addRow(user.getId(), user.getUsername());
            usersTable.addRule();
        }

        System.out.println(usersTable.render());
    }

    private static void saveStudents() {
        Gson gson = new Gson();
        String studentsJson = gson.toJson(students);
        ioManager.writeFile(FileNames.Students, studentsJson);
    }
}
