package model.statement;

import model.adt.IMyMap;
import model.state.PrgState;
import model.types.IType;

public class NopStmt implements IStmt{
   
    @Override
    public String toString()
    {
        return "Nop";
    }

    public PrgState execute(PrgState state)
    {
        //return state;
         return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new NopStmt();
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        return typeEnv;
    }
}
