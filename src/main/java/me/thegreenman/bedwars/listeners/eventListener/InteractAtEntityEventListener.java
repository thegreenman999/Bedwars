package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.PlayerClass;

import me.thegreenman.bedwars.Menys.ShopMeny;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class InteractAtEntityEventListener implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());

        if (playerClass == null) {
            return;
        }

        if (event.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            if (event.getRightClicked().getName().equals("Shop")) {
                ShopMeny.openShop(playerClass);
            }
        }

    }

}
