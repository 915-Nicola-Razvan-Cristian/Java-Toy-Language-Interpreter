package model.adt;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

import exceptions.KeyNotFoundException;

public class MyMap<K, V> implements IMyMap<K, V>{
    Map<K,V> map;

    public MyMap()
    {
        map = new HashMap<>();
    }

    public MyMap(Map<K, V> dict)
    {
        map = new HashMap<K,V>(dict);
    }

    public V get(K key) throws KeyNotFoundException
    {
        if(!map.containsKey(key))
        {
            throw new KeyNotFoundException("Key not found");
        }
        return map.get(key);
    }

    @Override
    public void insert(K key, V value)
    {
        map.put(key, value);
    }

    public boolean contains(K key)
    {
        return map.containsKey(key);
    }

    public void remove(K key) throws KeyNotFoundException
    {
        if(!map.containsKey(key))
        throw new KeyNotFoundException("Key not found");
        map.remove(key);
    }

    public Set<K> keySet()
    {
        return map.keySet();
    }

    public Map<K,V> getContent()
    {
        return new HashMap<>(map);
    }


    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(K key : map.keySet())
        {
            str.append(key.toString()).append(" -> ").append(map.get(key));
            str.append("\n");
        }
        return str.toString();
    }
    public String toString2()
    {
        StringBuilder str = new StringBuilder();
        for(K key : map.keySet())
        {
            str.append(key.toString());
            str.append("\n");
        }
        return str.toString();   
    }
    @Override
    public IMyMap<K, V> deepCopy() {
        return new MyMap<>(this.map);
    }

}


