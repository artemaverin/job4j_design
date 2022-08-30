package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isUNKNOWN() {
        Box box = new Box(10, -1);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .endsWith("object");
    }

    @Test
    void idNegativeVertex() {
        Box box = new Box(2, 12);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(-1)
                .isNegative()
                .isNotNull();
    }
    @Test
    void idPositiveVertex() {
        Box box = new Box(4, 12);
        int num = box.getNumberOfVertices();
        assertThat(num).isEqualTo(4)
                .isPositive()
                .isEven()
                .isGreaterThan(2);
    }

    @Test
    void isExc() {
        Box box = new Box(4, 12);
        boolean num = box.isExist();
        assertThat(num).isEqualTo(true)
                .isNotNull()
                .isTrue();
    }

    @Test
    void isNotExc() {
        Box box = new Box(2, 12);
        boolean num = box.isExist();
        assertThat(num).isEqualTo(false)
                .isNotNull()
                .isFalse();
    }

    @Test
    void isCubeArea() {
        Box box = new Box(8, 3);
        double num = box.getArea();
        assertThat(num).isEqualTo(54);
    }

    @Test
    void isDefault() {
        Box box = new Box(3, 3);
        double num = box.getArea();
        assertThat(num).isEqualTo(0)
                .isLessThan(2)
                .isLessThanOrEqualTo(0.4);
    }
}