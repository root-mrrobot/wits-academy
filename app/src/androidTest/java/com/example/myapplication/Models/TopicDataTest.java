package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.TopicData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TopicDataTest {
    // Creating a hypothetical topicData for tests
    TopicData topicData = new TopicData("topicTest");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTopicOne() {
        String expected = "topicTest";
        assertEquals(expected, topicData.getTopicOne());
    }

    @Test
    public void setTopicOne() {
        topicData.setTopicOne("bored");
        String expected = "bored";
        assertEquals(expected, topicData.getTopicOne());
    }
}
