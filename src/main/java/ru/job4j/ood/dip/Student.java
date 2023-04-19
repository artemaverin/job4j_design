package ru.job4j.ood.dip;

public class Student {
    public void study(MathLesson math) {
        math.checkHomeWork();
    }
}

class MathLesson {
    public String checkHomeWork() {
        return "проверка ДЗ";
    }
}