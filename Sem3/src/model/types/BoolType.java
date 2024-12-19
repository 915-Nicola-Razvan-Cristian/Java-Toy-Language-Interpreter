package model.types;

import model.values.BoolValue;
import model.values.IValue;

public class BoolType implements IType{
    private final boolean value=false;

    public boolean equals(IType other)
    {
        return other instanceof BoolType;
    }

    public boolean getValue()
    {
        return value;
    }

    public IValue defaultValue()
    {
        return new BoolValue(false);
    }

    public IType getType()
    {
        return new BoolType();
    }

    @Override
    public String toString()
    {
        return "boolean";
    }
}
