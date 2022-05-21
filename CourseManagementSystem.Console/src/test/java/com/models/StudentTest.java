package com.models;

import com.loaders.CourseLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentTest {
    private Student student;
    private UUID id;
    private UserType userType;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;
    private UUID enrolledCourseId;
    private int level;
    private ArrayList<UUID> enrolledModules;
    private UUID enrolledModule1;
    private UUID enrolledModule2;
    private ArrayList<UUID> completedModules;
    private UUID completedModule1;
    private UUID completedModule2;
    private ArrayList<Grade> grades;

    @BeforeEach
    public void setup() {
        this.id = UUID.randomUUID();
        this.userType = UserType.Admin;
        this.forename = "John";
        this.surname = "Mason";
        this.email = "jjnrmason@me.com";
        this.username = "john.mason";
        this.password = "password";
        this.enrolledCourseId = UUID.randomUUID();
        this.level = 5;
        this.enrolledModules = new ArrayList<>();
        this.enrolledModule1 = UUID.randomUUID();
        this.enrolledModule2 = UUID.randomUUID();
        this.enrolledModules.add(this.enrolledModule1);
        this.enrolledModules.add(this.enrolledModule2);
        this.completedModules = new ArrayList<>();
        this.completedModule1 = UUID.randomUUID();
        this.completedModule2 = UUID.randomUUID();
        this.completedModules.add(this.completedModule1);
        this.completedModules.add(this.completedModule2);
        this.grades = new ArrayList<>();
        this.grades.add(new Grade(UUID.randomUUID(), this.completedModule1, 90));
        this.grades.add(new Grade(UUID.randomUUID(), this.completedModule2, 90));
        this.student = new Student(this.id, this.userType, this.forename, this.surname, this.email, this.username, this.password, this.enrolledCourseId, this.level, this.enrolledModules, this.completedModules, this.grades);
    }

    @Test
    public void testItPopulatesTheParentClass() {
        assertEquals(this.id, this.student.getId());
        assertEquals(this.userType, this.student.getUserType());
        assertEquals(this.forename, this.student.getForename());
        assertEquals(this.surname, this.student.getSurname());
        assertEquals(this.email, this.student.getEmail());
        assertEquals(this.username, this.student.getUsername());
        assertEquals(this.password, this.student.getPassword());
    }

    @Test
    public void testItPopulatesTheEnrolledCourseId() {
        assertEquals(this.enrolledCourseId, this.student.getEnrolledCourseId());
    }

    @Test
    public void testItPopulatesTheLevel() {
        assertEquals(this.level, this.student.getLevel());
    }

    @Test
    public void testItPopulatesTheEnrolledModules() {
        assertEquals(this.enrolledModules.size(), this.student.getEnrolledModules().size());
    }

    @Test
    public void testItPopulatesTheCompletedModules() {
        assertEquals(this.completedModules.size(), this.student.getCompletedModules().size());
    }

    @Test
    public void testItPopulatesTheGrades() {
        assertEquals(this.grades.size(), this.student.getGrades().size());
    }

    @Test
    public void testItSetsTheEnrolledCourseId() {
        UUID newEnrolledCourseId = UUID.randomUUID();
        this.student.setEnrolledCourseId(newEnrolledCourseId);
        assertEquals(newEnrolledCourseId, this.student.getEnrolledCourseId());
    }

    @Test
    public void testItSetsTheLevel() {
        int newLevel = 4;
        this.student.setLevel(newLevel);
        assertEquals(newLevel, this.student.getLevel());
    }

    @Test
    public void testItSetsTheEnrolledModules() {
        assertEquals(2, this.student.getEnrolledModules().size());
        this.student.setEnrolledModules(new ArrayList<>());
        assertEquals(0, this.student.getEnrolledModules().size());
    }

    @Test
    public void testItSetsTheCompletedModules() {
        assertEquals(2, this.student.getCompletedModules().size());
        this.student.setCompletedModules(new ArrayList<>());
        assertEquals(0, this.student.getCompletedModules().size());
    }

    @Test
    public void testItSetsTheGrades() {
        assertEquals(2, this.student.getGrades().size());
        this.student.setGrades(new ArrayList<>());
        assertEquals(0, this.student.getGrades().size());
    }

    @Test
    public void testItGetsTheEnrolledModulesWithDetails() {
        CourseLoader mockCourseLoader = mock(CourseLoader.class);

        ArrayList<Module> mockedModules = new ArrayList<>();
        Module module1 = new Module(this.enrolledModule1, "Module1", true, 4, false, UUID.randomUUID());
        Module module2 = new Module(this.enrolledModule2, "Module2", true, 4, false, UUID.randomUUID());
        mockedModules.add(module1);
        mockedModules.add(module2);
        Course mockedCourse = new Course(UUID.randomUUID(), "Mocked Course", true, mockedModules);

        when(mockCourseLoader.find(this.enrolledCourseId)).thenReturn(mockedCourse);

        ArrayList<Module> result = this.student.getEnrolledModulesWithDetails(mockCourseLoader);

        assertEquals(2, result.size());
    }

    @Test
    public void testItAddsAnEnrolledModule() {
        assertEquals(2, this.student.getEnrolledModules().size());
        this.student.addEnrolledModule(UUID.randomUUID());
        assertEquals(3, this.student.getEnrolledModules().size());
    }

    @Test
    public void testItAddsACompletedModule() {
        assertEquals(2, this.student.getCompletedModules().size());
        this.student.addCompletedModule(UUID.randomUUID());
        assertEquals(3, this.student.getCompletedModules().size());
    }

    @Test
    public void testItRemovesTheEnrolledModule() {
        assertEquals(2, this.student.getEnrolledModules().size());
        this.student.removeEnrolledModule(this.enrolledModule2);
        assertEquals(1, this.student.getEnrolledModules().size());
    }

    @Test
    public void testItAddsAGrade() {
        assertEquals(2, this.student.getGrades().size());
        Grade newGrade = new Grade(UUID.randomUUID(), UUID.randomUUID(), 80);
        this.student.addGrade(newGrade);
        assertEquals(3, this.student.getGrades().size());
    }

    @Test
    public void testItGetsTheCompletedModulesWithDetails() {
        CourseLoader mockCourseLoader = mock(CourseLoader.class);

        ArrayList<Module> mockedModules = new ArrayList<>();
        Module module1 = new Module(this.completedModule1, "Module1", true, 4, false, UUID.randomUUID());
        Module module2 = new Module(this.completedModule2, "Module2", true, 4, false, UUID.randomUUID());
        mockedModules.add(module1);
        mockedModules.add(module2);
        Course mockedCourse = new Course(UUID.randomUUID(), "Mocked Course", true, mockedModules);

        when(mockCourseLoader.find(this.enrolledCourseId)).thenReturn(mockedCourse);

        ArrayList<Module> result = this.student.getCompletedModulesWithDetails(mockCourseLoader);

        assertEquals(2, result.size());
        assertEquals("Module1", result.get(0).getName());
        assertEquals("Module2", result.get(1).getName());
    }

    @Test
    public void testItGetsTheCompetedModulesWithGrade() {
        CourseLoader mockCourseLoader = mock(CourseLoader.class);

        ArrayList<Module> mockedModules = new ArrayList<>();
        Module module1 = new Module(this.completedModule1, "Module1", true, 4, false, UUID.randomUUID());
        Module module2 = new Module(this.completedModule2, "Module2", true, 4, false, UUID.randomUUID());
        mockedModules.add(module1);
        mockedModules.add(module2);
        Course mockedCourse = new Course(UUID.randomUUID(), "Mocked Course", true, mockedModules);

        when(mockCourseLoader.find(this.enrolledCourseId)).thenReturn(mockedCourse);

        ArrayList<CompletedModuleWithGrade> result = this.student.getCompletedModulesWithGrade(mockCourseLoader);

        assertEquals(2, result.size());
        assertEquals("Module1", result.get(0).getModule().getName());
        assertEquals("Module2", result.get(1).getModule().getName());
    }

    @Test
    public void testItGetsTheCompletedModulesWithGradesForASpecificLevel() {
        CourseLoader mockCourseLoader = mock(CourseLoader.class);

        ArrayList<Module> mockedModules = new ArrayList<>();
        Module module1 = new Module(this.completedModule1, "Module1", true, 4, false, UUID.randomUUID());
        Module module2 = new Module(this.completedModule2, "Module2", true, 5, false, UUID.randomUUID());
        mockedModules.add(module1);
        mockedModules.add(module2);
        Course mockedCourse = new Course(UUID.randomUUID(), "Mocked Course", true, mockedModules);

        when(mockCourseLoader.find(this.enrolledCourseId)).thenReturn(mockedCourse);

        ArrayList<CompletedModuleWithGrade> result = this.student.getCompletedModulesWithGrade(mockCourseLoader, 4);

        assertEquals(1, result.size());
        assertEquals("Module1", result.get(0).getModule().getName());
    }
}
