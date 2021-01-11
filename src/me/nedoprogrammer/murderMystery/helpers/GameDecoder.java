package me.nedoprogrammer.murderMystery.helpers;

import me.nedoprogrammer.murderMystery.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.json.JSONObject;

public class GameDecoder {

    public static Game decode(String json)
    {
        var obj = new JSONObject(json);
        var name = obj.getString("name");
        var lobby = decodeLocation(obj.getJSONObject("lobby"));
        var spawn = decodeLocation(obj.getJSONObject("spawn"));
        var maxPlayers = obj.getInt("maxPlayers");
        var game = new Game(name);
        game.maxPlayers = maxPlayers;
        game.lobby = lobby;
        game.spawn = spawn;
        return game;
    }

    public static Location decodeLocation(JSONObject obj)
    {
        var name = obj.getString("world");
        var x = obj.getDouble("x");
        var y = obj.getDouble("y");
        var z = obj.getDouble("z");
        var world = Bukkit.getServer().getWorld(name);
        return new Location(world, x, y, z);
    }

}
