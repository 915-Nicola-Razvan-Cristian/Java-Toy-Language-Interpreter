package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.*;
import model.types.IType;
import model.values.IValue;

public interface IExpr {
     IValue evaluate(IMyMap<String, IValue> symTbl, IHeap heap) throws KeyNotFoundException, ExpressionException;
     IExpr deepCopy();
     IType typecheck(IMyMap<String, IType> typeEnv) throws Exception;
}