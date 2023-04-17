package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {

        for (Menu.MenuItemInfo i: menu) {
            String s = "";
            int count = 0;
            for (Character c : i.getNumberInfo().toCharArray()) {
                if (c == '.') {
                    count++;
                }
            }
            for (int j = 0; j < count - 1; j++) {
                s += "---";
            }
            System.out.println(s + i.getNameInfo() + " " + i.getNumberInfo());
        }
    }
}
