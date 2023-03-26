package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    @Test
    void whenMaxInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(43, 4, 56, 90, 13);
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        assertThat(maxMin.max(list, comparator)).isEqualTo(90);
    }

    @Test
    void whenMinInt() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(43, 4, 56, 90, 13);
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        assertThat(maxMin.min(list, comparator)).isEqualTo(4);
    }

    @Test
    void whenMaxString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("b", "d", "a", "y", "f", "c");
        Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);
        assertThat(maxMin.max(list, comparator)).isEqualTo("y");
    }

    @Test
    void whenMinString() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("b", "d", "a", "y", "f", "c");
        Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);
        assertThat(maxMin.min(list, comparator)).isEqualTo("a");
    }

    @Test
    void whenEmptyList() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of();
        Comparator<String> comparator = (o1, o2) -> o1.compareTo(o2);
        assertThatThrownBy(() -> maxMin.max(list, comparator)).isInstanceOf(IllegalArgumentException.class);
    }
}