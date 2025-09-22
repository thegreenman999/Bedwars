package me.thegreenman.bedwars;

import me.thegreenman.bedwars.commands.AdminCommand;
import me.thegreenman.bedwars.commands.ResetCommand;
import me.thegreenman.bedwars.commands.StartCommand;
import me.thegreenman.bedwars.commands.reloadCommand;
import me.thegreenman.bedwars.listeners.eventListener.*;
import me.thegreenman.bedwars.menys.AdminMeny;
import me.thegreenman.bedwars.menys.ShopMeny;
import me.thegreenman.bedwars.tasks.ScorebordTask;
import me.thegreenman.bedwars.utils.Logger;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Bedwars extends JavaPlugin {

    public static Bedwars main = null;

    public static World world;
    public static Boolean debug;
    public static boolean gameOn = false;
    public List<Entity> spawnEntitys = new ArrayList<>();

    public BukkitTask scorebord = null;

    public static YamlConfiguration lang;
    public static YamlConfiguration config;
    public static YamlConfiguration gameConfig;
    public static YamlConfiguration lobbyConfig;
    public static YamlConfiguration shopConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // verabol declaration
        main = this;
        debug = this.getConfig().getBoolean("debug-mode");


        if (this.getConfig().getString("world-name") == null) {
            world = Bukkit.getWorld("world");
        }
        else {
            world = Bukkit.getWorld(this.getConfig().getString("world-name"));
        }


        // saving configs
        saveResource("game.yml", true);
        saveResource("config.yml", true);
        saveResource("lobby.yml", true);

        saveResource("shop.yml", true);

        if (this.getConfig().getString("lang") == null) {
            this.getLogger().severe("Config.lang is empty defaulting to en_us");
            this.getConfig().set("lang", "en_us");
        }
        else {
            saveResource("lang/" + this.getConfig().getString("lang") + ".yml", true);
        }

        saveResource("lang/en_us.yml", true);

        // load language config
        File file = new File(this.getDataFolder(), "lang/" + this.getConfig().getString("lang") + ".yml");
        lang = YamlConfiguration.loadConfiguration(file);

        // load lobby config
        File file1 = new File(this.getDataFolder(), "lobby.yml");
        lobbyConfig = YamlConfiguration.loadConfiguration(file1);

        // load game config
        File file2 = new File(this.getDataFolder(), "game.yml");
        gameConfig = YamlConfiguration.loadConfiguration(file2);

        // load config
        File file3 = new File(main.getDataFolder(), "shop.yml");
        shopConfig = YamlConfiguration.loadConfiguration(file3);

        // load config
        config = (YamlConfiguration) this.getConfig();




        // setups Classes
        new Logger(this.getName());
        new ShopMeny();


        // start command
        getCommand("start").setExecutor(new StartCommand());

        // Admin meny
        getCommand("adminmeny").setExecutor(new AdminCommand());
        getServer().getPluginManager().registerEvents(new AdminMeny(), this);

        // reload command
        getCommand("reload").setExecutor(new reloadCommand());

        // reset arena command
        getCommand("reset").setExecutor(new ResetCommand());
        getServer().getPluginManager().registerEvents(new ResetCommand(), this);


        // event listerners
        getServer().getPluginManager().registerEvents(new BedBreakListener(), this);

        getServer().getPluginManager().registerEvents(new deathListener(), this);

        getServer().getPluginManager().registerEvents(new fireBallListener(), this);

        getServer().getPluginManager().registerEvents(new InteractAtEntityEventListener(), this);

        getServer().getPluginManager().registerEvents(new MenyListener(), this);

        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
    }

    @Override
    public void onDisable() {
        reset();
        ResetCommand.reset();

        if (scorebord != null && !scorebord.isCancelled()) {
            scorebord.cancel();
        }
    }


    public void reset() {
        gameOn = false;

        PlayerClass.reset();

        spawnEntitys.forEach(Entity::remove);

        removeBeds(new Location(world, gameConfig.getInt("PinkTeam.Bed-loc.x"), gameConfig.getInt("PinkTeam.Bed-loc.y"), gameConfig.getInt("PinkTeam.Bed-loc.z")), gameConfig.getString("PinkTeam.Bed-loc.facing"));
        removeBeds(new Location(world, gameConfig.getInt("GreenTeam.Bed-loc.x"), gameConfig.getInt("GreenTeam.Bed-loc.y"), gameConfig.getInt("GreenTeam.Bed-loc.z")), gameConfig.getString("GreenTeam.Bed-loc.facing"));
        removeBeds(new Location(world, gameConfig.getInt("RedTeam.Bed-loc.x"), gameConfig.getInt("RedTeam.Bed-loc.y"), gameConfig.getInt("RedTeam.Bed-loc.z")), gameConfig.getString("RedTeam.Bed-loc.facing"));
        removeBeds(new Location(world, gameConfig.getInt("BlueTeam.Bed-loc.x"), gameConfig.getInt("BlueTeam.Bed-loc.y"), gameConfig.getInt("BlueTeam.Bed-loc.z")), gameConfig.getString("BlueTeam.Bed-loc.facing"));
    }

    public static void removeBeds(Location location, String facing) {
        if (facing == null) {
            Logger.log("Gameconfig has unset optinons");
            return;
        }
        location.getBlock().setType(Material.AIR);
        switch (facing.toLowerCase()) {
            case "south":
                location.add(0,0,1);
            case "north":
                location.add(0,0,-1);
            case "west":
                location.add(-1,0,0);
            case "east":
                location.add(1,0,0);
        }
        location.getBlock().setType(Material.AIR);
    }

    public void reload() {
        // load language config
        File file = new File(this.getDataFolder(), this.getConfig().getString("lang") + ".yml");
        lang = YamlConfiguration.loadConfiguration(file);

        // lobby config file
        File file1 = new File(this.getDataFolder(), "lobby.yml");
        lobbyConfig = YamlConfiguration.loadConfiguration(file1);

        // load game config
        File file2 = new File(this.getDataFolder(), "game.yml");
        gameConfig = YamlConfiguration.loadConfiguration(file2);

        // load config
        config = (YamlConfiguration) this.getConfig();
    }

    public void startScorebord() {
        scorebord = this.getServer().getScheduler().runTaskTimer(this, ScorebordTask.getInstace(), 0L, 1L);
    }
}
