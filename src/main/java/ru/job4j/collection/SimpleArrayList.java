package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        expand();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T res = container[index];
        container[index] = newValue;
        return res;
    }

    @Override
    public T remove(int index) {
        T res = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                size - index - 1
        );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return res;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
       return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void expand() {
        if (container.length == 0) {
            container = (T[]) new Object[5];
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;

            private int count = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }
}
