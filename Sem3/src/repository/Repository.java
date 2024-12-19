package repository;

import model.state.PrgState;
import model.statement.CompStmt;

import java.util.List;

import exceptions.RepoException;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Repository implements IRepository{
    private List<PrgState> states;
    private String filename;
    private int currentState;

    public Repository(String file)
    {
        states = new ArrayList<PrgState>();
        currentState = 0;
        filename = file;
    }

    public void add(PrgState p)
    {
        states.add(p);
    }

    public List<PrgState> getList()
    {
        return states;
    }

    public void setPrgList(List<PrgState> list)
    {
        states = list;
    }

    public PrgState getCrtPrg()
    {
        return states.get(currentState);
    }

    public void logPrgStateExec(PrgState state) throws RepoException
    {
        PrgState current = state;
        try
        {
            //if(state.getStk().peek().getClass() != new CompStmt(null, null).getClass())
            //{
                try{
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
                    writer.println(current.toString());
                    writer.close();
                }
                catch(IOException io)
                {
                    throw new RepoException("Cannot write in the file");
                }
            //}
        }
        catch(Exception e){}
        
       
        
    }
    
}
