package me.nedoprogrammer.murderMystery.commands;

import me.nedoprogrammer.murderMystery.MurderMystery;
import me.nedoprogrammer.murderMystery.game.Game;
import me.nedoprogrammer.murderMystery.game.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGame implements CommandExecutor {
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
        var toAdd = new Game(strings[0]);
        toAdd.players.add(new GamePlayer(player));
        MurderMystery.creatingGames.add(toAdd);
        player.sendMessage(ChatColor.GREEN + "Успешно начато создание игры \"%s\"!".formatted(strings[0]));
        player.sendTitle(ChatColor.GREEN + "Успех", "", 10, 10, 10);
        return true;
    }
}
