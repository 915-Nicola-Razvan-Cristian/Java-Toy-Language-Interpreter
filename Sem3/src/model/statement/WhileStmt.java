package model.statement;


import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.PrgState;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStmt implements IStmt{


    private IExpr expr;
    private IStmt innerStmt;

    public WhileStmt(IExpr e, IStmt s)
    {
        expr = e;
        innerStmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        
        IValue exprValue = expr.evaluate(state.getSymTbl(), state.getHeap());
        if(!exprValue.getType().equals(new BoolType()))
        {
            throw new StatementException("Invalid expression in 'while'");
        }

        if(((BoolValue)exprValue).getValue())
        {
            state.getStk().push(this);
            state.getStk().push(innerStmt);
        }
        //return state;
        return null;
    }


    @Override
    public String toString()
    {
        return "While(" + expr.toString() + ")" + " DO " + innerStmt.toString() + "\n";
    }


    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expr.deepCopy(), innerStmt);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        innerStmt.typecheck(typeEnv);
        IType typeExpr = expr.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType()))
        {
            return typeEnv;
        }
        else    
            throw new Exception("WhileStmt: Expression not BoolType");
    }
    
}
