package model.adt;

import exceptions.KeyNotFoundException;

import java.util.Map;
import java.util.Set;
public interface IMyMap<K, V> {
    V get(K key) throws KeyNotFoundException;
    void insert(K key, V value);
    boolean contains(K key);
    void remove(K key) throws KeyNotFoundException;
    String toString2();
    Set<K> keySet();
    IMyMap<K, V> deepCopy();
    Map<K, V> getContent();
    
}
