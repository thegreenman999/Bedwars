package me.thegreenman.bedwars.listeners.eventListener;

import static me.thegreenman.bedwars.Bedwars.gameOn;
import static me.thegreenman.bedwars.Bedwars.main;
import me.thegreenman.bedwars.Bedwars;

import me.thegreenman.bedwars.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BedBreakListener implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (!gameOn) {
            return;
        }
        if (!event.getBlock().getType().toString().endsWith("_BED")) {
            return;
        }
        if (Bedwars.lang.getString("bed-break-massage") == null) {
            main.getLogger().severe("language file has a empty part called bed-break-massage");
        }

        Bukkit.broadcastMessage(Bedwars.lang.getString("bed-break-massage").replace("<bed>", switch (event.getBlock().getType().toString()) {
            case "RED_BED" -> ChatColor.BOLD + "" + ChatColor.RED + "Reds Bed";
            case "GREEN_BED" -> ChatColor.BOLD + "" + ChatColor.GREEN + "Greens Bed";
            case "BLUE_BED" -> ChatColor.BOLD + "" + ChatColor.BLUE + "Blues Bed";
            case "PINK_BED" -> ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Pinks Bed";
            default -> throw new IllegalStateException("Unexpected value: " + event.getBlock().getType().toString());
        }));

        switch (event.getBlock().getType()) {
            case RED_BED:
                GameStart.TeamRed.removeBed();
            case GREEN_BED:
                GameStart.TeamGreen.removeBed();
            case PINK_BED:
                GameStart.TeamPink.removeBed();
            case BLUE_BED:
                GameStart.TeamBlue.removeBed();
        }

        event.setDropItems(false);
    }
}
