package view;

import java.util.Scanner;


import java.util.HashMap;
import java.util.Map;
import view.commands.*;;

public class View {
    private Map<String, Command> commands;
    public View()
    {
        commands = new HashMap<String,Command>();
    }

    public void addCommand(String commandName, Command c)
    {
        commands.put(commandName, c);
    }

    public void show()
    {
        for(Command c: commands.values())
        {
            System.out.println(c.getKey());
        }
        var s = new Scanner(System.in);
        while (true) {
            System.out.println("> ");
            var chosenKey = s.nextLine();
            Command com = commands.get(chosenKey);
            if(!commands.containsKey(chosenKey))
            {
                System.out.println("Unknown command");
                continue;
            }
            try{
            com.execute();  
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                s.close();
                System.exit(-1);
            }
        }
        
        
    }
}

//     public void runProgram()
//     {
//         System.out.println("Input a program: ");
//         Scanner s = new Scanner(System.in);
//         System.out.println("Ex1\nEx2\nEx3\nEx4 - err");
//         int opt = s.nextInt();
//         switch (opt) {
//             case 1:
//                 {
//                 IMyStack<IStmt> exeStack = new MyStack<IStmt>();
//                 IMyMap<String, IValue> symTable = new MyMap<String, IValue>();
//                 IMyList<IValue> out = new MyList<IValue>();
                
//                 IMyMap<String, BufferedReader> fileTable = new MyMap<String, BufferedReader>();
//                 IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
//                 new CompStmt(new AssignStmt("v",new ValueExpr(new IntValue(2))), new PrintStmt(new VariableExpr("v"))));
//                 PrgState p = new PrgState(exeStack, symTable, out, ex1, fileTable);
//                 controller.add(p);
//                 try{
//                 controller.allStep();
//                 }
//                 catch(ExpressionException e)
//                 {
//                     System.err.println(e.getMessage());
//                 }
//                 catch(EmptyStackException e)
//                 {
//                     System.err.println(e.getMessage());
//                 }
//                 catch(StatementException e)
//                 {
//                     System.err.println(e.getMessage());
//                 }
//                 catch(KeyNotFoundException e)
//                 {
//                     System.err.println(e.getMessage());
//                 }
//                 catch(RepoException e)
//                 {
//                     System.err.println(e.getMessage());
//                 }
//                 break;
//                 }
//             case 2:
//             {
//                 IMyStack<IStmt> exeStack = new MyStack<IStmt>();
//                 IMyMap<String, IValue> symTable = new MyMap<String, IValue>();
//                 IMyList<IValue> out = new MyList<IValue>();
//                 IMyMap<String, BufferedReader> fileTable = new MyMap<String, BufferedReader>();   
//                 IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
//                 new CompStmt(new VarDeclStmt("b",new IntType()),
//                 new CompStmt(new AssignStmt("a", new ArithExp(new ValueExpr(new IntValue(2)),new
//                 ArithExp(new ValueExpr(new IntValue(3)), new ValueExpr(new IntValue(5)),3),1)),
//                 new CompStmt(new AssignStmt("b",new ArithExp(new VariableExpr("a"), new ValueExpr(new
//                 IntValue(1)),1)), new PrintStmt(new VariableExpr("b"))))));
//                 PrgState p = new PrgState(exeStack, symTable, out, ex2, fileTable);
//                 controller.add(p);
//                 try{
//                     controller.allStep();
//                     }
//                     catch(ExpressionException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(EmptyStackException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(StatementException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(KeyNotFoundException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(RepoException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                 break;
//             }
//             case 3:{
                
//                 IMyStack<IStmt> exeStack = new MyStack<IStmt>();
//                 IMyMap<String, IValue> symTable = new MyMap<String, IValue>();
//                 IMyList<IValue> out = new MyList<IValue>();
//                 IMyMap<String, BufferedReader> fileTable = new MyMap<String, BufferedReader>();   
//                 IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
//                 new CompStmt(new VarDeclStmt("v", new IntType()),
//                 new CompStmt(new AssignStmt("a", new ValueExpr(new BoolValue(true))),
//                 new CompStmt(new IfStmt(new VariableExpr("a"),new AssignStmt("v",new ValueExpr(new 
//                 IntValue(2))), new AssignStmt("v", new ValueExpr(new IntValue(3)))), new PrintStmt(new 
//                 VariableExpr("v"))))));
//                 PrgState p = new PrgState(exeStack, symTable, out, ex3, fileTable);
//                 controller.add(p);
//                 try{
//                     controller.allStep();
//                     }
//                     catch(ExpressionException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(EmptyStackException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(StatementException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(KeyNotFoundException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(RepoException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                 break;

//             }
//             case 4:
//             {
//                 IMyStack<IStmt> exeStack = new MyStack<IStmt>();
//                 IMyMap<String, IValue> symTable = new MyMap<String, IValue>();
//                 IMyList<IValue> out = new MyList<IValue>();
//                 IMyMap<String, BufferedReader> fileTable = new MyMap<String, BufferedReader>();  

//                 IStmt ex4 = new CompStmt(new VarDeclStmt("v",new IntType()),new VarDeclStmt("v",new IntType()));

//                 PrgState p = new PrgState(exeStack, symTable, out, ex4, fileTable);
//                 controller.add(p); 
//                 try{
//                     controller.allStep();
//                     }
//                     catch(ExpressionException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(EmptyStackException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(StatementException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(KeyNotFoundException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     catch(RepoException e)
//                     {
//                         System.err.println(e.getMessage());
//                     }
//                     break;

//             }
//             default:
//                 break;
//         }
//         s.close();
//     }
// }
