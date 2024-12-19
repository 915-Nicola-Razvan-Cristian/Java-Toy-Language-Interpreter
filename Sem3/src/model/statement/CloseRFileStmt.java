package model.statement;

import java.io.BufferedReader;
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

public class CloseRFileStmt implements IStmt{


    private IExpr expr;

    public CloseRFileStmt(IExpr expr){
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        IValue exprValue = expr.evaluate(state.getSymTbl(),state.getHeap());
        if(!exprValue.getType().equals(new StringType()))
        {
            throw new StatementException("Error open R File");
        }
        if(!(state.getFileTable().contains(((StringValue)exprValue).getValue())))
        {
            throw new StatementException("Variable not defined");
        }
        try{
            BufferedReader r = state.getFileTable().get(((StringValue)exprValue).getValue());
            r.close();
            state.getFileTable().remove(((StringValue)exprValue).getValue());
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
        return "CloseRFile(" + expr.toString() + ");\n";
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
            throw new Exception("CloseRFileStmt: variable not a string");
    }
    
}
