package set;

import java.util.ArrayList;

public interface Set<T> {

    boolean isEmpty();

    boolean contains(T element);

    int size();

    void add(T element);

    void addAll(Set<T> set);

    void remove(T element);

    void removeAll(Set<T> set);

    void retainAll(Set<T> set);

    ArrayList<T> setEntriesAsList();

}
