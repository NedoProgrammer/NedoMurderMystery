package me.nedoprogrammer.murderMystery.commands;

import me.nedoprogrammer.murderMystery.MurderMystery;
import me.nedoprogrammer.murderMystery.helpers.ConsoleColor;
import me.nedoprogrammer.murderMystery.helpers.GameEncoder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame implements CommandExecutor {
    private MurderMystery plugin;

    public SaveGame(MurderMystery plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return false;
        var player = (Player) commandSender;
        if(strings.length != 1) {
            player.sendMessage(ChatColor.RED + "Неверное количество аргументов.\nНужно: 1 | Получено: %d".formatted(strings.length));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        var game = MurderMystery.creatingGames.stream()
                .filter(obj -> obj.name.equals(strings[0]))
                .findFirst()
                .orElse(null);
        if(game == null)
        {
            player.sendMessage(ChatColor.RED + "Игры с названием %s не существует.".formatted(strings[0]));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        String output = "";
        if(game.maxPlayers == -1)
            output += "Не задано количество игроков!\n";
        if(game.lobby == null)
            output += "Не задано лобби игры!\n";
        if(game.spawn == null)
            output += "Не задана начальная точка игры!\n";
        if(!output.equals(""))
        {
            player.sendMessage(ChatColor.RED + "Не удалось сохранить игру \"%s\":\n%s".formatted(strings[0], output));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        else
        {
            var gameFile = new File(plugin.GAMES_DIRECTORY + "\\" + game.name + ".json");
            if(!gameFile.exists()) {
                try {
                    gameFile.createNewFile();
                    var fw = new FileWriter(gameFile);
                    var json = GameEncoder.encode(game);
                    fw.write(json);
                    fw.close();
                    player.sendMessage(ChatColor.GREEN + "Игра %s успешно сохранена!".formatted(strings[0]));
                    player.sendTitle(ChatColor.GREEN + "Успех", "", 10, 10, 10);
                    MurderMystery.creatingGames.remove(game);
                    MurderMystery.playableGames.add(game);
                    return true;
                } catch (IOException e) {
                    plugin.logger.info(ChatColor.DARK_RED + "Couldn't create a game file!" + ConsoleColor.RESET);
                    player.sendMessage(e.getMessage());
                }
            }
            return true;
        }
    }
}
