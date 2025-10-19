package me.thegreenman.bedwars.commands;

import me.thegreenman.bedwars.Bedwars;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

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
        // TODO: clear all chest in the arena
        placeBlock.removeAll(toRemove);
    }

}
