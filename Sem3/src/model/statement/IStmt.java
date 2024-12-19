package model.statement;

import model.adt.IMyMap;
import model.state.PrgState;
import model.types.IType;
import exceptions.*;

public interface IStmt {

    PrgState execute(PrgState state) throws StatementException, KeyNotFoundException, ExpressionException;
    String toString();
    IStmt deepCopy();
    IMyMap<String, IType> typecheck(IMyMap<String, IType> typeEnv) throws Exception;
}
