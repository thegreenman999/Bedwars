package me.thegreenman.bedwars;


import me.thegreenman.bedwars.utils.Logger;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class TeamClass {

    private final String color;
    private Location spawnLoc;
    private final List<PlayerClass> players;
    public Color armorColor;

    private Boolean bed = true;

    public ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    public ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    public ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    public ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    private ItemStack sword = new ItemStack(Material.WOODEN_SWORD);

    public TeamClass(List<PlayerClass> players, String color) {
        this.players = players;

        for (PlayerClass playerClass : players) {
            playerClass.Team = this;
        }

        this.color = color;
        Logger.log("Team " + color + ": " + players.toString());

        int[] colorRGB = new int[]{255, 255, 255};
        colorRGB = switch (color.toLowerCase()) {
            case "green" -> parseHexColor(Bedwars.gameConfig.getString("GreenTeam.Armor-color"));
            case "pink" -> parseHexColor(Bedwars.gameConfig.getString("PinkTeam.Armor-color"));
            case "red" -> parseHexColor(Bedwars.gameConfig.getString("RedTeam.Armor-color"));
            case "blue" -> parseHexColor(Bedwars.gameConfig.getString("BlueTeam.Armor-color"));
            default -> colorRGB;
        };

        armorColor = Color.fromRGB(colorRGB[0], colorRGB[1], colorRGB[2]);

        // boots
        LeatherArmorMeta bootsItemMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsItemMeta.setColor(armorColor);
        boots.setItemMeta(bootsItemMeta);

        // leggings
        LeatherArmorMeta leggingsItemMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsItemMeta.setColor(armorColor);
        leggings.setItemMeta(leggingsItemMeta);

        // chestplate
        LeatherArmorMeta chestplateItemMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateItemMeta.setColor(armorColor);
        chestplate.setItemMeta(chestplateItemMeta);

        // helmet
        LeatherArmorMeta helmetItemMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetItemMeta.setColor(armorColor);
        helmetItemMeta.addEnchant(Enchantment.AQUA_AFFINITY, 1, true);
        helmet.setItemMeta(helmetItemMeta);

        addarmor();
    }

    private void addarmor() {
        for (PlayerClass player : players) {
            player.getPlayer().getInventory().clear();
            player.getPlayer().getInventory().setBoots(boots);
            player.getPlayer().getInventory().setLeggings(leggings);
            player.getPlayer().getInventory().setChestplate(chestplate);
            player.getPlayer().getInventory().setHelmet(helmet);
            player.getPlayer().getInventory().addItem(sword);
        }
    }

    public List<PlayerClass> getPlayers() {
        return players;
    }

    public String getColor() {
        return color;
    }

    public boolean getBed() {
        return bed;
    }

    public void removeBed() {
        bed = false;
    }

    public void removePlaye(PlayerClass playerClass) {
        players.remove(playerClass);
    }

    private static int[] parseHexColor(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("Color string is null.");
        }

        String s = hex.trim();
        if (s.startsWith("#")) s = s.substring(1);

        // Validate characters
        if (!s.matches("(?i)^[0-9a-f]{3}([0-9a-f]{3})?$")) {
            throw new IllegalArgumentException("Invalid hex color: " + hex);
        }

        // Expand 3-digit shorthand to 6-digit
        if (s.length() == 3) {
            s = new StringBuilder()
                    .append(s.charAt(0)).append(s.charAt(0))
                    .append(s.charAt(1)).append(s.charAt(1))
                    .append(s.charAt(2)).append(s.charAt(2))
                    .toString();
        }

        int r = Integer.parseInt(s.substring(0, 2), 16);
        int g = Integer.parseInt(s.substring(2, 4), 16);
        int b = Integer.parseInt(s.substring(4, 6), 16);

        return new int[]{r, g, b};
    }

}