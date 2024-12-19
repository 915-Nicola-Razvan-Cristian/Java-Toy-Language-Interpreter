package model.adt;


import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<T> implements IMyStack<T>{
    Stack<T> stack;

    public MyStack()
    {
        this.stack = new Stack<>();
    }

    public T pop () throws EmptyStackException{
        if(stack.isEmpty())
        throw new EmptyStackException();
        return stack.pop();
    }


    public T peek() throws EmptyStackException
    {
        if(stack.isEmpty())
        throw new EmptyStackException();
        return stack.peek();
    }

    public void push(T elem)
    {
        stack.push(elem);
    }

    public int getSize()
    {
        return stack.size();
    }

    public boolean isEmpty()
    {
        return stack.size() == 0;
    }


    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        
        for (int i = stack.size() - 1; i >= 0; i--)
        {
            str.append(stack.get(i));
        }
    
        return "ExeStack contains: \n" + str.toString();
    }
}
