package model.adt;

import java.util.Map;

import exceptions.KeyNotFoundException;
import model.values.IValue;

public interface IHeap {
    int allocate(IValue value);
    void delete(Integer addr) throws KeyNotFoundException;
    boolean exists(Integer addr);
    void set(Integer addr, IValue value);
    IValue get(Integer addr) throws KeyNotFoundException;
    Map<Integer, IValue> getValues();
    void setContent(Map<Integer, IValue> content);
    Map<Integer, IValue> getContent();
}
