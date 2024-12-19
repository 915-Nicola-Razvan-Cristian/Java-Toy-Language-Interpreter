package model.types;

import model.values.IValue;

public interface IType {
    IValue defaultValue();
    boolean equals(IType other);
    IType getType();
}
