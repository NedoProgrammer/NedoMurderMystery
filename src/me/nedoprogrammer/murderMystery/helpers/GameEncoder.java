package me.nedoprogrammer.murderMystery.helpers;

import me.nedoprogrammer.murderMystery.game.Game;

import org.bukkit.Location;
import org.json.JSONObject;

public class GameEncoder {

    public static String encode(Game game)
    {
        var obj = new JSONObject();
        obj.put("name", game.name);
        obj.put("maxPlayers", game.maxPlayers);
        obj.put("spawn", encodeLocation(game.spawn));
        obj.put("lobby", encodeLocation(game.lobby));
        return obj.toString(4);
    }

    public static JSONObject encodeLocation(Location loc)
    {
        var result = new JSONObject();
        result.put("x", loc.getX());
        result.put("y", loc.getY());
        result.put("z", loc.getZ());
        result.put("world", loc.getWorld().getName());
        return result;
    }

}
