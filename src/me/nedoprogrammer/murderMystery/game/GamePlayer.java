package me.nedoprogrammer.murderMystery.game;

import org.bukkit.entity.Player;

public class GamePlayer {
    public Player spigotPlayer;
    public boolean isMurderer = false;

    public GamePlayer(Player spigotPlayer) {
        this.spigotPlayer = spigotPlayer;
    }
}
