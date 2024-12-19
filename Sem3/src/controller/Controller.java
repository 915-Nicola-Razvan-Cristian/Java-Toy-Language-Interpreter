package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import exceptions.*;
import model.adt.IMyMap;
import model.adt.IMyStack;
import model.adt.MyMap;
import model.state.PrgState;
import model.statement.CompStmt;
import model.statement.IStmt;
import model.statement.NopStmt;
import model.types.IType;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;
import repository.IRepository;

public class Controller {
    private IRepository repo;
    private boolean displayFlag = false;
    ExecutorService executor;


    public Controller(IRepository r)//, ExecutorService exe)
    {
        repo = r;
    }

    public Controller(IRepository r, ExecutorService e)
    {
        repo = r;
        executor = e;
    }

    public Controller(IRepository r,boolean df)
    {
        
        repo = r;
        displayFlag = df;
    }
    
    public void add(PrgState p)
    {
        repo.add(p);
    }


    Stream<Integer> getRefsRec(Integer addr, Map<Integer, IValue> heap)
    {
        try{
        var v = heap.get(addr);
        if(v.getType() instanceof RefType)
        {
            return Stream.concat(getRefsRec(((RefValue)v).getValue(), heap), Stream.of(addr));
        }
        }
        catch(Exception e)
        {

        }
        return Stream.of(addr);
        
    }

    public void runTypeChecker() throws Exception
    {
        for(PrgState state : repo.getList())
        {
            IMyMap<String, IType> typeEnv = new MyMap<>();

            if(!state.getStk().isEmpty())
            {
                state.getStk().peek().typecheck(typeEnv);
            }
        }
    }

    public Map<Integer, IValue> garbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap)
    {
        return heap.entrySet().stream().filter(entry->symTableAddr.contains(entry.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public List<Integer> getAddrFromSymTbl(Collection<IValue> symTableValues, Map<Integer, IValue> heap)
    {
        return symTableValues.stream()
        .filter(v->v instanceof RefValue)
        .map(v->{RefValue v1 = (RefValue)v; return getRefsRec(v1.getValue(), heap);}).flatMap(e->e).collect(Collectors.toList());
    }


    public PrgState oneStep(PrgState state) throws ExpressionException,EmptyStackException,StatementException,KeyNotFoundException,RepoException
    {
        IMyStack<IStmt> stk = state.getStk();
        if(stk.isEmpty())
            throw new ExpressionException("PrgState Stack is empty");
        IStmt crtStmt = stk.pop();
        PrgState res = crtStmt.execute(state);
        if(! (crtStmt.getClass() == new CompStmt(new NopStmt(), new NopStmt()).getClass()))
        {
            repo.logPrgStateExec(state);
        }
        return res;
    }
    
    public void allStep() throws ExpressionException,EmptyStackException,StatementException,KeyNotFoundException, RepoException, Exception
    {
        runTypeChecker();
        PrgState prg = repo.getCrtPrg();
        if(displayFlag)
        {
            System.out.println(prg);
        }
        repo.logPrgStateExec(prg);
        while (!prg.getStk().isEmpty()) {

            oneStep(prg);
            prg.getHeap().setContent(garbageCollector(getAddrFromSymTbl(prg.getSymTbl().getContent().values(),prg.getHeap().getContent()), prg.getHeap().getContent()));
            if(displayFlag)
                System.out.println(prg);
            
        }
    }




    public void allStep2() throws Exception
    {
        runTypeChecker();
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getList());
        prgList.stream().forEach(p -> {
            try {
                repo.logPrgStateExec(p);
            } catch (Exception e) {
            }
        });
        prgList = removeCompletedPrg(prgList);
        //List<List<Integer>> symTableList = new ArrayList<>();
        while (prgList.size()>0) {
            List<Integer> symTableAddresses = prgList.stream()
                .flatMap(prg -> getAddrFromSymTbl(prg.getSymTbl().getContent().values(), prg.getHeap().getContent()).stream())
                .distinct().collect(Collectors.toList());
            prgList.get(0).getHeap().setContent(garbageCollector(symTableAddresses, prgList.get(0).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(prgList);
        }
        executor.shutdownNow();

        repo.setPrgList(prgList);
    }


    public void oneStepForAllPrg(List<PrgState> prgList)
    {
        
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
            .collect(Collectors.toList());

        List<PrgState> newPrgList = new ArrayList<>();
        try{
        newPrgList = executor.invokeAll(callList).stream()
            .map(future->{try{ return future.get();}catch(Exception e){ e.printStackTrace();return null;}})
                .filter(p->p!=null).collect(Collectors.toList());
    
        }catch(Exception e)
        {
            e.printStackTrace();
        }


        prgList.addAll(newPrgList);


        prgList.forEach(prg->{
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        repo.setPrgList(prgList);
    }


    public List<PrgState> removeCompletedPrg(List<PrgState> lst)
    {
        List<PrgState> newList = lst.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
        return newList;
    }
}
