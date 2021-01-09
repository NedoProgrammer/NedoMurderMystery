package me.nedoprogrammer.murderMystery;

import me.nedoprogrammer.murderMystery.commands.CreateGame;
import me.nedoprogrammer.murderMystery.commands.SetLobby;
import me.nedoprogrammer.murderMystery.commands.SetPlayers;
import me.nedoprogrammer.murderMystery.game.Game;
import me.nedoprogrammer.murderMystery.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class MurderMystery extends JavaPlugin {

    public final static String VERSION = "0.1";
    public final static ArrayList<Game> creatingGames = new ArrayList<>();
    @Override
    public void onEnable() {
        getLogger().info("Enabling NedoMurderMystery (version %s)".formatted(VERSION));
        getCommand("mmcreate").setExecutor(new CreateGame());
        getCommand("mmlobby").setExecutor(new SetLobby());
        getCommand("mmplayers").setExecutor(new SetPlayers());
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling NedoMurderMystery..");
    }
}
