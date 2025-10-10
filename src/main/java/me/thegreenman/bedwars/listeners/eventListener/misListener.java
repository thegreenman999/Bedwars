package me.thegreenman.bedwars.listeners.eventListener;

import io.papermc.paper.connection.PlayerLoginConnection;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class misListener implements Listener {

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
        event.getInventory().setResult(null);
    }

    // Fireball
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }
        if (event.getAction().isLeftClick()) {
            return;
        }
        if (event.getItem().getType().equals(Material.FIRE_CHARGE)) {
            //event.getPlayer().getLocation().get
            Fireball fireball = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Fireball.class);
            fireball.setDirection(event.getPlayer().getEyeLocation().getDirection());
            event.getItem().setAmount(event.getItem().getAmount() - 1);
        }
    }
}
