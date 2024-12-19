package model.adt;


import java.util.HashMap;
import java.util.Map;

import exceptions.KeyNotFoundException;
import model.values.IValue;

public class Heap implements IHeap{

    private Map<Integer, IValue> values;
    private int freeAddr;

    public Heap()
    {
        values = new HashMap<Integer, IValue>();
        freeAddr = 1;
    }

    @Override
    public int allocate(IValue value) {
        values.put(freeAddr++, value);
        return freeAddr - 1;
    }

    @Override
    public void delete(Integer addr) throws KeyNotFoundException {
        values.remove(addr);
    }

    @Override
    public boolean exists(Integer addr) {
        return values.containsKey(addr);
    }

    @Override
    public void set(Integer addr, IValue value) {
        values.put(addr, value);
    }


    @Override
    public void setContent(Map<Integer, IValue> content)
    {
        this.values = content;
    }

    @Override
    public IValue get(Integer addr) throws KeyNotFoundException{
        if(values.containsKey(addr))
            return values.get(addr);
        else 
            throw new KeyNotFoundException("Key not found");
    }



    @Override
    public Map<Integer, IValue> getContent()
    {
        return new HashMap<>(values);
    }



    @Override
    public Map<Integer, IValue> getValues() {
        return new HashMap<>(values);
    }
    

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(Integer key : values.keySet())
        {
            try {
                str.append(key.toString()).append(" -> ").append(values.get(key));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            str.append("\n");
        }
        return str.toString();
    }

    
}
