package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.PlayerClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class deathListener implements Listener {

    // check if bed is destord
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());



    }
}
