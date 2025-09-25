package me.thegreenman.bedwars.listeners.eventListener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if (!event.getBlock().equals(Material.GREEN_WOOL) && !event.getBlock().equals(Material.RED_WOOL) && !event.getBlock().equals(Material.BLUE_WOOL) && !event.getBlock().equals(Material.PINK_WOOL) &&
        !event.getBlock().equals(Material.GREEN_TERRACOTTA) && !event.getBlock().equals(Material.RED_TERRACOTTA) && !event.getBlock().equals(Material.BLUE_TERRACOTTA) && !event.getBlock().equals(Material.PINK_TERRACOTTA) &&
        !event.getBlock().equals(Material.END_STONE) && !event.getBlock().equals(Material.OAK_PLANKS)) {
                    event.setCancelled(true);
        }
    }
}
