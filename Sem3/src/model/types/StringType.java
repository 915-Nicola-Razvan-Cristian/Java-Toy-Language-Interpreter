package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType{


    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public boolean equals(IType other) {
       return other instanceof StringType;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString()
    {
        return "string";
    }
    
}
