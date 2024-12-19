package model.expressions;

import model.adt.*;
import model.types.IType;
import model.values.IValue;

public class ValueExpr implements IExpr{
    private IValue val;

    public ValueExpr(IValue value)
    {
        val = value;
    }

   // @Override
    public IValue evaluate(IMyMap<String, IValue> symTbl ,IHeap heap)
    {
        return val;
    }

    @Override
    public String toString()
    {
        return val.toString();
    }

    @Override
    public IExpr deepCopy()
    {
        return new ValueExpr(val);
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        return val.getType();
    }
}
