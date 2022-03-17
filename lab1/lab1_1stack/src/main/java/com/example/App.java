package com.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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

    public T pop(){
        if (stack.size()==0){
            throw new NoSuchElementException();
        }
        return this.stack.remove(this.stack.size()-1);
    }

    public T peek(){
        if (stack.size()==0){
            throw new NoSuchElementException();
        }
        return this.stack.get(this.stack.size()-1);
    }

    public int size(){
        return this.stack.size();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }
}
