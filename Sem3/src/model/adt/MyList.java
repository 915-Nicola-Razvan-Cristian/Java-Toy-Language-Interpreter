package model.adt;

import java.util.ArrayList;
import java.util.List;;

public class MyList<T> implements IMyList<T>{

    private List<T> list;

    public MyList()
    {
        list = new ArrayList<T>();
    }

    public List<T> getAll()
    {
        return list;
    }

    public void add(T elem)
    {
        list.add(elem);
    }

    public void setList(List<T> l)
    {
        this.list = l;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(T elem : list)
        {
            str.append(elem);
            str.append("\n");
        }
        return "Out contains: \n" + str.toString();
    }
}
