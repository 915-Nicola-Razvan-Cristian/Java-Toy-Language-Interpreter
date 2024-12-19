package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IHeap;
import model.adt.IMyMap;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicExp implements IExpr{
    private IExpr exp1;
    private IExpr exp2;
    private LogicOperation op;

    public LogicExp(IExpr e1, IExpr e2, LogicOperation o)
    {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }


    @Override 
    public String toString()
    {
        return exp1.toString() + " " + op.toString().toLowerCase() + " " + exp2.toString(); 
    }
    @Override
    public IValue evaluate(IMyMap<String, IValue> symTbl, IHeap heap) throws ExpressionException,KeyNotFoundException
    {
        var leftExpr = exp1.evaluate(symTbl,heap);
        var rightExpr = exp2.evaluate(symTbl,heap);
        if(!(leftExpr.getType().equals(new BoolType()) && rightExpr.getType().equals(new BoolType())))
        {
            throw new ExpressionException("Left and right expressions are not the same");
        }

        if(op == LogicOperation.AND)
        {
           BoolValue newV = new BoolValue(((BoolValue)leftExpr).getValue() && ((BoolValue)rightExpr).getValue());
           return newV;
        }
        else
        {
            BoolValue newV = new BoolValue(((BoolValue)leftExpr).getValue() && ((BoolValue)rightExpr).getValue());
            return newV;
        }   
    }

    @Override
    public IExpr deepCopy()
    {
        return new LogicExp(exp2, exp1, op);
    }


    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType t1, t2;
        t1 = exp1.typecheck(typeEnv);
        t2 = exp2.typecheck(typeEnv);
        if(t1.equals(new BoolType()))
        {
            if(t2.equals(new BoolType()))
                return new BoolType();
            else
                throw new ExpressionException("Second operand is not a bool");
        }
        else
            throw new ExpressionException("first operand is not a bool");
    }
    
}
