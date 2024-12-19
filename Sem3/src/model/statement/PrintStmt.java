package model.statement;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.IType;

public class PrintStmt implements IStmt{
    private final IExpr expr;

    public PrintStmt(IExpr ex)
    {
        expr = ex;
    }

    public PrgState execute(PrgState state) throws KeyNotFoundException, ExpressionException
    {
        var res = expr.evaluate(state.getSymTbl(),state.getHeap());
        state.getOut().add(res);
        //return state;
        return null;
    }

    @Override
    public String toString()
    {
        return "print("+expr.toString()+");\n";
    }

    @Override
    public IStmt deepCopy()
    {
        return new PrintStmt(expr);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        expr.typecheck(typeEnv);
        return typeEnv;
    }
}
