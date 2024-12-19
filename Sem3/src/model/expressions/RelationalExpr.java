package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IHeap;
import model.adt.IMyMap;
import model.types.BoolType;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

public class RelationalExpr implements IExpr{

    private IExpr expr1;
    private IExpr expr2;
    private RelationalOperator operator;

    public RelationalExpr(IExpr e1, IExpr e2, RelationalOperator op) {
        expr1 = e1;
        expr2 = e2;
        operator = op;
    }

    @Override
    public IValue evaluate(IMyMap<String, IValue> symTbl, IHeap heap) throws KeyNotFoundException, ExpressionException {
        IValue value1 = expr1.evaluate(symTbl, heap);
        IValue value2 = expr2.evaluate(symTbl, heap);

        if (!(value1 instanceof IntValue) || !(value2 instanceof IntValue)) {
            throw new ExpressionException("Relational expressions can only be applied to integers.");
        }

        int int1 = ((IntValue) value1).getValue();
        int int2 = ((IntValue) value2).getValue();

        BoolValue result = new BoolValue(false);
        switch (operator) {
            case LESS_THAN:
                result.setValue(int1 < int2);
                break;
            case LESS_THAN_OR_EQUAL:
                result.setValue(int1 <= int2);
                break;
            case EQUAL:
                result.setValue(int1 == int2);
                break;
            case NOT_EQUAL:
                result.setValue(int1 != int2);
                break;
            case GREATER_THAN:
                result.setValue(int1 > int2);
                break;
            case GREATER_THAN_OR_EQUAL:
                result.setValue(int1 >= int2);
                break;
            default:
                throw new ExpressionException("Unknown relational operator.");
        }
        return result;
    }

    @Override
    public IExpr deepCopy() {
        return new RelationalExpr(expr2, expr1, operator);
    }

    @Override
    public String toString()
    {
        return expr1.toString() + " " + operator.toString() + " " + expr2.toString();
    }

    @Override
    public IType typecheck(IMyMap<String, IType> typeEnv) throws Exception {
        IType typ1, typ2;
        typ1 = expr1.typecheck(typeEnv);
        typ2 = expr2.typecheck(typeEnv);
        if(typ1.equals(new IntType()))
        {
            if(typ2.equals(new IntType()))
                return new BoolType();
            else
                throw new Exception("Second operand is not an integer");
        }
        else
            throw new Exception("First operand is not an integer");

    }
}
