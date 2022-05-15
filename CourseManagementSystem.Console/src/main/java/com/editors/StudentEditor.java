package com.editors;

import com.loaders.StudentLoader;
import com.models.Student;
import com.savers.StudentSaver;

import java.util.ArrayList;
import java.util.UUID;

public class StudentEditor implements IEditor<Student> {
    private final StudentLoader loader = new StudentLoader();
    private final StudentSaver saver = new StudentSaver();

    public void add(Student entity) {
        ArrayList<Student> students = this.loader.loadAll();
        students.add(entity);
        this.saver.saveAll(students);
    }

    public void update(Student entity) {
        int index = 0;
        ArrayList<Student> students = this.loader.loadAll();
        UUID studentId = entity.getId();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                index = i;
                break;
            }
        }

        students.set(index, entity);
        this.saver.saveAll(students);
    }

    public void delete(Student entity) {
        ArrayList<Student> students = this.loader.loadAll();
        students.remove(entity);
        this.saver.saveAll(students);
    }

    public void delete(UUID id) {
        int index = 0;
        ArrayList<Student> students = this.loader.loadAll();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        students.remove(index);
        this.saver.saveAll(students);
    }
}
