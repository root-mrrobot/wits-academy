package com.example.myapplication.Models;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Pdf_data;
import com.example.myapplication.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PdfDataTest {
    // Creating a hypothetical user for tests
    Pdf_data pdfData = new Pdf_data("testName", "testUrl", "testSubject", "testTopic");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetters() {
        String expected = "testName";
        assertEquals(expected, pdfData.getName_pdf());

        String expected1 = "testUrl";
        assertEquals(expected1, pdfData.getUrl_pdf());
    }

    @Test
    public void setPdfName() {
        pdfData.setName_pdf("bored");
        String expected = "bored";
        assertEquals(expected, pdfData.getName_pdf());
    }

    @Test
    public void setPdfUrl() {
        pdfData.setUrl_pdf("url");
        String expected = "url";
        assertEquals(expected, pdfData.getUrl_pdf());
    }
}
