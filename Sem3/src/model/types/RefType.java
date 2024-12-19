package model.types;

import model.values.IValue;
import model.values.RefValue;

public class RefType implements IType{
    private IType inner;

    public RefType(IType i)
    {
        inner = i;
    }

    

    public IType getInner()
    {
        return inner;
    }

    public void setInner(IType newInner)
    {
        inner = newInner;
    }

    @Override
    public boolean equals(IType other)
    {
        if(other instanceof RefType)
        {
            return inner.equals(((RefType)other).getInner());
        }
        else{
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "Ref(" + inner.toString() + ")";
    }

    public IValue defaultValue()
    {
        return new RefValue(1, inner);
    }

    // @Override
    // public boolean equals(IType other) {
    //     return other instanceof RefType;
    // }

    @Override
    public IType getType() {
        return new RefType(null);
    }

}
