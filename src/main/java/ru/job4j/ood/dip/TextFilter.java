package ru.job4j.ood.dip;

public class TextFilter {
    Filter filter;
    String text;

    public TextFilter(String text) {
        this.text = text;
    }
    public String textEditor(String text) {
        return this.filter.someLogicFilter(this.text);
    }
 }

class Filter {
    String someLogicFilter(String text) {
        return "some logic" + text;
    }
}
