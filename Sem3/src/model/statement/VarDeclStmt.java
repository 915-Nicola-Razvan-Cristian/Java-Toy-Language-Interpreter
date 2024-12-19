package model.statement;

import exceptions.StatementException;
import model.adt.IMyMap;
import model.state.PrgState;
import model.types.IType;



public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String n, IType t)
    {
        name = n;
        type = t;
    }

    @Override
    public IStmt deepCopy()
    {
        return new VarDeclStmt(name, type);
    }   
    public PrgState execute(PrgState state) throws StatementException
    {
        if(state.getSymTbl().contains(name))
        {
            throw new StatementException("Variable is already declared");
        }
        
        state.getSymTbl().insert(name, type.defaultValue());

        //return state;
        return null;
    }

    public String toString()
    {
        return type.toString() + " " + name + ";\n";
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        typeEnv.insert(name, type);
        return typeEnv;
    }
}
