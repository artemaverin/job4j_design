package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }
        private Node<E> head;
        private int modCount;
        private int size;

        public SimpleLinkedList() {
            this.head = new Node<E>();
        }


        @Override
        public void add(E value) {
            Node<E> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<E>(value, null);
            modCount++;
            size++;
        }

        @Override
        public E get(int index) {
            Objects.checkIndex(index, size);
            int count = 0;
            Node<E> currentNode = head.next;
            while (currentNode != null) {
                if (count == index) {
                    return currentNode.data;
                }
                count += 1;
                currentNode = currentNode.next;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {

                private int expectedModCount = modCount;

                Node<E> currentNode = head.next;

                @Override
                public boolean hasNext() {
                    if (expectedModCount != modCount) {
                        throw new ConcurrentModificationException();
                    }
                    return currentNode != null;
                }

                @Override
                public E next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    Node<E> nextNode = currentNode;
                    currentNode = currentNode.next;
                    return nextNode.data;
                }
            };
        }
}
