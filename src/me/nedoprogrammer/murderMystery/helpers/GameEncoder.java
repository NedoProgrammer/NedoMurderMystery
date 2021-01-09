package me.nedoprogrammer.murderMystery.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.nedoprogrammer.murderMystery.game.Game;

public class GameEncoder {

    public static String encode(Game game)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(game);
    }

}
