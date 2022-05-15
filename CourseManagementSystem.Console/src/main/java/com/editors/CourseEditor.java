package com.editors;

import com.loaders.CourseLoader;
import com.models.Course;
import com.savers.CourseSaver;

import java.util.ArrayList;
import java.util.UUID;

public class CourseEditor implements IEditor<Course> {
    private final CourseLoader loader = new CourseLoader();
    private final CourseSaver saver = new CourseSaver();

    public void add(Course entity) {
        ArrayList<Course> courses = this.loader.loadAll();
        courses.add(entity);
        this.saver.saveAll(courses);
    }

    public void update(Course entity) {
        int index = 0;
        ArrayList<Course> courses = this.loader.loadAll();
        UUID courseId = entity.getId();

        for (int i = 0; i < courses.size(); i ++) {
            if (courses.get(i).getId().equals(courseId)) {
                index = i;
                break;
            }
        }

        courses.set(index, entity);
        this.saver.saveAll(courses);
    }

    public void delete(Course entity) {
        ArrayList<Course> courses = this.loader.loadAll();
        courses.remove(entity);
        this.saver.saveAll(courses);
    }

    public void delete(UUID id) {
        int index = 0;
        ArrayList<Course> courses = this.loader.loadAll();

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        courses.remove(index);
        this.saver.saveAll(courses);
    }
}
