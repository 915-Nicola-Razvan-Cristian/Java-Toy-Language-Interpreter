
import java.io.BufferedReader;

import controller.Controller;
import model.adt.*;
import model.expressions.*;
import model.state.PrgState;
import model.statement.*;
import model.types.*;
import model.values.*;
import repository.IRepository;
import repository.Repository;
import view.View;
import view.commands.*;


public class App {
    public static void main(String[] args)
    {
        IMyStack<IStmt> exeStack1 = new MyStack<IStmt>();
        IMyMap<String, IValue> symTable1 = new MyMap<String, IValue>();
        IMyList<IValue> out1 = new MyList<IValue>();         
        IMyMap<String, BufferedReader> fileTable1 = new MyMap<String, BufferedReader>();
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
        new CompStmt(new AssignStmt("v",new ValueExpr(new IntValue(2))), new PrintStmt(new VariableExpr("v"))));
        PrgState p1 = new PrgState(exeStack1, symTable1, out1, ex1, fileTable1, new Heap());
        IRepository r1 = new Repository("log1.txt");
        Controller c1 = new Controller(r1);
        c1.add(p1);


        IMyStack<IStmt> exeStack2 = new MyStack<IStmt>();
        IMyMap<String, IValue> symTable2 = new MyMap<String, IValue>();
        IMyList<IValue> out2 = new MyList<IValue>();         
        IMyMap<String, BufferedReader> fileTable2 = new MyMap<String, BufferedReader>();
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp(new ValueExpr(new IntValue(2)),new
                ArithExp(new ValueExpr(new IntValue(3)), new ValueExpr(new IntValue(5)),3),1)),
                new CompStmt(new AssignStmt("b",new ArithExp(new VariableExpr("a"), new ValueExpr(new
                IntValue(1)),1)), new PrintStmt(new VariableExpr("b"))))));
        PrgState p2 = new PrgState(exeStack2, symTable2, out2, ex2, fileTable2,new Heap());
        IRepository r2 = new Repository("log2.txt");
        Controller c2 = new Controller(r2);
        c2.add(p2);


        IMyStack<IStmt> exeStack3 = new MyStack<IStmt>();
        IMyMap<String, IValue> symTable3 = new MyMap<String, IValue>();
        IMyList<IValue> out3 = new MyList<IValue>();         
        IMyMap<String, BufferedReader> fileTable3 = new MyMap<String, BufferedReader>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExpr(new BoolValue(true))),
                new CompStmt(new IfStmt(new VariableExpr("a"),new AssignStmt("v",new ValueExpr(new 
                IntValue(2))), new AssignStmt("v", new ValueExpr(new IntValue(3)))), new PrintStmt(new 
                VariableExpr("v"))))));
        PrgState p3 = new PrgState(exeStack3, symTable3, out3, ex3, fileTable3, new Heap());
        IRepository r3 = new Repository("log3.txt");
        Controller c3 = new Controller(r3);
        c3.add(p3);


        
        IMyStack<IStmt> exeStack4 = new MyStack<IStmt>();
        IMyMap<String, IValue> symTable4 = new MyMap<String, IValue>();
        IMyList<IValue> out4 = new MyList<IValue>();         
        IMyMap<String, BufferedReader> fileTable4 = new MyMap<String, BufferedReader>();
        //                                                          here    
        IStmt fileEx = new CompStmt(new VarDeclStmt("varf", new StringType()),new CompStmt(new AssignStmt("varf",new ValueExpr(new StringValue("test.in"))),
        new CompStmt(new OpenRFileStmt(new VariableExpr("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()), new CompStmt(new ReadFileStmt(new VariableExpr("varf"), "varc"),
        new CompStmt(new PrintStmt(new VariableExpr("varc")),
        new CompStmt(new ReadFileStmt(new VariableExpr("varf"), "varc"), new CompStmt(new PrintStmt(new VariableExpr("varc")), 
        new CloseRFileStmt(new VariableExpr("varf"))))))))));
        PrgState fileState = new PrgState(exeStack4, symTable4, out4, fileEx, fileTable4, new Heap());
        IRepository r4 = new Repository("logFile.txt");
        Controller c4 = new Controller(r4);
        c4.add(fileState);

        MyStack<IStmt> exeStack5 = new MyStack<IStmt>();
        IMyMap<String, IValue> symTable5 = new MyMap<String, IValue>();
        IMyList<IValue> out5 = new MyList<IValue>();
        IMyMap<String, BufferedReader> fileTable5 = new MyMap<String, BufferedReader>();
        IStmt relEx = new CompStmt(new VarDeclStmt("n", new BoolType()), new AssignStmt("n", new RelationalExpr(new ValueExpr(new IntValue(3)),new ValueExpr(new IntValue(4)),RelationalOperator.EQUAL)));
        PrgState relState = new PrgState(exeStack5, symTable5, out5, relEx, fileTable5, new Heap());
        IRepository r5 = new Repository("logRel.txt");
        Controller c5 = new Controller(r5);
        c5.add(relState);


        IStmt heapAlloc = new CompStmt(                 //here
                new VarDeclStmt("v",new RefType(new StringType())),
                new CompStmt(
                        new AllocateHeapStmt("v", new ValueExpr(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a",new RefType(new RefType(new IntType()))),
                                new CompStmt( 
                                        new AllocateHeapStmt("a",
                                                new VariableExpr(
                                                        "v")),
                                        new CompStmt(
                                                new PrintStmt(
                                                        new VariableExpr(
                                                                "v")),
                                                new PrintStmt(
                                                        new VariableExpr(
                                                                "a")))))));

        PrgState halloc = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), heapAlloc, new MyMap<>(), new Heap());
        IRepository r6 = new Repository("heapAlloc.txt");
        Controller c6 = new Controller(r6);
        c6.add(halloc);


        IStmt readHeap = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType()) ),
                new CompStmt(
                        new AllocateHeapStmt("v", new ValueExpr(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a",
                                        new RefType(new RefType(new IntType()))
                                        ),
                                new CompStmt(
                                        new AllocateHeapStmt("a",
                                                new VariableExpr(
                                                        "v")),
                                        new CompStmt(
                                                new PrintStmt(
                                                        new ReadHeapExpr(
                                                                new VariableExpr(
                                                                        "v"))),
                                                new PrintStmt(
                                                        new ArithExp(
                                                                new ReadHeapExpr(
                                                                        new ReadHeapExpr(
                                                                                new VariableExpr(
                                                                                        "a"))),
                                                                new ValueExpr(
                                                                        new IntValue(5)),
                                                                1)))))));
        PrgState hRead = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), readHeap, new MyMap<>(), new Heap());
        IRepository r7 = new Repository("readHeap.txt");
        Controller c7 = new Controller(r7);
        c7.add(hRead);

        IStmt writeHeap = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new AllocateHeapStmt("v", new ValueExpr(new IntValue(20))),
                        new CompStmt(
                                new PrintStmt(new ReadHeapExpr(
                                        new VariableExpr("v"))),
                                new CompStmt(
                                        new WriteHeapStmt("v",
                                                new ValueExpr(
                                                        new IntValue(30))),
                                        new PrintStmt(
                                                new ArithExp(
                                                        new ReadHeapExpr(
                                                                new VariableExpr(
                                                                        "v")),
                                                        new ValueExpr(
                                                                new IntValue(5)),
                                                        1))))));
        PrgState hWrite = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), writeHeap, new MyMap<>(), new Heap());
        IRepository r8 = new Repository("writeHeap.txt");
        Controller c8 = new Controller(r8);
        c8.add(hWrite);




        IStmt garbageColl = new CompStmt(
                new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(
                        new AllocateHeapStmt("v", new ValueExpr(new IntValue(20))),
                        new CompStmt(
                                new VarDeclStmt("a",
                                        new RefType(new RefType(new IntType()))
                                        ),
                                new CompStmt(
                                        new AllocateHeapStmt("a",
                                                new VariableExpr(
                                                        "v")),
                                        new CompStmt(
                                                new AllocateHeapStmt(
                                                        "v",
                                                        new ValueExpr(
                                                                new IntValue(30))),
                                                new CompStmt(
                                                        new AllocateHeapStmt(
                                                                "v",
                                                                new ValueExpr(
                                                                        new IntValue(40))),
                                                        new PrintStmt(
                                                                new ReadHeapExpr(
                                                                        new ReadHeapExpr(
                                                                                new VariableExpr(
                                                                                        "a"))))))))));
        PrgState garbage = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), garbageColl, new MyMap<>(), new Heap());
        IRepository r9 = new Repository("garbageColl.txt");
        Controller c9 = new Controller(r9);
        c9.add(garbage);



        IStmt whileStmt = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpr(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(
                                        new RelationalExpr(
                                                new VariableExpr("v"),
                                                new ValueExpr(new IntValue(0)),
                                                RelationalOperator.GREATER_THAN),
                                        new CompStmt(
                                                new PrintStmt(new VariableExpr("v")),
                                                new AssignStmt("v",
                                                        new ArithExp(
                                                                new VariableExpr("v"),
                                                                new ValueExpr(new IntValue(1)),
                                                                2)))),
                                new PrintStmt(new VariableExpr("v")))));
        PrgState whileState = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), whileStmt, new MyMap<>(), new Heap());
        IRepository r10 = new Repository("while.txt");
        Controller c10 = new Controller(r10);
        c10.add(whileState);


        IStmt whileStmt2 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExpr(new IntValue(4))),
                        new CompStmt(
                                new WhileStmt(new LogicExp(
                                        new RelationalExpr(
                                                new VariableExpr("v"),
                                                new ValueExpr(new IntValue(0)),
                                                RelationalOperator.GREATER_THAN),
                                                        new RelationalExpr(new VariableExpr("v"), new ValueExpr(new IntValue(6)), RelationalOperator.LESS_THAN),
                                                                LogicOperation.AND),
                                        new CompStmt(
                                                new PrintStmt(new VariableExpr("v")),
                                                new AssignStmt("v",
                                                        new ArithExp(
                                                                new VariableExpr("v"),
                                                                new ValueExpr(new IntValue(1)),
                                                                2)))),
                                new PrintStmt(new VariableExpr("v")))));
        PrgState whileState2 = new PrgState(new MyStack<>(), new MyMap<>(), new MyList<>(), whileStmt2, new MyMap<>(), new Heap());
        IRepository r11 = new Repository("while2.txt");
        Controller c11 = new Controller(r11);
        c11.add(whileState2);



        View menu = new View();
        menu.addCommand("0", new ExitCommand("0. Exit", "Exit"));
        menu.addCommand("1",new RunExampleCommand("1. Run 1",ex1.toString() + "\n*******\n",c1));
        menu.addCommand("2",new RunExampleCommand("2. Run 2",ex2.toString() + "\n*******\n",c2));
        menu.addCommand("3",new RunExampleCommand("3. Run 3",ex3.toString() + "\n*******\n",c3));
        menu.addCommand("4",new RunExampleCommand("4. Run file test",fileEx.toString() + "\n*******\n",c4));
        menu.addCommand("5",new RunExampleCommand("5. Run relation test",relEx.toString() + "\n*******\n",c5));
        menu.addCommand("6", new RunExampleCommand("6. Run heap alloc test", "Run heap alloc test", c6));
        menu.addCommand("7", new RunExampleCommand("7. Run read heap test", null, c7));
        menu.addCommand("8", new RunExampleCommand("8. Run heap write test", null, c8));
        menu.addCommand("9", new RunExampleCommand("9. Run garbage collector test", null, c9));
        menu.addCommand("10", new RunExampleCommand("10. Run while test", null, c10));
        menu.addCommand("11", new RunExampleCommand("11. Run while test 2", null, c11));

        IStmt forkStmt = new CompStmt(new VarDeclStmt("v", new IntType()), 
                                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), 
                                                new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(10))), 
                                                                new CompStmt(new AllocateHeapStmt("a", new ValueExpr(new IntValue(22))), 
                                                                        new CompStmt(new ForkStmt(
                                                                                        new CompStmt(new WriteHeapStmt("a",new ValueExpr(new IntValue(30))), 
                                                                                                                new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(32))), 
                                                                                                                        new CompStmt(new PrintStmt(new VariableExpr("v")), new PrintStmt(new ReadHeapExpr(new VariableExpr("a"))))))), 
                                                                                new CompStmt(new PrintStmt(new VariableExpr("v")), new PrintStmt(new ReadHeapExpr(new VariableExpr("a")))))))));
        IRepository r12 = new Repository("fork.txt");
        Controller c12 = new Controller(r12);
        PrgState forkState = new PrgState(new MyStack<IStmt>(), new MyMap<String, IValue>(), new MyList<IValue>(), forkStmt, new MyMap<String, BufferedReader>(), new Heap());
        c12.add(forkState);                                                                        


        IStmt fork2 = new CompStmt(new VarDeclStmt("v", new IntType()), 
                            new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), 
                                        new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(10))), 
                                            new CompStmt(new AllocateHeapStmt("a", new ValueExpr(new IntValue(22))), 
                                                    new CompStmt(new ForkStmt(
                                                            new CompStmt(new WriteHeapStmt("a",new ValueExpr(new IntValue(30))), 
                                                                        new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(32))), 
                                                                            new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(29))), new PrintStmt(new VariableExpr("v")))), new PrintStmt(new ReadHeapExpr(new VariableExpr("a"))))))), 
                                                                 new CompStmt(new PrintStmt(new VariableExpr("v")), new PrintStmt(new ReadHeapExpr(new VariableExpr("a")))))))));
        IRepository r13 = new Repository("fork_in_fork.txt");
        Controller c13 = new Controller(r13);
        PrgState forkState2 = new PrgState(new MyStack<IStmt>(), new MyMap<String, IValue>(), new MyList<IValue>(), fork2, new MyMap<String, BufferedReader>(), new Heap());
        c13.add(forkState2);    


        IStmt fork3 = new CompStmt(new VarDeclStmt("v", new IntType()), 
        new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), 
                        new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(10))), 
                                        new CompStmt(new AllocateHeapStmt("a", new ValueExpr(new IntValue(22))), 
                                                new CompStmt(new ForkStmt(
                                                                new CompStmt(new WriteHeapStmt("a",new ValueExpr(new IntValue(30))), 
                                                                                        new CompStmt(new AssignStmt("v", new ValueExpr(new IntValue(32))), 
                                                                                                new CompStmt(new PrintStmt(new VariableExpr("v")), new PrintStmt(new ReadHeapExpr(new VariableExpr("a"))))))), 
                                                        new CompStmt(new ForkStmt(new PrintStmt(new VariableExpr("v"))), new PrintStmt(new ReadHeapExpr(new VariableExpr("a")))))))));

        IRepository r14 = new Repository("fork_after_fork.txt");
        Controller c14 = new Controller(r14);
        PrgState forkState3 = new PrgState(new MyStack<IStmt>(), new MyMap<String, IValue>(), new MyList<IValue>(), fork3, new MyMap<String, BufferedReader>(), new Heap());
        c14.add(forkState3); 

        
        menu.addCommand("12", new RunExampleCommand("12. Run fork test", null, c12));
        menu.addCommand("13", new RunExampleCommand("13. Run fork in fork", null, c13));
        menu.addCommand("14", new RunExampleCommand("14. Run fork after fork", null, c14));
        menu.show();
    }
    
}
