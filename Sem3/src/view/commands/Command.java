package view.commands;

import exceptions.EmptyStackException;
import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.RepoException;
import exceptions.StatementException;

public abstract class Command {
    private String key;
    private String description;

    public Command(String k, String desc)
    {
        key = k;
        description = desc;
    }

    public abstract void execute() throws ExpressionException, EmptyStackException, StatementException, KeyNotFoundException, RepoException, Exception ;

    
    public String getDescription()
    {
        return description;
    }

    public String getKey()
    {
        return key;
    }
}
