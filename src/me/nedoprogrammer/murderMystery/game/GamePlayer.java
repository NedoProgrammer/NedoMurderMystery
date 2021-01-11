package me.nedoprogrammer.murderMystery.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GamePlayer {
    public Player spigotPlayer;
    public Location previousLocation;
    public boolean isMurderer = false;

    public GamePlayer(Player spigotPlayer) {
        this.spigotPlayer = spigotPlayer;
    }
}
