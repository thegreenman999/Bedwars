package me.thegreenman.bedwars.tasks;

import me.thegreenman.bedwars.PlayerClass;
import me.thegreenman.bedwars.utils.Logger;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import static me.thegreenman.bedwars.Bedwars.*;

public class RespawnTask extends BukkitRunnable {
    private PlayerClass playerClass;

    @Override
    public void run() {
        playerClass.getPlayer().teleport(switch (playerClass.Team.getColor().toLowerCase()) {
            case "blue" -> new Location(world,
                        gameConfig.getInt("BlueTeam.spawn-loc.x"),
                        gameConfig.getInt("BlueTeam.spawn-loc.y"),
                        gameConfig.getInt("BlueTeam.spawn-loc.z"),
                        gameConfig.getInt("BlueTeam.spawn-loc.yaw"),
                        gameConfig.getInt("BlueTeam.spawn-loc.pitch"));
            case "green" -> new Location(world,
                        gameConfig.getDouble("GreenTeam.spawn-loc.x"),
                        gameConfig.getDouble("GreenTeam.spawn-loc.y"),
                        gameConfig.getDouble("GreenTeam.spawn-loc.z"),
                        (float) gameConfig.getDouble("GreenTeam.spawn-loc.yaw"),
                        (float) gameConfig.getDouble("GreenTeam.spawn-loc.pitch"));
            case "pink" -> new Location(world,
                        gameConfig.getInt("PinkTeam.spawn-loc.x"),
                        gameConfig.getInt("PinkTeam.spawn-loc.y"),
                        gameConfig.getInt("PinkTeam.spawn-loc.z"),
                        gameConfig.getInt("PinkTeam.spawn-loc.yaw"),
                        gameConfig.getInt("PinkTeam.spawn-loc.pitch"));
            case "red" -> new Location(world,
                    gameConfig.getDouble("RedTeam.spawn-loc.x"),
                    gameConfig.getDouble("RedTeam.spawn-loc.y"),
                    gameConfig.getDouble("RedTeam.spawn-loc.z"),
                    (float) gameConfig.getDouble("RedTeam.spawn-loc.yaw"),
                    (float) gameConfig.getDouble("RedTeam.spawn-loc.pitch"));

            default -> throw new IllegalStateException("Unexpected value: " + playerClass.Team.getColor() + " spawn location");
        });
        playerClass.getPlayer().setGameMode(GameMode.SURVIVAL);
        if (debug) {
            Logger.log(playerClass.getName() + " has respawn");
        }

        playerClass.addarmor();
    }

    public RespawnTask(PlayerClass player) {
        this.playerClass = player;
    }
}
