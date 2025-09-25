package me.thegreenman.bedwars.listeners.eventListener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import java.net.http.WebSocket;

public class craftListener implements Listener {

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
        event.getInventory().setResult(null);
    }
}
