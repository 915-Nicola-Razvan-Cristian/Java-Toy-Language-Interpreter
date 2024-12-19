package model.statement;

import exceptions.StatementException;
import model.adt.IMyMap;
import model.adt.IMyStack;
import model.state.PrgState;
import model.types.IType;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;
    
    @Override
    public String toString() {

        return first.toString() + snd.toString();
    }

    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        snd = s;
    }

    public PrgState execute(PrgState state) throws StatementException{
        IMyStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        //return state;
        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new CompStmt(first, snd);
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        return snd.typecheck(first.typecheck(typeEnv));
    }
}