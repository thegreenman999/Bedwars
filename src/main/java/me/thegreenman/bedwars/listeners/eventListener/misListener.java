package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.Bedwars;
import me.thegreenman.bedwars.PlayerClass;
import me.thegreenman.bedwars.tasks.RespawnTask;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.thegreenman.bedwars.Bedwars.config;
import static me.thegreenman.bedwars.Bedwars.gameConfig;

public class misListener implements Listener {
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

        if (playerClass.Team.getBed() && playerClass.Team != null) {
            new RespawnTask(playerClass).runTaskLater(Bedwars.main, config.getInt("respawnTime")* 20L);
        }
        else {
            playerClass.Team.removePlaye(playerClass);
            playerClass.Team = null;
        }

    }

    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event) {
        event.getInventory().setResult(null);
    }

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
