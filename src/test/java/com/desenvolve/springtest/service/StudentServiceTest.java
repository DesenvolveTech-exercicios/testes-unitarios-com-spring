package com.desenvolve.springtest.service;

import com.desenvolve.springtest.model.Course;
import com.desenvolve.springtest.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @Autowired
    private StudentService service;

    @BeforeEach
    void setup(){
        service = Mockito.mock(StudentService.class);
    }

    private final static List<Student> students = new ArrayList<>();
    private final static List<Course> courses = new ArrayList<>();

    static {
        //Initialize Data
        Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
                .asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));
        Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));


        Student ranga = new Student("Student1", "Ranga Karanam",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(course1, course2)));

        courses.add(course1);
        courses.add(course2);
        students.add(ranga);
    }

    @Test
    void shouldRetrieveAllStudents(){
        Mockito.when(service.retrieveAllStudents()).thenReturn(students);
        assertEquals(students, service.retrieveAllStudents());
    }

    @Test
    void shouldRetrieveStudent(){
        Mockito.when(service.retrieveStudent(Mockito.anyString())).thenReturn(students.get(0));
        assertEquals(students.get(0), service.retrieveStudent("Student1"));
    }

    @Test
    void shouldRetrieveCourses(){
        Mockito.when(service.retrieveCourses(Mockito.anyString()))
                .thenReturn(students.get(0).getCourses());
        assertEquals(courses, service.retrieveCourses("Student1"));
    }

    @Test
    void shouldRetrieveCourse(){
        Mockito.when(service.retrieveCourse(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(students.get(0).getCourses().get(0));
        assertEquals(courses.get(0), service.retrieveCourse("Student1","Course1"));
    }

    @Test
    void shouldAddCourse(){
        Course course3 = new Course("Course3", "kafka", "5 Examples",
                Arrays.asList("producers", "consumers", "topics",
                        "partições"));
        Mockito.when(service.addCourse(Mockito.anyString(), Mockito.any(Course.class))).thenReturn(course3);
        assertEquals(course3, service.addCourse("Student1", course3));
    }
}
