package me.thegreenman.bedwars.listeners.eventListener;

import static me.thegreenman.bedwars.Bedwars.config;
import static me.thegreenman.bedwars.Bedwars.gameConfig;

import me.thegreenman.bedwars.*;
import me.thegreenman.bedwars.tasks.RespawnTask;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class DeathListener implements Listener {
    private static Location specSpawn = new Location(Bedwars.world,
            gameConfig.getInt("spec-spawn-loc.x"),
            gameConfig.getInt("spec-spawn-loc.y"),
            gameConfig.getInt("spec-spawn-loc.z"));

    // check if bed is destord
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        String deathMessage = "";
        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());

        if (Bedwars.lang.getString("Death-massage") != null) {
            deathMessage = Bedwars.lang.getString("Death-massage");
        }
        deathMessage.replace("<player>", event.getPlayer().getName());
//        deathMessage.replace("<killer>", event.getPlayer().getKiller().getName());

        if (playerClass == null) {
            return;
        }

        player.setGameMode(GameMode.SPECTATOR);

        player.getInventory().clear();

        playerClass.subPickaxeLevel();
        playerClass.subAxeLevel();

        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(specSpawn);
            }
        }.runTaskLater(Bedwars.main, 1L); // makes the player start spec in the arena

        if (playerClass.Team != null && playerClass.Team.getBed()) {
            RespawnTask.respawnTaskList.add(new RespawnTask(playerClass).runTaskLater(Bedwars.main, config.getInt("respawnTime") * 20L));
            deathMessage.replace("<final>", "");
        }
        else if (playerClass.Team != null) {
            playerClass.Team.removePlayer(playerClass);
            playerClass.Team = null;
            deathMessage.replace("<final>", ChatColor.AQUA + "" + ChatColor.BOLD + "Final kill");

            GameStop.Check();

        }

        event.setDeathMessage(deathMessage);

    }

}
