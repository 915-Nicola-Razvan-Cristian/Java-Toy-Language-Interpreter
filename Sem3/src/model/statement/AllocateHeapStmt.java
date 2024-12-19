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

public class AllocateHeapStmt implements IStmt{
    
    private String varName;
    private IExpr expr;

    public AllocateHeapStmt(String name, IExpr e)
    {
        varName = name;
        expr = e;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        
        if(!state.getSymTbl().contains(varName))
        {
            throw new StatementException("Undefined variable name " + varName);
        }

        IValue exprValue = expr.evaluate(state.getSymTbl(), state.getHeap());

        IValue value = state.getSymTbl().get(varName);

        if(!value.getType().equals(new RefType(exprValue.getType())))
        {
            throw new StatementException("Non ref type");
        }


        if(!exprValue.getType().equals(((RefValue)value).getInnerType()))
        {
            throw new StatementException("Invalid type");
        }

        ((RefValue)value).setAddr(state.getHeap().allocate(exprValue.deepCopy()));
        //return state;
        return null;
        
    }

    @Override
    public String toString() {
        return String.format("allocate(%s, %s);\n", varName, expr);
    }

    @Override
    public IStmt deepCopy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deepCopy'");
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typevar = typeEnv.get(varName);
        IType typeExpr = expr.typecheck(typeEnv);
        if(typevar.equals(new RefType(typeExpr)))
        {
            return typeEnv;
        }
        else
            throw new Exception("NEW stmt: right hand side and left hand side have different types");
    }


    
}
