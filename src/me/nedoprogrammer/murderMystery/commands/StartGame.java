package me.nedoprogrammer.murderMystery.commands;

import me.nedoprogrammer.murderMystery.MurderMystery;
import me.nedoprogrammer.murderMystery.game.Game;
import me.nedoprogrammer.murderMystery.game.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGame implements CommandExecutor {
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

        var game = MurderMystery.playableGames.stream()
                .filter(obj -> obj.name.equals(strings[0]))
                .findFirst()
                .orElse(null);
        if(game == null)
        {
            player.sendMessage(ChatColor.RED + "Игры с названием %s не существует.".formatted(strings[0]));
            player.sendTitle(ChatColor.RED + "Ошибка", "", 10, 10, 10);
            return false;
        }
        game.acceptPlayers = true;
        player.sendMessage(ChatColor.GREEN + "Успешно начато ожидание игроков для игры \"%s\"!".formatted(strings[0]));
        player.sendTitle(ChatColor.GREEN + "Успех", "", 10, 10, 10);
        return true;
    }
}
