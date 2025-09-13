package me.thegreenman.bedwars;

import me.thegreenman.bedwars.utils.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static me.thegreenman.bedwars.Bedwars.*;


public class ItemRepeatSpawning implements Listener {
    public ItemStack iron = new ItemStack(Material.IRON_INGOT, 1);
    public ItemStack gold = new ItemStack(Material.GOLD_INGOT, 1);
    public ItemStack emerald = new ItemStack(Material.EMERALD, 1);

    public Location redItemSpawnLoc = null;
    public Location greenItemSpawnLoc = null;
    public Location blueItemSpawnLoc = null;
    public Location pinkItemSpawnLoc = null;
    public Location emeraldItemSpawnLoc = null;
    public Location emerald2ItemSpawnLoc = null;

    public BukkitTask SpawnIron = null;
    public BukkitTask SpawnGold = null;
    public BukkitTask SpawnEmerald = null;


    public ItemRepeatSpawning() {
        // Fördröjning för att säkerställa att världen är laddad
        Bukkit.getScheduler().runTaskLater(main, this::startSpawnIron, 20L);
        Bukkit.getScheduler().runTaskLater(main, this::startSpawnGold, 20L);
        Bukkit.getScheduler().runTaskLater(main, this::startSpawnEmerald, 20L);

        redItemSpawnLoc = new Location (world, gameConfig.getDouble("RedTeam.ore-gen-loc.x"), gameConfig.getDouble("RedTeam.ore-gen-loc.y"), gameConfig.getDouble("RedTeam.ore-gen-loc.z"));
        greenItemSpawnLoc = new Location (world, gameConfig.getDouble("GreenTeam.ore-gen-loc.x"), gameConfig.getDouble("GreenTeam.ore-gen-loc.y"), gameConfig.getDouble("GreenTeam.ore-gen-loc.z"));
        blueItemSpawnLoc = new Location (world, gameConfig.getDouble("BlueTeam.ore-gen-loc.x"), gameConfig.getDouble("BlueTeam.ore-gen-loc.y"), gameConfig.getDouble("BlueTeam.ore-gen-loc.z"));
        pinkItemSpawnLoc = new Location (world, gameConfig.getDouble("PinkTeam.ore-gen-loc.x"), gameConfig.getDouble("PinkTeam.ore-gen-loc.y"), gameConfig.getDouble("PinkTeam.ore-gen-loc.z"));
        emeraldItemSpawnLoc = new Location (world, gameConfig.getDouble("middle.ore-gen1-loc.x"), gameConfig.getDouble("middle.ore-gen1-loc.y"), gameConfig.getDouble("middle.ore-gen1-loc.z"));
        emerald2ItemSpawnLoc = new Location (world, gameConfig.getDouble("middle.ore-gen2-loc.x"), gameConfig.getDouble("middle.ore-gen2-loc.y"), gameConfig.getDouble("middle.ore-gen2-loc.z"));
    }


    public void startSpawnIron () {
        SpawnIron = new BukkitRunnable() {
            @Override
            public void run() {
                if (!checkSurvivalPlayers()) { return; }
                spawnItemAtLocation(redItemSpawnLoc, iron);
                spawnItemAtLocation(greenItemSpawnLoc, iron);
                spawnItemAtLocation(blueItemSpawnLoc, iron);
                spawnItemAtLocation(pinkItemSpawnLoc, iron);
            }
        }.runTaskTimer(main, 100L, 20 * 15); // tick * seconds
    }

    public void startSpawnGold () {
        SpawnGold = new BukkitRunnable() {
            @Override
            public void run() {
                if (!checkSurvivalPlayers()) { return; }
                spawnItemAtLocation(redItemSpawnLoc, gold);
                spawnItemAtLocation(greenItemSpawnLoc, gold);
                spawnItemAtLocation(blueItemSpawnLoc, gold);
                spawnItemAtLocation(pinkItemSpawnLoc, gold);
            }
        }.runTaskTimer(main, 100L, 20 * 45); // tick * seconds
    }

    public void startSpawnEmerald () {
        SpawnEmerald = new BukkitRunnable() {
            @Override
            public void run() {
                if (!checkSurvivalPlayers()) { return; }
                spawnItemAtLocation(emeraldItemSpawnLoc, emerald);
                spawnItemAtLocation(emerald2ItemSpawnLoc, emerald);
            }
        }.runTaskTimer(main, 100L, 20 * 60); // tick * seconds // 60 seconds
    }


    public void spawnItemAtLocation(Location location, ItemStack itemStack) {
        World world = location.getWorld();
        if (world != null) {
            world.dropItemNaturally(location, itemStack);
        } else {
            Logger.log("Invalid world for the specified location.");
        }
    }

    public static boolean checkSurvivalPlayers() {
//		Main.log("[PVPBM] Bukkit.getOnlinePlayers().size() = " + Bukkit.getOnlinePlayers().size());
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            return false;
        }
        for (Player pl : Bukkit.getOnlinePlayers())	{
            if (pl.getGameMode().equals(GameMode.SURVIVAL)) {
//				Main.log("[PVPBM] Player=" + pl.getName() + " has GM=" + pl.getGameMode());
                return true;
            }
        }
        return false;
    }

}