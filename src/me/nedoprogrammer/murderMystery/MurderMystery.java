package me.nedoprogrammer.murderMystery;

import me.nedoprogrammer.murderMystery.commands.*;
import me.nedoprogrammer.murderMystery.game.Game;
import me.nedoprogrammer.murderMystery.listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MurderMystery extends JavaPlugin {

    public ConsoleCommandSender logger = Bukkit.getConsoleSender();
    public final static String VERSION = "0.1";
    public final static ArrayList<Game> creatingGames = new ArrayList<>();
    public final String GAMES_DIRECTORY = getDataFolder().getAbsolutePath() + "\\Games";
    @Override
    public void onEnable() {
        logger.sendMessage("Enabling NedoMurderMystery (version %s)".formatted(VERSION));
        if(!getDataFolder().exists())
        {
           logger.sendMessage(ChatColor.RED + "Data directory does not exist! Creating.");
           if(!getDataFolder().mkdir())
               logger.sendMessage(ChatColor.DARK_RED + "Couldn't create the Data directory! This can cause problems when saving the game.");
        }
        var gamesDirectory = new File(GAMES_DIRECTORY);
        if(!gamesDirectory.exists())
        {
            logger.sendMessage(ChatColor.RED + "Games directory does not exist! Creating.");
            if(!gamesDirectory.mkdir())
                logger.sendMessage(ChatColor.DARK_RED + "Couldn't create the Games directory! This can cause problems when saving the game.");
        }

        getCommand("mmcreate").setExecutor(new CreateGame());
        getCommand("mmlobby").setExecutor(new SetLobby());
        getCommand("mmplayers").setExecutor(new SetPlayers());
        getCommand("mmworld").setExecutor(new SetWorld());
        getCommand("mmsave").setExecutor(new SaveGame(this));
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {
        logger.sendMessage("Disabling NedoMurderMystery..");
    }
}
