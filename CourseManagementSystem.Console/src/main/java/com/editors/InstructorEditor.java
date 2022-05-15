package com.editors;

import com.loaders.InstructorLoader;
import com.models.Instructor;
import com.savers.InstructorSaver;

import java.util.ArrayList;
import java.util.UUID;

public class InstructorEditor implements IEditor<Instructor> {
    private final InstructorLoader loader = new InstructorLoader();
    private final InstructorSaver saver = new InstructorSaver();

    public void add(Instructor entity) {
        ArrayList<Instructor> instructors = this.loader.loadAll();
        instructors.add(entity);
        this.saver.saveAll(instructors);
    }

    public void update(Instructor entity) {
        int index = 0;
        ArrayList<Instructor> instructors = this.loader.loadAll();
        UUID instructorId = entity.getId();

        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId().equals(instructorId)) {
                index = i;
                break;
            }
        }

        instructors.set(index, entity);
        this.saver.saveAll(instructors);
    }

    public void delete(Instructor entity) {
        ArrayList<Instructor> instructors = this.loader.loadAll();
        instructors.remove(entity);
        this.saver.saveAll(instructors);
    }

    public void delete(UUID id) {
        int index = 0;
        ArrayList<Instructor> instructors = this.loader.loadAll();

        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        instructors.remove(index);
        this.saver.saveAll(instructors);
    }
}
