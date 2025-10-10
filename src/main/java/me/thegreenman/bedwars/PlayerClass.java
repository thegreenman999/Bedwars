package me.thegreenman.bedwars;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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

    public TeamClass Team;

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
            case 2:
                player.getInventory().remove(new ItemStack(Material.WOODEN_AXE));
                player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                break;
            case 3:
                player.getInventory().remove(new ItemStack(Material.IRON_AXE));
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
                break;
        }
    }

    public void updatePickAxe() {
        switch (pickaxeLevel) {
            case 1:
                player.getInventory().addItem(new ItemStack(Material.WOODEN_PICKAXE));
                break;
            case 2:
                player.getInventory().remove(new ItemStack(Material.WOODEN_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                break;
            case 3:
                player.getInventory().remove(new ItemStack(Material.IRON_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                break;
        }
    }

    public void addarmor() {
        player.getInventory().setChestplate(Team.chestplate);
        player.getInventory().setHelmet(Team.helmet);

        if (getArmorLevel() != 1) {
            updateArmor();
        }
        else {
            player.getInventory().setBoots(Team.boots);
            player.getInventory().setLeggings(Team.leggings);
        }

    }

    public void addtools() {
        switch (pickaxeLevel) {
            case 1:
                player.getInventory().addItem(new ItemStack(Material.WOODEN_PICKAXE));
                break;
            case 2:
                player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                break;
            case 3:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                break;
        }
        switch (axeLevel) {
            case 1:
                player.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                break;
            case 2:
                player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                break;
            case 3:
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
                break;
        }
        player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
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

    public void addKills() {
        Kills++;
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
        if (pickaxeLevel > 0){
            pickaxeLevel--;
        }
    }

    public int getAxeLevel() {
        return axeLevel;
    }

    public void addAxeLevel() {
        axeLevel++;
    }
    public void subAxeLevel() {
        if (axeLevel > 0){
            axeLevel--;
        }
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

    public static PlayerClass findplayer(String playerName) {
        for (PlayerClass playerClass : playerClasses) {
            if (playerClass.getPlayer().getName().equals(playerName)) {
                return playerClass;
            }
        }
        return null;
    }

    public static void reset() {
        playerClasses = new ArrayList<>();
    }


}
