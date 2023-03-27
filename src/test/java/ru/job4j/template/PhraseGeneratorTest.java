package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class PhraseGeneratorTest {

    @Test
    public void whenCorrectGeneration() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("subject", "you");
        assertThat(generator.produce(template, map)).isEqualTo("I am a Petr, Who are you?");
    }

    @Test
    public void whenKeysNotMatchWithMap() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, I am from ${place}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("subject", "you");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapHasExtraKeys() {
        PhraseGenerator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("subject", "you");
        map.put("place", "USA");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

}