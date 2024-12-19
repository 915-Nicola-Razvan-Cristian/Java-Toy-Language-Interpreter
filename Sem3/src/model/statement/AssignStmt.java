package model.statement;


import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.IType;
import model.values.IValue;

public class AssignStmt implements IStmt{
    private String variable;
    private IExpr expr;

    public AssignStmt(String var,IExpr ex)
    {
        variable = var;
        expr = ex;
    }


    @Override
    public IStmt deepCopy()
    {
        return new AssignStmt(variable, expr);
    }

    @Override
    public String toString()
    {
        return variable.toString() + " = " + expr.toString() + ";\n";
    }

    public PrgState execute(PrgState state) throws StatementException,KeyNotFoundException, ExpressionException
    {
        if(!(state.getSymTbl().contains(variable)))
        {
            throw new StatementException("Variable not defined" + variable.toString());
        }
        IValue evalValue = expr.evaluate(state.getSymTbl(),state.getHeap());
        IType type = state.getSymTbl().get(variable).getType();

        if(evalValue.getType().equals(type))
        {
            state.getSymTbl().insert(variable, evalValue);
        }
        else
        {
            throw new StatementException("Type does not match");
        }
        //return state;
        return null;

    }


    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typeVar = typeEnv.get(variable);
        IType typeExpr = expr.typecheck(typeEnv);
        if(typeVar.equals(typeExpr))
            return typeEnv;
        else
        {
            throw new Exception("Assignment: right hand side and left hand side have different types"); 
        }
    }
}
