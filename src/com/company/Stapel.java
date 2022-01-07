package com.company;

import java.util.LinkedList;

public class Stapel<T> {
    public static void main(String[] args) {
        Stapel<String>  test = new Stapel<String>();

    }
    private LinkedList<T> data;

    public Stapel() {
        this.data = new LinkedList<T>();
    }

    public boolean isEmpty() {
        return !(this.data.size() >= 0);
    }

    public T pop() {
        return this.data.pollLast();
    }

    public void push(T item) {
        this.data.addLast(item);
        return;
    }

    public T peek() {
        return this.data.peek();
    }

    public void reverse() {
        return;
    }
}
