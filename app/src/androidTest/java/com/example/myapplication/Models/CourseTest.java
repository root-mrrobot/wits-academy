package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {
    // Creating a hypothetical user for tests
    Course course = new Course("testID", "testName", "testDesc", "testCategory", "testTeacher", "testImage", 2.5);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testID";
        assertEquals(expected, course.getId());

        String expected1 = "testName";
        assertEquals(expected1, course.getName());

        String expected2 = "testDesc";
        assertEquals(expected2, course.getDescription());

        String expected3 = "testCategory";
        assertEquals(expected3, course.getCategory());

        String expected4 = "testTeacher";
        assertEquals(expected4, course.getTeacher());

        String expected5 = "testImage";
        assertEquals(expected5, course.getImage());

//        Double expected6 = 2.5;
//        assertEquals(java.util.Optional.of(expected6), course.getRating());
    }

    @Test
    public void setCourseID() {
        course.setId("ID");
        String expected = "ID";
        assertEquals(expected, course.getId());
    }

    @Test
    public void setCourseName() {
        course.setName("Name");
        String expected = "Name";
        assertEquals(expected, course.getName());
    }

    @Test
    public void setCourseDesc() {
        course.setDescription("Desc");
        String expected = "Desc";
        assertEquals(expected, course.getDescription());
    }

    @Test
    public void setCourseCategory() {
        course.setCategory("Category");
        String expected = "Category";
        assertEquals(expected, course.getCategory());
    }

    @Test
    public void setCourseTeacher() {
        course.setTeacher("Teacher");
        String expected = "Teacher";
        assertEquals(expected, course.getTeacher());
    }

    @Test
    public void setCourseImage() {
        course.setImage("Image");
        String expected = "Image";
        assertEquals(expected, course.getImage());
    }

//    @Test
//    public void setCourseRating() {
//        course.setRating(2.5);
//        Double expected = 2.5;
//        assertEquals(expected), course.getRating());
//    }
}
