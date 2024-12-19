package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue{
    private boolean value;

    
    public boolean equals(IValue other)
    {
        if(other.getType() == this.getType())
        {
            return other.getType() instanceof BoolValue && this.value == ((BoolValue)other).getValue();
        }
        return false;
    }    


    public void setValue(boolean value)
    {
        this.value = value;
    }

    public IType getType()
    {
        return new BoolType();
    }

    public boolean getValue()
    {
        return this.value;
    }

    public BoolValue(final boolean value)
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
        return new BoolValue(value);
    }

}
