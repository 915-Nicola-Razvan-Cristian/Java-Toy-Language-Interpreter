package model.statement;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class WriteHeapStmt implements IStmt{

    private String varName;
    private IExpr expr;

    public WriteHeapStmt(String name, IExpr e)
    {
        varName = name;
        expr = e;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        if(!state.getSymTbl().contains(varName))
        {
            throw new StatementException("undeclared variable name " + varName);
        }
        IValue value = state.getSymTbl().get(varName);

        IValue exprValue = expr.evaluate(state.getSymTbl(), state.getHeap());


        if(!value.getType().equals(new RefType(exprValue.getType())))
        {
            throw new StatementException("Invalid type");
        }

        if(!exprValue.getType().equals(((RefValue)value).getInnerType()))
        {
            throw new StatementException("Invalid inner type");
        }

        state.getHeap().set(((RefValue)value).getValue(), exprValue);
        
        //return state;
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(varName, expr.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("writeHeap(%s, %s);\n", varName, expr);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typeVal = typeEnv.get(varName);
        IType typeExpr = expr.typecheck(typeEnv);
        if(typeVal.equals(new RefType(typeExpr)))
        {
            return typeEnv;
        }
        else
            throw new Exception("WriteHeapStmt: Variable and expression are not of the same type");
    }
    
}