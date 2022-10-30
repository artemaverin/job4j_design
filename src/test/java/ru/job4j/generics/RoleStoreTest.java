package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "role 1"));
        Role res = roleStore.findById("2");
        assertThat(res.getRole()).isEqualTo("role 1");
    }

    @Test
    void whenAddAndNotFindRole() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "role 1"));
        Role res = roleStore.findById("1");
        assertThat(res).isNull();
    }

    @Test
    void whenReplaceIsTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "role 1"));
        boolean res = roleStore.replace("2", new Role("1", "replaced role"));
        assertThat(res).isTrue();
    }

    @Test
    void whenReplaceIsFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("2", "role 1"));
        boolean res = roleStore.replace(null, new Role("1", "replaced role"));
        assertThat(res).isFalse();
    }

    @Test
    void whenDeleteTrue() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "role 1"));
        roleStore.add(new Role("2", "role 2"));
        boolean res = roleStore.delete("2");
        assertThat(res).isTrue();
    }

    @Test
    void whenDeleteFalse() {
        RoleStore roleStore = new RoleStore();
        roleStore.add(new Role("1", "role 1"));
        roleStore.add(new Role("2", "role 2"));
        boolean res = roleStore.delete("3");
        assertThat(res).isFalse();
    }

}