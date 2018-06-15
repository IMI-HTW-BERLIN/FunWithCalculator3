package set;

import java.util.List;

public interface Set<T> {

    boolean isEmpty();

    boolean contains(T element);

    boolean equals(Set<T> set);

    int size();

    void add(T element);

    Set combine(Set<T> set);

    void remove(T element);

    Set subtract(Set<T> set);

    Set intersect(Set<T> set);

    List<T> setEntriesAsList();

}
