package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.Bedwars;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;
import java.util.List;

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


    // Chatgpt code
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        // List of blocks that are actually allowed to be destroyed
        List<Material> allowedBlocks = List.of(
                Material.GREEN_WOOL,
                Material.RED_WOOL,
                Material.BLUE_WOOL,
                Material.PINK_WOOL,
                Material.GREEN_TERRACOTTA,
                Material.RED_TERRACOTTA,
                Material.BLUE_TERRACOTTA,
                Material.PINK_TERRACOTTA,
                Material.END_STONE,
                Material.OAK_PLANKS
        );

        // Go through all the blocks that would be destroyed and remove the unauthorized ones
        Iterator<Block> it = event.blockList().iterator();
        while (it.hasNext()) {
            Block block = it.next();
            if (!allowedBlocks.contains(block.getType())) {
                it.remove(); // protects the block from explosion
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        event.setCancelled(!event.getBlock().getType().equals(Material.GREEN_WOOL) && !event.getBlock().getType().equals(Material.RED_WOOL) && !event.getBlock().getType().equals(Material.BLUE_WOOL) &&
                !event.getBlock().getType().equals(Material.PINK_WOOL) && !event.getBlock().getType().equals(Material.GREEN_TERRACOTTA) && !event.getBlock().getType().equals(Material.RED_TERRACOTTA) &&
                !event.getBlock().getType().equals(Material.BLUE_TERRACOTTA) && !event.getBlock().getType().equals(Material.PINK_TERRACOTTA) && !event.getBlock().getType().equals(Material.END_STONE) &&
                !event.getBlock().getType().equals(Material.OAK_PLANKS));

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.TNT)) {
            event.setCancelled(true);
            MenyListener.removeItems(event.getPlayer(), event.getBlock().getType(), 1);
            Bedwars.world.spawn(event.getBlockPlaced().getLocation(), TNTPrimed.class).setSource(event.getPlayer());
        }
    }

}