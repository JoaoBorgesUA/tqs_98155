package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    private App<Integer> stack;

    @BeforeEach
    void setUp() {
        stack= new App<>();
    }

    @DisplayName("Stack empty at the beggining")
    @Test
    void EmptyAtStart(){
        assertTrue(stack.isEmpty());
    }
}
