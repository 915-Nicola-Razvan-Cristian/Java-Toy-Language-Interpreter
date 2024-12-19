package model.statement;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.IType;
import model.types.IntType;
import model.types.StringType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;
import java.io.*;

public class ReadFileStmt implements IStmt{
    private IExpr exp;
    private String varName;

    public ReadFileStmt(IExpr e, String v)
    {
        exp = e;
        varName = v;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        var t = state.getSymTbl();
        if(!t.contains(varName))
        {
            throw new StatementException("Variable name not defined");
        }
        if(!t.get(varName).getType().equals(new IntType()))
        {
            throw new StatementException("Variable is not of type INT");
        }
        IValue res = exp.evaluate(state.getSymTbl(),state.getHeap());
        if(!res.getType().equals(new StringType()))
        {
            throw new StatementException("Value read is not a string");
        }

        BufferedReader r = state.getFileTable().get(((StringValue)res).getValue());

        try{
            String readResult = r.readLine();
            if(readResult == "")
            {
                readResult = "0";
            }
            int parsedResult;
            try{
            parsedResult = Integer.parseInt(readResult);
            }
            catch(NumberFormatException e)
            {
                throw new StatementException("Error reading int from file");
            }
            state.getSymTbl().insert(varName, new IntValue(parsedResult));
            //return state;
            return null;
        }
        catch(IOException e)
        {
            throw new StatementException("IO excepion trying to read file " + ((StringValue)res).getValue());
        }

    }

    @Override
    public String toString()
    {
        return "ReadFile(" + exp.toString() + ", " + varName + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(exp, varName);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typeExpr = exp.typecheck(typeEnv);
        IType typeVal = typeEnv.get(varName);
        if(!typeExpr.equals(new StringType()))
            throw new Exception("ReadFileStmt: file name not a string");
        if(!typeVal.equals(new IntType()))
        {
            throw new Exception("ReadFileStmt: variable not of type int");
        }
        return typeEnv;
    }
    
}
