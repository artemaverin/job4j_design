package ru.job4j.io;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenPairWithCommentAndGaps() {
        String path = "./data/pair_with_comment_and_gaps.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }

    @Test
    void whenPairWithException() {
        String path = "./data/pair_with exception.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("incorrect line : hibernate.connection.username=");
    }

    @Test
    void whenPairWithInaccuracies() {
        String path = "./data/pair_with_expected_inaccuracies.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"))
                .isEqualTo("SomeDriver");
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password");
    }

}