package me.nedoprogrammer.murderMystery.listeners;

import me.nedoprogrammer.murderMystery.MurderMystery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        var player = event.getPlayer();
        var foundGame = MurderMystery.creatingGames.stream().
                filter(game -> game.players.stream().
                        filter(gamePlayer -> gamePlayer.spigotPlayer.getName().equals(player.getName()))
                        .findFirst()
                        .orElse(null) != null)
                .findFirst()
                .orElse(null);
        if(foundGame == null)
            return;
        if(foundGame.playing)
            event.setCancelled(true);
    }
}
