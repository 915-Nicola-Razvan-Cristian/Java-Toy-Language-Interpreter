package model.statement;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.expressions.IExpr;
import model.state.*;
import model.types.BoolType;
import model.types.IType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStmt implements IStmt{

    private IExpr exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(IExpr ex, IStmt then, IStmt el)
    {
        exp = ex;
        thenS = then;
        elseS = el;

    }

    @Override
    public String toString()
    {
        return "IF(" + exp.toString() + ")\n THEN {\n\t" + thenS.toString() + " }\n ELSE {\n\t" + elseS.toString() +" }\n";
    }

    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException
    {
        
            IValue expValue;
            
            expValue = exp.evaluate(state.getSymTbl(),state.getHeap());
           
            if(!(expValue.getType() instanceof BoolType))
            {
                    throw new StatementException("Invalid expression");
            }
            if(((BoolValue)expValue).getValue())
            {
            state.getStk().push(thenS);
            }
            else
            {
            state.getStk().push(elseS);
            }
           
            //return state;
            return null;
        }

    @Override
    public IStmt deepCopy()
    {
        return new IfStmt(exp, thenS, elseS);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typeExpr = exp.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType()))
        {
            thenS.typecheck(typeEnv.deepCopy());
            elseS.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
        throw new Exception("The condition of IF does not have type bool");

    }

}