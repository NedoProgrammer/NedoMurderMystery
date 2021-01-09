package me.nedoprogrammer.murderMystery.commands;

import me.nedoprogrammer.murderMystery.MurderMystery;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlayers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return false;
        var player = (Player) commandSender;
        if(strings.length != 2) {
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
        if(!isParsable(strings[1]))
        {
            player.sendMessage(ChatColor.RED + "%s - не число.".formatted(strings[1]));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        var max = Integer.parseInt(strings[1]);
        game.maxPlayers = max;
        player.sendMessage(ChatColor.GREEN + "Успешно задано максимальное количество игроков для игры \"%s\": %d!".formatted(strings[0], max));
        player.sendTitle(ChatColor.GREEN + "Успех", "", 10, 10, 10);
        return true;
    }

    public static boolean isParsable(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException exception)
        {
            return false;
        }
    }
}
