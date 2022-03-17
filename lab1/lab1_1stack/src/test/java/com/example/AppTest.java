package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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

    @DisplayName("Stack size 0 at the beggining")
    @Test
    void SizeZeroAtStart(){
        assertTrue(stack.size()==0);
    }

    @DisplayName("Stack is being correctly filled up")      //what??
    @Test
    void IsPushed(){
        assertTrue(stack.size()==0);
    }

    @DisplayName("Stack can correctly pop")
    @Test
    void PopAnElement(){
        int i = 123;
        stack.push(i);
        assertEquals(i, stack.pop());
    }

    @DisplayName("Stack can correctly peek")
    @Test
    void PeekAnElement(){
        int i = 123;
        stack.push(i);
        stack.push(124);
        assertEquals(124, stack.peek());
        assertEquals(124, stack.peek());
    }

    @DisplayName("Stack empty after n pops")
    @Test
    void EmptyTheStack(){
        for (int i = 1; i < 4; i++) {
            stack.push(i);
            System.out.print(i);
        }
        for (int i = 0; i<3; i++){
            stack.pop();
        }

        assertTrue(stack.isEmpty());
    }

    @DisplayName("When stack is empty it shows the exception")
    @Test
    void StackPopThrowsException(){
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        });
    }

    @DisplayName("When stack is empty it shows the exception")
    @Test
    void StackPeekThrowsException(){
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        });
    }
}
