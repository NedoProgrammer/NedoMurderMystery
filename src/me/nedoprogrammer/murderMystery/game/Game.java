package me.nedoprogrammer.murderMystery.game;

import org.bukkit.Location;

import java.util.ArrayList;

public class Game {
    public String name;
    public Location lobby;
    public Location spawn;
    public int maxPlayers  = -1;
    public boolean playing = false;
    public ArrayList<GamePlayer> players = new ArrayList<>();

    public Game(String name) {
        this.name = name;
    }
}
