package com.example;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App<T> {
    private ArrayList<T> stack;

    public App(){
        this.stack = new ArrayList<>();
    }

    public void push(T val){
        this.stack.add(val);
    }
}
