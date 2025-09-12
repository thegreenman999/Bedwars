package me.thegreenman.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerClass {
    private static List<PlayerClass> playerClasses = new ArrayList<>();

    // player objet
    private final Player player;

    private int Kills = 0;

    private int armorLevel = 1;
    private int pickaxeLevel = 0;
    private int axeLevel = 0;

    public String Team;

    public PlayerClass(Player player) {
        this.player = player;
        playerClasses.add(this);
    }


    public Object getUUID(Boolean string) {
        if (!string) {
            return player.getUniqueId();
        }
        else {
            return player.getUniqueId().toString();
        }
    }

    public void updateArmor() {
        switch (armorLevel) {
            case 2:
                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                break;
            case 3:
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                break;
            case 4:
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                break;
            default:

        }
    }

    public void updateAxe() {
        switch (axeLevel) {
            case 1:
                player.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                break;
        }
    }

    public void updatePickAxe() {
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return player.getName();
    }

    public int getKills() {
        return Kills;
    }


    public int getArmorLevel() {
        return armorLevel;
    }

    public void setArmorLevel(int armorLevel) {
        this.armorLevel = armorLevel;
    }

    public int getPickaxeLevel() {
        return pickaxeLevel;
    }

    public void addPickaxeLevel() {
        pickaxeLevel++;
    }
    public void subPickaxeLevel() {
        pickaxeLevel--;
    }

    public int getAxeLevel() {
        return axeLevel;
    }

    public void addAxeLevel() {
        axeLevel++;
    }
    public void subAxeLevel() {
        axeLevel--;
    }


    public static List<PlayerClass> toPlayerclass(List<Player> players) {
        List<PlayerClass> playerClasses = new ArrayList<>();

        for (Player player : players) {
            PlayerClass playerClass = new PlayerClass(player);
            playerClasses.add(playerClass);
        }

        return playerClasses;
    }

    public static PlayerClass findplayer(UUID playerUUID) {
        for (PlayerClass playerClass : playerClasses) {
            if (playerClass.getPlayer().getUniqueId().equals(playerUUID)) {
                return playerClass;
            }
        }
        return null;
    }

    public static void reset() {
        playerClasses = new ArrayList<>();
    }
}
