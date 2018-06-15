package set;

import java.util.ArrayList;

public class CustomSet<T> implements Set<T> {

    private ArrayList<T> setList;

    public CustomSet(T... elements) {
        setList = new ArrayList<>();
        for (T element : elements) {
            add(element);
        }
    }

    public CustomSet() {
        setList = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return setList.isEmpty();
    }

    @Override
    public boolean contains(T element) {
        return setList.contains(element);
    }

    @Override
    public boolean equals(Set<T> set) {
        for (T element : set.setEntriesAsList()) {
            if(!contains(element)) return false;
        }
        return true;
    }

    @Override
    public int size() {
        return setList.size();
    }

    @Override
    public void add(T element) {
        if (!contains(element))
            setList.add(element);
    }

    @Override
    public Set combine(Set<T> set) {
        Set<T> temp = null;
        temp = (Set<T>) this.clone();

        for (T element : set.setEntriesAsList()) {
            temp.add(element);
        }

        return temp;
    }

    @Override
    public void remove(T element) {
        if (contains(element))
            setList.remove(element);
    }

    @Override
    public Set subtract(Set<T> set) {
        Set<T> temp = null;
        temp = (Set<T>) this.clone();

        for (T element : set.setEntriesAsList()) {
            temp.remove(element);
        }

        return temp;
    }

    @Override
    public Set intersect(Set<T> set) {
        Set<T> temp = new CustomSet<>();

        for (T element : set.setEntriesAsList()) {
            if (contains(element))
                temp.add(element);
        }

        return temp;
    }

    @Override
    public ArrayList<T> setEntriesAsList() {
        return setList;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ ");

        for (T element : setList) {
            string.append(element).append(" ");
        }

        return string.append("}").toString();
    }

    @Override
    protected Object clone() {
        Set<T> temp = new CustomSet<>();
        for (T element : setList) {
            temp.add(element);
        }
        return temp;
    }
}
