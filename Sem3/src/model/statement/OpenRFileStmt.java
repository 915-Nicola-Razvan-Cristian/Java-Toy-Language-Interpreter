package model.statement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.IType;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

public class OpenRFileStmt implements IStmt{

    private IExpr expr;

    public OpenRFileStmt(IExpr expr)
    {
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue exprValue = expr.evaluate(state.getSymTbl(),state.getHeap());
        if(!exprValue.getType().equals(new StringType()))
        {
            throw new StatementException("Error open R File");
        }
        if(state.getFileTable().contains(((StringValue)exprValue).getValue()))
        {
            throw new StatementException("Variable already declared");
        }
        try{
            BufferedReader r = new BufferedReader(new FileReader(((StringValue)exprValue).getValue()));
            state.getFileTable().insert(((StringValue)exprValue).getValue(), r);
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        //return state;
        return null;
    }

    @Override
    public String toString()
    {
        return "OpenRFile(" + expr.toString() + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deepCopy'");
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typeExpr = expr.typecheck(typeEnv);
        if(typeExpr.equals(new StringType()))
        {
            return typeEnv;
        }
        else 
            throw new Exception("OpenRFileStmt: variable not a string");
    }
    
}
