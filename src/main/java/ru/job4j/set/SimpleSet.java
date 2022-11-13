package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;
import ru.job4j.collection.SimpleList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean res = !contains(value);
        if (res) {
            set.add(value);
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T t: set) {
            if (Objects.equals(value, t)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return i < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(i++);
            }
        };
    }
}
