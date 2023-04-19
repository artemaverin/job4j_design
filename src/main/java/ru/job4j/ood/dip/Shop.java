package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private List<Beer> beerList = new ArrayList<>();

    public void addBeer(Beer beer) {
        beerList.add(beer);
    }

    public List<Beer> getBeerList() {
        return beerList;
    }
}
