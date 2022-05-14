package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {

    @Test
    public void getImage_Uri() {
        Model m = new Model("test", "");
        assertEquals("test", m.getImage_Uri());
    }

    @Test
    public void setImage_Uri() {
        Model m = new Model("test2", "");
        m.setImage_Uri("test");
        assertEquals("test", m.getImage_Uri());
    }

    @Test
    public void getName() {
        Model m  = new Model("", "test");
        assertEquals("test", m.getName());
    }

    @Test
    public void setName() {
        Model m = new Model("", "test2");
        m.setName("test");
        assertEquals("test", m.getName());
    }
}