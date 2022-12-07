package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final PlayerSerializable ps = new PlayerSerializable(true, 100,
                new String[] {"health potion", "sword", "bow"}, new Pet("horse"));

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(ps));

        /* Модифицируем json-строку */
        final String playerJson =
                "{"
                        + "\"hasArmor\":false,"
                        + "\"health\":85,"
                        + "\"inventory\":"
                        + "[\"coins\",\"mana potion\"],"
                        + "\"pet\":"
                        + "{"
                        + "\"petType\":\"eagle\""
                        + "}"
                        + "}";

        final PlayerSerializable player = gson.fromJson(playerJson, PlayerSerializable.class);
        System.out.println(player);
    }
}
