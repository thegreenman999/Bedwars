package me.thegreenman.bedwars;

import me.thegreenman.bedwars.utils.Logger;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

import static me.thegreenman.bedwars.Bedwars.*;


public class GameStart {

    public static TeamClass TeamRed = null;
    public static TeamClass TeamBlue = null;
    public static TeamClass TeamPink = null;
    public static TeamClass TeamGreen = null;

    public static ItemRepeatSpawning itemRepeatSpawning = null;
    

    public GameStart() {

        // green Team
        List<Player> greenTeamPlayers = getPlayersInBox(
                new Location(world, lobbyConfig.getInt("GreenTeam.pos1.x"), lobbyConfig.getInt("GreenTeam.pos1.y"), lobbyConfig.getInt("GreenTeam.pos1.z")),
                new Location(world, lobbyConfig.getInt("GreenTeam.pos2.x"), lobbyConfig.getInt("GreenTeam.pos2.y"), lobbyConfig.getInt("GreenTeam.pos2.z"))
        );

        if (!greenTeamPlayers.isEmpty()) {
            TeamGreen = new TeamClass(PlayerClass.toPlayerclass(greenTeamPlayers), "Green");

            placeBed(new Location(world, gameConfig.getInt("GreenTeam.Bed-loc.x"), gameConfig.getInt("GreenTeam.Bed-loc.y"), gameConfig.getInt("GreenTeam.Bed-loc.z")),
                    BlockFace.valueOf(gameConfig.getString("GreenTeam.Bed-loc.facing").toUpperCase()),
                    Material.GREEN_BED
            );
            if (Bedwars.debug){
                Logger.log("Green Bed");
            }
        }

        // red Team
        List<Player> redTeamPlayers = getPlayersInBox(
                new Location(world, lobbyConfig.getInt("RedTeam.pos1.x"), lobbyConfig.getInt("RedTeam.pos1.y"), lobbyConfig.getInt("RedTeam.pos1.z")),
                new Location(world, lobbyConfig.getInt("RedTeam.pos2.x"), lobbyConfig.getInt("RedTeam.pos2.y"), lobbyConfig.getInt("RedTeam.pos2.z"))
        );

        if (!redTeamPlayers.isEmpty()) {
            TeamRed = new TeamClass(PlayerClass.toPlayerclass(redTeamPlayers), "Red");

            placeBed(new Location(world, gameConfig.getInt("RedTeam.Bed-loc.x"), gameConfig.getInt("RedTeam.Bed-loc.y"), gameConfig.getInt("RedTeam.Bed-loc.z")),
                    BlockFace.valueOf(gameConfig.getString("RedTeam.Bed-loc.facing").toUpperCase()),
                    Material.RED_BED
            );
            if (Bedwars.debug){
                Logger.log("Red Bed");
            }
        }

        // blue Team
        List<Player> blueTeamPlayers = getPlayersInBox(
                new Location(world, lobbyConfig.getInt("BlueTeam.pos1.x"), lobbyConfig.getInt("BlueTeam.pos1.y"), lobbyConfig.getInt("BlueTeam.pos1.z")),
                new Location(world, lobbyConfig.getInt("BlueTeam.pos2.x"), lobbyConfig.getInt("BlueTeam.pos2.y"), lobbyConfig.getInt("BlueTeam.pos2.z"))
        );

        if (!blueTeamPlayers.isEmpty()) {
            TeamBlue = new TeamClass(PlayerClass.toPlayerclass(blueTeamPlayers), "Blue");

            placeBed(new Location(world, gameConfig.getInt("BlueTeam.Bed-loc.x"), gameConfig.getInt("BlueTeam.Bed-loc.y"), gameConfig.getInt("BlueTeam.Bed-loc.z")),
                    BlockFace.valueOf(gameConfig.getString("BlueTeam.Bed-loc.facing").toUpperCase()),
                    Material.BLUE_BED
            );
            if (Bedwars.debug){
                Logger.log("Blue Bed");
            }
        }

        // pink Team
        List<Player> pinkTeamPlayers = getPlayersInBox(
                new Location(world, lobbyConfig.getInt("PinkTeam.pos1.x"), lobbyConfig.getInt("PinkTeam.pos1.y"), lobbyConfig.getInt("PinkTeam.pos1.z")),
                new Location(world, lobbyConfig.getInt("PinkTeam.pos2.x"), lobbyConfig.getInt("PinkTeam.pos2.y"), lobbyConfig.getInt("PinkTeam.pos2.z"))
        );

        if (!pinkTeamPlayers.isEmpty()) {
            TeamPink = new TeamClass(PlayerClass.toPlayerclass(pinkTeamPlayers), "Pink");

            placeBed(new Location(world, gameConfig.getInt("PinkTeam.Bed-loc.x"), gameConfig.getInt("PinkTeam.Bed-loc.y"), gameConfig.getInt("PinkTeam.Bed-loc.z")),
                    BlockFace.valueOf(gameConfig.getString("PinkTeam.Bed-loc.facing").toUpperCase()),
                    Material.PINK_BED
            );
            if (Bedwars.debug){
                Logger.log("Pink Bed");
            }
        }

        gameOn = true;

        spawnVillagers();
        main.startScorebord();

        itemRepeatSpawning = new ItemRepeatSpawning();

        if (lang.getString("Game-start-massage") == null) {
            main.getLogger().severe("language file has a empty part called Game-start-massage");
        }
        else {
            Bukkit.broadcastMessage(lang.getString("Game-start-massage"));
        }

        main.getServer().getScheduler().runTaskLater(main,
                bukkitTask -> {
                    tpTeam(greenTeamPlayers, new Location(world,
                            gameConfig.getDouble("GreenTeam.spawn-loc.x"),
                            gameConfig.getDouble("GreenTeam.spawn-loc.y"),
                            gameConfig.getDouble("GreenTeam.spawn-loc.z"),
                            (float) gameConfig.getDouble("GreenTeam.spawn-loc.yaw"),
                            (float) gameConfig.getDouble("GreenTeam.spawn-loc.pitch"))
                    );
                    tpTeam(redTeamPlayers, new Location(world,
                            gameConfig.getDouble("RedTeam.spawn-loc.x"),
                            gameConfig.getDouble("RedTeam.spawn-loc.y"),
                            gameConfig.getDouble("RedTeam.spawn-loc.z"),
                            (float) gameConfig.getDouble("RedTeam.spawn-loc.yaw"),
                            (float) gameConfig.getDouble("RedTeam.spawn-loc.pitch"))
                    );
                    tpTeam(blueTeamPlayers, new Location(world,
                            gameConfig.getInt("BlueTeam.spawn-loc.x"),
                            gameConfig.getInt("BlueTeam.spawn-loc.y"),
                            gameConfig.getInt("BlueTeam.spawn-loc.z"),
                            gameConfig.getInt("BlueTeam.spawn-loc.yaw"),
                            gameConfig.getInt("BlueTeam.spawn-loc.pitch"))
                    );
                    tpTeam(pinkTeamPlayers, new Location(world,
                            gameConfig.getInt("PinkTeam.spawn-loc.x"),
                            gameConfig.getInt("PinkTeam.spawn-loc.y"),
                            gameConfig.getInt("PinkTeam.spawn-loc.z"),
                            gameConfig.getInt("PinkTeam.spawn-loc.yaw"),
                            gameConfig.getInt("PinkTeam.spawn-loc.pitch"))
                    );
                }, 20L);


        for (Player player : Bukkit.getOnlinePlayers()) {
            if (blueTeamPlayers.contains(player) || redTeamPlayers.contains(player) || greenTeamPlayers.contains(player) || pinkTeamPlayers.contains(player)) {
                player.setGameMode(GameMode.SURVIVAL);
                player.setHealth(20.0);
                player.setSaturation(20);
            }
            else {
                player.setGameMode(GameMode.SPECTATOR);
            }
        }


    }

    private void tpTeam(List<Player> team, Location location) {
        for (Player player : team) {
            player.teleport(location);
        }
    }

    public static void placeBed(Location loc, BlockFace facing, Material bedType) {
        if (!bedType.name().endsWith("_BED")) {
            throw new IllegalArgumentException("Material måste vara en säng, t.ex. RED_BED.");
        }

        // Hämta huvudblocket
        Block headBlock = world.getBlockAt(loc);
        headBlock.setType(bedType);

        // Blockdata för huvudändan
        Bed headData = (Bed) headBlock.getBlockData();
        headData.setPart(Bed.Part.HEAD);
        headData.setFacing(facing);
        headBlock.setBlockData(headData, true);

        // Hämta fotänden (bakåt från riktningen)
        Block footBlock = headBlock.getRelative(facing.getOppositeFace());
        footBlock.setType(bedType);

        Bed footData = (Bed) footBlock.getBlockData();
        footData.setPart(Bed.Part.FOOT);
        footData.setFacing(facing);
        footBlock.setBlockData(footData, true);
    }

    public static List<Player> getPlayersInBox(Location pos1 , Location pos2) {


        int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
        int minY = Math.min(pos1.getBlockY(), pos2.getBlockY());
        int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());
        int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
        int maxY = Math.max(pos1.getBlockY(), pos2.getBlockY());
        int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

        Location location = new Location(world, 0,0,0);

        List<Player> players = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (Bedwars.debug) {
                Bukkit.broadcastMessage("debug 1 " + player.getLocation());
                Bukkit.broadcastMessage("debug 0 " + minX + "  " + minY + "  " + minZ);
                Bukkit.broadcastMessage("debug 0.5 " + maxX + "  " + maxY + "  " + maxZ);
            }
            for (int X = minX; X <= maxX; X++) {
                if (player.getLocation().getBlockX() == X) {
                    if (Bedwars.debug) {
                        location.setX(X);
                        Bukkit.broadcastMessage("debug 2");
                    }
                    for (int Y = minY; Y <= maxY; Y++) {
                        if (player.getLocation().getBlockY() == Y) {
                            if (Bedwars.debug) {
                                location.setY(Y);
                                Bukkit.broadcastMessage("debug 3");
                            }
                            for (int Z = minZ; Z <= maxZ; Z++) {
                                if (Bedwars.debug) {
                                    location.setZ(Z);
                                    Bukkit.broadcastMessage("debug 4 " + location);
                                }
                                if (player.getLocation().getBlockZ() == Z) {
                                    if (Bedwars.debug){
                                        Bukkit.broadcastMessage("debug 5");
                                    }
                                    players.add(player);
                                }
                            }
                        }
                    }

                }
            }
        }


        return players;
    }

    private void spawnVillagers() {


        spawnVillager(world, new Location(world,
                        gameConfig.getDouble("GreenTeam.shop-loc.x"),
                        gameConfig.getDouble("GreenTeam.shop-loc.y"),
                        gameConfig.getDouble("GreenTeam.shop-loc.z"),
                        (float) gameConfig.getDouble("GreenTeam.shop-loc.yaw"),
                        (float) gameConfig.getDouble("GreenTeam.shop-loc.pitch")),
                Villager.Type.PLAINS, "Shop");
        spawnVillager(world, new Location(world,
                        gameConfig.getDouble("RedTeam.shop-loc.x"),
                        gameConfig.getDouble("RedTeam.shop-loc.y"),
                        gameConfig.getDouble("RedTeam.shop-loc.z"),
                        (float) gameConfig.getDouble("RedTeam.shop-loc.yaw"),
                        (float) gameConfig.getDouble("RedTeam.shop-loc.pitch")),
                Villager.Type.PLAINS, "Shop");
        spawnVillager(world, new Location(world,
                        gameConfig.getDouble("PinkTeam.shop-loc.x"),
                        gameConfig.getDouble("PinkTeam.shop-loc.y"),
                        gameConfig.getDouble("PinkTeam.shop-loc.z"),
                        (float) gameConfig.getDouble("PinkTeam.shop-loc.yaw"),
                        (float) gameConfig.getDouble("PinkTeam.shop-loc.pitch")),
                Villager.Type.PLAINS, "Shop");
        spawnVillager(world, new Location(world,
                        gameConfig.getDouble("BlueTeam.shop-loc.x"),
                        gameConfig.getDouble("BlueTeam.shop-loc.y"),
                        gameConfig.getDouble("BlueTeam.shop-loc.z"),
                        (float) gameConfig.getDouble("BlueTeam.shop-loc.yaw"),
                        (float) gameConfig.getDouble("BlueTeam.shop-loc.pitch")),
                Villager.Type.PLAINS, "Shop");

    }

    public void spawnVillager(World world, Location location, Villager.Type type, String name) {

        if (debug){
            Bukkit.broadcastMessage("Villager has spawn on " + location + " " + name);
        }
        Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
        villager.setGravity(false);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.setVillagerType(type);
        villager.setCustomName(name);
        villager.setCustomNameVisible(false);
        main.spawnEntitys.add(villager);
    }
}
