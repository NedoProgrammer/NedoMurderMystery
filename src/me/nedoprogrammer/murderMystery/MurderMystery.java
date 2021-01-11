package me.nedoprogrammer.murderMystery;

import com.sun.source.doctree.StartElementTree;
import me.nedoprogrammer.murderMystery.commands.*;
import me.nedoprogrammer.murderMystery.game.Game;
import me.nedoprogrammer.murderMystery.helpers.ConsoleColor;
import me.nedoprogrammer.murderMystery.helpers.GameDecoder;
import me.nedoprogrammer.murderMystery.listeners.BlockBreakListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class MurderMystery extends JavaPlugin {

    public Logger logger = Bukkit.getLogger();
    public final static String VERSION = "0.1";
    public final static ArrayList<Game> creatingGames = new ArrayList<>();
    public final static ArrayList<Game> playableGames = new ArrayList<>();
    public final String GAMES_DIRECTORY = getDataFolder().getAbsolutePath() + "\\Games";
    @Override
    public void onEnable() {
        logger.info("Enabling NedoMurderMystery (version %s)".formatted(VERSION));
        if(!getDataFolder().exists())
        {
           logger.info(ChatColor.RED + "Data directory does not exist! Creating.");
           if(!getDataFolder().mkdir())
               logger.info(ConsoleColor.RED_BOLD + "Couldn't create the Data directory! This can cause problems when saving the game." + ConsoleColor.RESET);
        }
        var gamesDirectory = new File(GAMES_DIRECTORY);
        if(gamesDirectory.exists())
        {
            for (File file : Objects.requireNonNull(gamesDirectory.listFiles())) {
                try {
                    logger.info(ConsoleColor.YELLOW + "Loading the \"%s\" game..".formatted(file.getName()) + ConsoleColor.RESET);
                    var scanner = new Scanner(file);
                    StringBuilder data = new StringBuilder();
                    while(scanner.hasNextLine())
                        data.append(scanner.nextLine());
                    playableGames.add(GameDecoder.decode(data.toString()));
                    logger.info(ConsoleColor.GREEN + "Success!" + ConsoleColor.RESET);
                } catch (FileNotFoundException e) {
                    logger.info(ConsoleColor.RED_BOLD + "Failed to load the \"%s\" game!!".formatted(file.getName()) + ConsoleColor.RESET);
                    e.printStackTrace();
                }

            }
        }
        else
        {
            logger.info(ConsoleColor.RED + "Games directory does not exist! Creating." + ConsoleColor.RESET);
            if(!gamesDirectory.mkdir())
                logger.info(ConsoleColor.RED_BOLD + "Couldn't create the Games directory! This can cause problems when saving the game." + ConsoleColor.RESET);
        }

        getCommand("mmcreate").setExecutor(new CreateGame());
        getCommand("mmlobby").setExecutor(new SetLobby());
        getCommand("mmplayers").setExecutor(new SetPlayers());
        getCommand("mmworld").setExecutor(new SetWorld());
        getCommand("mmstart").setExecutor(new StartGame());
        getCommand("mmjoin").setExecutor(new JoinGame());
        getCommand("mmleave").setExecutor(new LeaveGame());
        getCommand("mmsave").setExecutor(new SaveGame(this));
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

    }

    @Override
    public void onDisable() {
        logger.info("Disabling NedoMurderMystery.." );
    }
}
