package repository;

import exceptions.*;
import java.util.List;

import exceptions.RepoException;
import model.state.PrgState;

public interface IRepository {

    void add(PrgState p);
    PrgState getCrtPrg();
    void logPrgStateExec(PrgState state) throws RepoException, EmptyStackException;
    List<PrgState> getList();
    void setPrgList(List<PrgState> list);
}
