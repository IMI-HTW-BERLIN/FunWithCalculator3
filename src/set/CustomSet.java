package set;

import java.util.ArrayList;

public class CustomSet<T> implements Set<T> {

    private ArrayList<T> setList;

    CustomSet() {
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
    public int size() {
        return setList.size();
    }

    @Override
    public void add(T element) {
        if (!contains(element))
            setList.add(element);
    }

    @Override
    public void addAll(Set<T> set) {
        for (T element : set.setEntriesAsList()) {
            add(element);
        }
    }

    @Override
    public void remove(T element) {
        if (contains(element))
            setList.remove(element);
    }

    @Override
    public void removeAll(Set<T> set) {
        for (T element : set.setEntriesAsList()) {
            remove(element);
        }
    }

    @Override
    public void retainAll(Set<T> set) {
        ArrayList<T> temp = new ArrayList<>();
        for (T element : set.setEntriesAsList()) {
            if (contains(element))
                temp.add(element);
        }
        setList = temp;
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
}
