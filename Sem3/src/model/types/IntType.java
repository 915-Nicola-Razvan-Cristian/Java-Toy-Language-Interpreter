package model.types;

import model.values.IValue;
import model.values.IntValue;

public class IntType implements IType {
    public boolean equals(IType other)
    {
        return other instanceof IntType;
    }
    public IType getType()
    {
        return new IntType();
    }

    public IValue defaultValue()
    {
        return new IntValue(0);
    }

    @Override
    public String toString()
    {
        return "int";
    }
}
