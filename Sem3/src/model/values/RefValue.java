package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    private Integer address;
    private IType locationType;

    public RefValue(int addr, IType type)
    {
        address = addr;
        locationType = type;
    }

    public Integer getValue()
    {
        return address;
    }

    public IType getType()
    {
        return new RefType(locationType);
    }

    public IType getInnerType()
    {
        return this.locationType;
    }

    public void setAddr(Integer addr)
    {
        address = addr;
    }


    public IValue deepCopy()
    {
        return new RefValue(address, locationType);
    }

    @Override
    public boolean equals(IValue others) {
        return others.getType() instanceof RefValue && this.address == ((RefValue)others).getValue() 
        && this.locationType == ((RefValue)others).getInnerType();

    }

    @Override
    public String toString()
    {
        return "(" + String.valueOf(address) + ", " + locationType.toString() + ")";
    }
    

}
