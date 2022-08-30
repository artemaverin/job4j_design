package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void siArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .containsOnly("second", "four", "first", "five", "three")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .startsWith("first", "second")
                .endsWith("four", "five");
    }

    @Test
    void isList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> array = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .allSatisfy(e -> {
                    assertThat(e).isLowerCase();
                    assertThat(e).isNotEqualTo("six");
                })
                .anyMatch(e -> e.length() == 4)
                .noneMatch(e -> e.length() < 3);
        assertThat(array).first().isEqualTo("first");
        assertThat(array).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void isSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> array = simpleConvert.toSet("first", "second", "three", "four", "five", "three");
        assertThat(array).element(3).isNotNull();
        assertThat(array).filteredOn(e -> e.length() > 5).first().isEqualTo("second");
    }

    @Test
    void isMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> array = simpleConvert.toMap("zero", "first", "second");
        assertThat(array).hasSize(3)
                .containsKeys("zero", "second", "first")
                .containsValues(0, 2, 1)
                .doesNotContainKeys("fourth", "fifth")
                .doesNotContainValue(5)
                .containsEntry("second", 2);
    }
}