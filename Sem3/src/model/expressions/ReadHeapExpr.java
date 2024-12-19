package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IHeap;
import model.adt.IMyMap;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class ReadHeapExpr implements IExpr{
    private IExpr expr;

    public ReadHeapExpr(IExpr e)
    {
        expr = e;
    }

    public IValue evaluate(IMyMap<String, IValue> symTable, IHeap heap) throws ExpressionException, KeyNotFoundException
    {
        IValue val = expr.evaluate(symTable,heap);
        if(val.getType().equals(new RefType(null)))
        {
            throw new ExpressionException("Non RefType expression eval");
        }
        int address = ((RefValue)val).getValue();
        if(!heap.exists(address))
        {
            throw new ExpressionException("Address not in Heap: " + address);
        }
        return (IValue) heap.get(address);

        
    }

    @Override
    public String toString() {
        return String.format("readHeap(%s)", expr);
    }

    public IExpr deepCopy()
    {
        return new ReadHeapExpr(expr.deepCopy());
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typ = expr.typecheck(typeEnv);
        if(typ instanceof RefType)
        {
            RefType left = (RefType)typ;
            return left.getInner();
        }
        else
        {
            throw new ExpressionException("the rH argument is not a RefType");
        }
    }

}
