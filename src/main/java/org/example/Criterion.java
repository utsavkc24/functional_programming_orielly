package org.example;

public interface Criterion<E> {
    boolean test(E e);
}
