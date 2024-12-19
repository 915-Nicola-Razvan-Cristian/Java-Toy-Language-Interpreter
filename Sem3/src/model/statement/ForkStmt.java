package model.statement;


import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import exceptions.StatementException;
import model.adt.IMyMap;
import model.adt.IMyStack;
import model.adt.MyMap;
import model.adt.MyStack;
import model.state.PrgState;
import model.types.IType;
import model.values.IValue;

public class ForkStmt implements IStmt{

    private IStmt stmt;

    public ForkStmt(IStmt s)
    {
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException {
        IMyStack<IStmt> exe1 = new MyStack<>();
        IMyMap<String, IValue> sym1 = new MyMap<>();
        sym1 = state.getSymTbl().deepCopy();
        PrgState newState = new PrgState(exe1, sym1, state.getOut(), stmt, state.getFileTable(), state.getHeap());
        return newState;
    }

    @Override
    public String toString()
    {
        return "Fork(" + stmt.toString() + ");\n";
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
    
}
