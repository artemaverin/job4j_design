package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ArgsNameTest {

    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[]{"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[]{"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512"});
        assertThatThrownBy(() -> jvm.get("Xms")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMultipleDotsKey() {
        ArgsName jvm = ArgsName.of(new String[]{"-hibernate.connection.password=password"});
        assertThat(jvm.get("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenWrongSomeArgument2() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-==password"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("current line: -==password does not contain Key");
    }

    @Test
    void whenWrongSomeArgument3() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("current line: -key= does not contain Value");
    }

    @Test
    void whenWrongSomeArgument4() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-key:password"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("current line: -key:password does not contain '=' symbol");
    }

    @Test
    void whenWrongSomeArgument5() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"key=password"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("current line: key=password does not contain '-' symbol");
    }

}