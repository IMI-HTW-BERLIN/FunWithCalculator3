package set;

import java.util.List;

public interface Set<T> {

    boolean isEmpty();

    boolean contains(T element);

    int size();

    void add(T element);

    void addAll(Set<T> set);

    void remove(T element);

    void removeAll(Set<T> set);

    void retainAll(Set<T> set);

    List<T> setEntriesAsList();

}
