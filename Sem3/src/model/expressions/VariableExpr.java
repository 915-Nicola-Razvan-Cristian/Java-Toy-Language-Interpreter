package model.expressions;

import exceptions.KeyNotFoundException;
import model.adt.IHeap;
import model.adt.IMyMap;
import model.types.IType;
import model.values.IValue;;

public class VariableExpr implements IExpr{
    private String variable;

    public VariableExpr(String var)
    {
        variable = var;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symTbl, IHeap heap) throws KeyNotFoundException
    {
        return symTbl.get(variable);
    }

    @Override
    public String toString()
    {
        return variable.toString();
    }

    @Override
    public IExpr deepCopy()
    {
        return new VariableExpr(variable);
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        return typeEnv.get(variable);
    }
}
