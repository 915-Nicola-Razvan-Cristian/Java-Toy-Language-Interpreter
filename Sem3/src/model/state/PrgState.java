package model.state;

import model.adt.*;
import model.statement.IStmt;
import model.values.*;
import java.io.*;

import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.RepoException;
import exceptions.StatementException;

public class PrgState {
    IMyStack<IStmt> exeStack;
    IMyMap<String, IValue> symTable;
    IMyList<IValue> out;
    IStmt originalProgram;
    IMyMap<String, BufferedReader> fileTable;
    IHeap heap;
    static private int nextId = 0;
    private int id;

    public PrgState(IMyStack<IStmt> stk, IMyMap<String, IValue> symtbl, IMyList<IValue> ot, IStmt prg, IMyMap<String, BufferedReader> fTable, IHeap heap)
    {
        id = this.getNextId();
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg;
        stk.push(prg);
        fileTable = fTable;
        this.heap = heap;
    }


    public synchronized int getNextId()
    {
        return ++nextId;
    }

    public IMyStack<IStmt> getStk()
    {
        return exeStack;
    }

    public boolean isNotCompleted()
    {
        return !(exeStack.isEmpty());
    }

     public PrgState oneStep() throws ExpressionException,EmptyStackException,StatementException,KeyNotFoundException,RepoException
    {
        if(exeStack.isEmpty())
            throw new ExpressionException("PrgState Stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    

    public IHeap getHeap()
    {
        return heap;
    }

    public IMyList<IValue> getOut()
    {
        return out;
    }

    public IMyMap<String, IValue> getSymTbl()
    {
        return symTable;
    }

    public IMyMap<String, BufferedReader> getFileTable()
    {
        return fileTable;
    }

    @Override
    public String toString()
    {
        
        return "Current prg: " + id + "\n\n\n" + exeStack.toString() +"\n*************\n" +"SymTable contains: \n" + symTable.toString() +"\n*************\n" + out.toString() + "\n*************\n" + "FileTable contains: \n" +fileTable.toString2() + "\n*************\n" + "Heap contains:\n" + heap.toString() + "\n-----------------\n";
    }
}
