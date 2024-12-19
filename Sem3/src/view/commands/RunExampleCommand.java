package view.commands;

import controller.Controller;
import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.RepoException;
import exceptions.StatementException;

public class RunExampleCommand extends Command{
    private Controller controller;
    public RunExampleCommand(String key, String desc, Controller c)
    {
        super(key, desc);
        controller = c;
    }
    
    public void execute() throws ExpressionException, EmptyStackException, StatementException, KeyNotFoundException, RepoException, Exception
    {
        controller.allStep2();
    }

    



}
