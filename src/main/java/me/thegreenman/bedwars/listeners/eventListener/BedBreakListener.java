package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.Bedwars;

import me.thegreenman.bedwars.GameStart;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static me.thegreenman.bedwars.Bedwars.*;

public class BedBreakListener implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (!gameOn) {
            return;
        }
        if (event.getBlock().getType().toString().endsWith("_BED")) {
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
                    removeBeds(new Location(world, gameConfig.getInt("RedTeam.Bed-loc.x"), gameConfig.getInt("RedTeam.Bed-loc.y"), gameConfig.getInt("RedTeam.Bed-loc.z")), gameConfig.getString("RedTeam.Bed-loc.facing"));
                    break;
                case GREEN_BED:
                    GameStart.TeamGreen.removeBed();
                    removeBeds(new Location(world, gameConfig.getInt("GreenTeam.Bed-loc.x"), gameConfig.getInt("GreenTeam.Bed-loc.y"), gameConfig.getInt("GreenTeam.Bed-loc.z")), gameConfig.getString("GreenTeam.Bed-loc.facing"));
                    break;
                case PINK_BED:
                    GameStart.TeamPink.removeBed();
                    removeBeds(new Location(world, gameConfig.getInt("PinkTeam.Bed-loc.x"), gameConfig.getInt("PinkTeam.Bed-loc.y"), gameConfig.getInt("PinkTeam.Bed-loc.z")), gameConfig.getString("PinkTeam.Bed-loc.facing"));
                    break;
                case BLUE_BED:
                    GameStart.TeamBlue.removeBed();
                    removeBeds(new Location(world, gameConfig.getInt("BlueTeam.Bed-loc.x"), gameConfig.getInt("BlueTeam.Bed-loc.y"), gameConfig.getInt("BlueTeam.Bed-loc.z")), gameConfig.getString("BlueTeam.Bed-loc.facing"));
                    break;
            }

            event.setDropItems(false);
        }
    }
}
