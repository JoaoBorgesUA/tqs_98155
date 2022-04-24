package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void ApiWorkingTest() {
        get("/todos").then().assertThat().statusCode(200);
    }

    @Test
    public void a() {
        get("/todos?id=20").then().assertThat().body("title", hasItems("ullam nobis libero sapiente ad optio sint"));
    }
}
