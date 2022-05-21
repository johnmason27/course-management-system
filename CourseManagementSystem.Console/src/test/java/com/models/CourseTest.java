package com.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course;
    private UUID id;
    private String name;
    private boolean availability;
    private ArrayList<Module> modules;
    private Module module1;

    @BeforeEach
    public void setup() {
        this.id = UUID.randomUUID();
        this.name = "Fake Course";
        this.availability = true;
        this.modules = new ArrayList<>();
        this.module1 = new Module(UUID.randomUUID(), "Module1", true, 4, false, UUID.randomUUID());
        Module module2 = new Module(UUID.randomUUID(), "Module2", true, 4, false, UUID.randomUUID());
        this.modules.add(this.module1);
        this.modules.add(module2);
        this.course = new Course(this.id, this.name, this.availability, this.modules);
    }

    @Test
    public void testTheCourseGetsPopulated() {
        assertEquals(this.id, this.course.getId());
        assertEquals(this.name, this.course.getName());
        assertEquals(this.availability, this.course.getAvailability());
        assertEquals(this.modules.size(), this.course.getModules().size());
        assertEquals(this.modules.get(0).getName(), this.course.getModules().get(0).getName());
        assertEquals(this.modules.get(1).getName(), this.course.getModules().get(1).getName());
    }

    @Test
    public void testItSetsTheId() {
        UUID newId = UUID.randomUUID();
        this.course.setId(newId);
        assertEquals(newId, this.course.getId());
    }

    @Test
    public void testItSetsTheName() {
        String newName = "New Course Name";
        this.course.setName(newName);
        assertEquals(newName, this.course.getName());
    }

    @Test
    public void testItSetsTheAvailability() {
        boolean newAvailability = false;
        this.course.setAvailability(availability);
        assertEquals(newAvailability, this.course.getAvailability());
    }

    @Test
    public void testItSetsTheModules() {
        this.course.setModules(new ArrayList<>());
        assertEquals(0, this.course.getModules().size());
    }

    @Test
    public void testItAddsANewModule() {
        Module newModule = new Module(UUID.randomUUID(), "New Module", true, 6, false, UUID.randomUUID());
        this.course.addModule(newModule);
        assertEquals(newModule.getName(), this.course.getModules().get(2).getName());
        assertEquals(3, this.course.getModules().size());
    }

    @Test
    public void testItRemovesAExistingModule() {
        Module existingModule = this.course.getModules().get(0);
        assertEquals(2, this.course.getModules().size());
        this.course.removeModule(existingModule);
        assertEquals(1, this.course.getModules().size());
    }

    @Test
    public void testItFindsAModuleByName() {
        Module foundModule = this.course.findModule(this.module1);
        assertEquals(this.module1.getId(), foundModule.getId());
        assertEquals(this.module1.getName(), foundModule.getName());
        assertEquals(this.module1.getLevel(), foundModule.getLevel());
        assertEquals(this.module1.getAvailability(), foundModule.getAvailability());
        assertEquals(this.module1.getOptional(), foundModule.getOptional());
        assertEquals(this.module1.getInstructor(), foundModule.getInstructor());
    }

    @Test
    public void testItUpdatesAModule() {
        this.module1.setName("New name!");
        this.course.updateModule(this.module1);
        Module existingModule = this.course.findModule(this.module1);
        assertEquals("New name!", existingModule.getName());
    }

    @Test
    public void testItTogglesAModuleAvailability() {
        assertTrue(this.course.getAvailability());
        this.course.setAvailability(false);
        assertFalse(this.course.getAvailability());
    }

    @Test
    public void testItGetsAllUnassignedModules() {
        assertEquals(0, this.course.getUnassignedModules().size());
    }

    @Test
    public void testItGetsAllAssignedModules() {
        assertEquals(2, this.course.getAssignedModules().size());
    }
}
