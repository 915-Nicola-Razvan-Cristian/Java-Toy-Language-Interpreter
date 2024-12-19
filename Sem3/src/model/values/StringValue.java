package model.values;

import model.types.IType;
import model.types.StringType;

public class StringValue implements IValue{

    private String value;

    public StringValue(String v)
    {
        value = v;
    }

    @Override
    public IType getType() {
        return new StringType();
    }


    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(IValue others) {
        if(others.getType() == this.getType())
        {
            return others.getType() instanceof StringValue && value == ((StringValue)others).getValue();
        }
        return false;
    }


    public IValue deepCopy()
    {
        return new StringValue(value);
    }

    @Override
    public String toString()
    {
        if(value == "")
        {
            return "''";
        }
        return value;
    }
    
}
