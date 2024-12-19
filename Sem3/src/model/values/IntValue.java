package model.values;

import model.types.IType;
import model.types.IntType;

public class IntValue implements IValue{
    private int value;
    public boolean equals(IValue other)
    {
        if(other.getType() == this.getType())
        {
            return other.getType() instanceof IntValue && this.value == ((IntValue)other).getValue();
        }
        return false;
    }    


    public IType getType()
    {
        return new IntType();
    }

    public int getValue()
    {
        return this.value;
    }

    public IntValue(final int value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }


    @Override
    public IValue deepCopy() {
        return new IntValue(value);
    }

}
