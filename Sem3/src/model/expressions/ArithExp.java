package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IHeap;
import model.adt.IMyMap;
import model.types.IType;
import model.types.IntType;
import model.values.IntValue;
import model.values.*;;

public class ArithExp implements IExpr {
    IExpr e1;
    IExpr e2;
    int op;

    public ArithExp(IExpr e1, IExpr e2, int o) {
        this.e1 = e1;
        this.e2 = e2;
        op = o;
    }

    public IValue evaluate(IMyMap<String,IValue> tbl, IHeap heap) throws ExpressionException, KeyNotFoundException
    {
            IValue v1,v2;
            v1= e1.evaluate(tbl, heap);
            if (v1.getType().equals(new IntType())) {
                v2 = e2.evaluate(tbl, heap);
                if (v2.getType().equals(new IntType())) {
                    IntValue i1 = (IntValue)v1;
                    IntValue i2 = (IntValue)v2;
                    int n1,n2;
                    n1= i1.getValue();
                    n2 = i2.getValue();
                    if (op==1) 
                        return new IntValue(n1+n2);
                    if (op ==2) 
                        return new IntValue(n1-n2);
                    if(op==3) 
                        return new IntValue(n1*n2);
                    if(op==4)
                        if(n2==0) 
                            throw new ExpressionException("division by zero");
                    else 
                        return new IntValue(n1/n2);
                }
                else
                    throw new ExpressionException("second operand is not an integer");
            }
            else
                throw new ExpressionException("first operand is not an integer");
            return new IntValue(0);
                
    }

    @Override
    public IExpr deepCopy()
    {
        return new ArithExp(e1, e2, op);
    }

    @Override
    public String toString()
    {
        char[] o = {'+','-','*','/'};
        return e1.toString() + " " + o[op-1] + " " + e2.toString();  
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType t1, t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if(t1.equals(new IntType()))
        {
            if(t2.equals(new IntType()))
                return new IntType();
            else
                throw new ExpressionException("Second operand is not an integer");
        }
        else
            throw new ExpressionException("first operand is not an integer");
    }
}