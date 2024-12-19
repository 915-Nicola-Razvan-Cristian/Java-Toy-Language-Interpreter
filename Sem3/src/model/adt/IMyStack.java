package model.adt;

import exceptions.*;

public interface IMyStack<T> {
    T pop() throws EmptyStackException;
    T peek() throws EmptyStackException;
    void push(T element);
    int getSize();
    boolean isEmpty();
}
