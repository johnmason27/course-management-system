package com.models;

import com.loaders.CourseLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InstructorTest {
    private Instructor instructor;
    private UUID id;
    private UserType userType;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;
    private ArrayList<UUID> assignedModules;
    private UUID module1;
    private UUID module2;
    private UUID module3;

    @BeforeEach
    public void setup() {
        this.id = UUID.randomUUID();
        this.userType = UserType.Admin;
        this.forename = "John";
        this.surname = "Mason";
        this.email = "jjnrmason@me.com";
        this.username = "john.mason";
        this.password = "password";
        this.assignedModules = new ArrayList<>();
        this.module1 = UUID.randomUUID();
        this.module2 = UUID.randomUUID();
        this.module3 = UUID.randomUUID();
        this.assignedModules.add(this.module1);
        this.assignedModules.add(this.module2);
        this.assignedModules.add(this.module3);
        this.instructor = new Instructor(this.id, this.userType, this.forename, this.surname, this.email, this.username, this.password, this.assignedModules);
    }

    @Test
    public void testItPopulatesTheParentClass() {
        assertEquals(this.id, this.instructor.getId());
        assertEquals(this.userType, this.instructor.getUserType());
        assertEquals(this.forename, this.instructor.getForename());
        assertEquals(this.surname, this.instructor.getSurname());
        assertEquals(this.email, this.instructor.getEmail());
        assertEquals(this.username, this.instructor.getUsername());
        assertEquals(this.password, this.instructor.getPassword());
    }

    @Test
    public void testItPopulatesTheAssignedModules() {
        assertEquals(this.assignedModules.size(), this.instructor.getAssignedModules().size());
    }

    @Test
    public void testYouCanAddAnAssignedModule() {
        UUID newModuleId = UUID.randomUUID();
        this.instructor.addAssignedModule(newModuleId);
        assertEquals(newModuleId, this.instructor.getAssignedModules().get(3));
    }

    @Test
    public void testYouCannotAddMoreThan4AssignedModules() {
        UUID fourthModuleId = UUID.randomUUID();
        this.instructor.addAssignedModule(fourthModuleId);
        UUID fifthModuleId = UUID.randomUUID();

        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> {
           this.instructor.addAssignedModule(fifthModuleId);
        });

        assertEquals("Cannot assign more than 4 modules to an instructor at one time.", thrown.getMessage());
    }

    @Test
    public void testYouCanRemoveAnAssignedModule() {
        UUID newModuleId = UUID.randomUUID();
        this.instructor.addAssignedModule(newModuleId);
        assertEquals(newModuleId, this.instructor.getAssignedModules().get(3));
        assertEquals(4, this.instructor.getAssignedModules().size());

        this.instructor.removeAssignedModule(newModuleId);
        assertEquals(3, this.instructor.getAssignedModules().size());
    }

    @Test
    public void testYouCanSetMultipleAssignedModulesAtOnce() {
        ArrayList<UUID> moduleIds = new ArrayList<>();
        UUID module1 = UUID.randomUUID();
        UUID module2 = UUID.randomUUID();
        moduleIds.add(module1);
        moduleIds.add(module2);

        this.instructor.setAssignedModules(moduleIds);

        assertEquals(moduleIds, this.instructor.getAssignedModules());
    }

    @Test
    public void testYouCanGetAssignedModulesWithDetails() {
        CourseLoader mockCourseLoader = mock(CourseLoader.class);
        ArrayList<Course> mockedCourses = new ArrayList<>();

        ArrayList<Module> mockedModules = new ArrayList<>();
        Module module1 = new Module(this.module1, "Module1", true, 4, false, this.id);
        Module module2 = new Module(this.module2, "Module2", true, 4, false, this.id);
        Module module3 = new Module(this.module3, "Module3", true, 4, false, this.id);
        mockedModules.add(module1);
        mockedModules.add(module2);
        mockedModules.add(module3);
        Course mockedCourse = new Course(UUID.randomUUID(), "Mocked Course", true, mockedModules);

        mockedCourses.add(mockedCourse);
        when(mockCourseLoader.loadAll()).thenReturn(mockedCourses);

        ArrayList<Module> assignedModulesWithDetails = this.instructor.getAssignedModulesWithDetails(mockCourseLoader);

        assertEquals(3, assignedModulesWithDetails.size());
        assertEquals("Module1", assignedModulesWithDetails.get(0).getName());
        assertEquals("Module2", assignedModulesWithDetails.get(1).getName());
        assertEquals("Module3", assignedModulesWithDetails.get(2).getName());
    }
}
