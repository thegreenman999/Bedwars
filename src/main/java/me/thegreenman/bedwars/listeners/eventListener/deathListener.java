package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.Bedwars;
import me.thegreenman.bedwars.PlayerClass;
import me.thegreenman.bedwars.tasks.RespawnTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.thegreenman.bedwars.Bedwars.config;
import static me.thegreenman.bedwars.Bedwars.gameConfig;

public class deathListener implements Listener {

    private static Location specSpawn = new Location(Bedwars.world, gameConfig.getInt("spec-spawn-loc.x"),
            gameConfig.getInt("spec-spawn-loc.y"),
            gameConfig.getInt("spec-spawn-loc.z"));

    // check if bed is destord
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());

        if (playerClass == null) {
            return;
        }
        player.setGameMode(GameMode.SPECTATOR);

        player.getInventory().clear();



        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(specSpawn);
            }
        }.runTaskLater(Bedwars.main, 2L); // to make the player start spec in the arena

        if (playerClass.Team.getBed()) {
            new RespawnTask(playerClass).runTaskLater(Bedwars.main, config.getInt("respawnTime")* 20L);
        }
        else {
            playerClass.Team.removePlaye(playerClass);
            playerClass.Team = null;
        }

    }
}
