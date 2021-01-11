package me.nedoprogrammer.murderMystery.game;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    public String name;
    public Location lobby;
    public Location spawn;
    public int maxPlayers  = -1;
    public boolean playing = false;
    public boolean acceptPlayers = false;
    public ArrayList<GamePlayer> players = new ArrayList<>();

    public Game(String name) {
        this.name = name;
    }

    public void start()
    {
        for (GamePlayer player : players) {
            player.spigotPlayer.teleport(spawn);
        }
    }

    public void join(Player p)
    {
        var toAdd = new GamePlayer(p);
        toAdd.previousLocation = p.getLocation();
        players.add(toAdd);
        p.teleport(lobby);
        var playerColor = players.size() == maxPlayers ? ChatColor.GREEN : ChatColor.RED;
        for (Player player : lobby.getWorld().getPlayers()) {
            player.sendMessage("[%sNedoMurderMystery%s] %s%s %sприсоединился к игре! (%s%d%s/%s%d%s)".formatted(ChatColor.DARK_RED, ChatColor.WHITE, ChatColor.GREEN, p.getName(), ChatColor.GOLD, playerColor, players.size(), ChatColor.WHITE, ChatColor.GREEN, maxPlayers, ChatColor.WHITE));
        }
    }

    public void leave(Player player)
    {
        var gamePlayer = players.stream()
                .filter(p -> p.spigotPlayer.getName().equals(player.getName()))
                .findFirst()
                .orElse(null);
        if(gamePlayer == null) return;
        players.remove(gamePlayer);
        gamePlayer.spigotPlayer.teleport(gamePlayer.previousLocation);
        var playerColor = players.size() == maxPlayers ? ChatColor.GREEN : ChatColor.RED;
        for (Player joined : lobby.getWorld().getPlayers()) {
            joined.sendMessage("[%sNedoMurderMystery%s] %s%s %sвышел из игры! (%s%d%s/%s%d%s)".formatted(ChatColor.DARK_RED, ChatColor.WHITE, ChatColor.GREEN, gamePlayer.spigotPlayer.getName(), ChatColor.GOLD, playerColor, players.size(), ChatColor.WHITE, ChatColor.GREEN, maxPlayers, ChatColor.WHITE));
        }
    }
}
