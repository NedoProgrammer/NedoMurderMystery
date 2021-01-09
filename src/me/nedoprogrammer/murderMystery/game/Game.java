package me.nedoprogrammer.murderMystery.game;

import org.bukkit.Location;

import java.util.ArrayList;

public class Game {
    public String name;
    public Location lobby;
    public int maxPlayers;
    public boolean playing = true;
    public ArrayList<GamePlayer> players = new ArrayList<>();

    public Game(String name) {
        this.name = name;
    }
}
