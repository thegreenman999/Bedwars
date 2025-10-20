package me.thegreenman.bedwars.commands;

import me.thegreenman.bedwars.Bedwars;

import me.thegreenman.bedwars.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ResetCommand implements CommandExecutor, Listener {

    private static List<Block> placeBlock = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (!(sender instanceof Player player)) {
//            return false;
//        }

        ResetCommand.reset();
        Bedwars.main.reset();

        return true;
    }



    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        if (!Bedwars.gameOn) {
            return;
        }
        if (event.getBlock().getType().name().endsWith("_BED")) {
            return;
        }

        placeBlock.add(event.getBlockPlaced());

    }

    public static void reset() {
        clearChests();

        if (placeBlock.isEmpty()) {
            return;
        }
        List<Block> toRemove = new ArrayList<>();
        for (Block block : placeBlock) {
            if (!block.getLocation().getBlock().getType().equals(Material.AIR)) {
                toRemove.add(block);
                block.setType(Material.AIR);
            }
        }

        placeBlock.removeAll(toRemove);
    }

    private static void clearChests() {
        for (int i = 0; i < 4; i++) {
            String team = switch (i) {
                case 0 -> "RedTeam";
                case 1 -> "PinkTeam";
                case 2 -> "BlueTeam";
                case 3 -> "GreenTeam";
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };

            Location loc = new Location(
                    Bedwars.world,
                    Bedwars.gameConfig.getInt(team + ".chest-loc.x"),
                    Bedwars.gameConfig.getInt(team + ".chest-loc.y"),
                    Bedwars.gameConfig.getInt(team + ".chest-loc.z"));

            loc.getBlock().setType(Material.AIR);
            loc.getBlock().setType(Material.CHEST);

//            if (!(block.getState() instanceof Chest chest)) {
//                return;
//            }
//
//            Inventory inv = chest.getInventory();
//            inv.clear();
//            chest.update();

//            if (Bedwars.debug) {
//                Logger.log("chest at " + chest.getLocation() + " has bin cleared");
//            }
        }
    }

}
