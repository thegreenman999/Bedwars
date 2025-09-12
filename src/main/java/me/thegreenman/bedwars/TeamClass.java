package me.thegreenman.bedwars;


import me.thegreenman.bedwars.utils.Logger;
import static me.thegreenman.bedwars.Bedwars.gameConfig;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class TeamClass {

    private final String color;
    private Location spawnLoc;
    private final List<PlayerClass> players;
    private Color armorColor;

    private ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    private ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    private ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    private ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    private ItemStack sword = new ItemStack(Material.WOODEN_SWORD);

    public TeamClass(List<PlayerClass> players, String color) {
        this.players = players;
        for (PlayerClass playerClass : players) {
            playerClass.Team = color;
        }
        this.color = color;
        Logger.log("Team " + color + ": " + players.toString());

        int[] colorRGB = new int[]{255, 255, 255};
        switch (color.toLowerCase()) {
            case "green":
                colorRGB = parseHexColor(Bedwars.gameConfig.getString("GreenTeam.Armor-color"));
                break;
            case "pink":
                colorRGB = parseHexColor(Bedwars.gameConfig.getString("PinkTeam.Armor-color"));
                break;
            case "red":
                colorRGB = parseHexColor(Bedwars.gameConfig.getString("RedTeam.Armor-color"));

                break;
            case "blue":
                colorRGB = parseHexColor(Bedwars.gameConfig.getString("BlueTeam.Armor-color"));
                break;
            default:

        }
        armorColor = Color.fromRGB(colorRGB[0], colorRGB[1], colorRGB[2]);

        addarmor();
    }

    private void addarmor() {
        LeatherArmorMeta bootsItemMeta = (LeatherArmorMeta) boots.getItemMeta();
        bootsItemMeta.setColor(armorColor);
        boots.setItemMeta(bootsItemMeta);

        LeatherArmorMeta leggingsItemMeta = (LeatherArmorMeta) leggings.getItemMeta();
        leggingsItemMeta.setColor(armorColor);
        leggings.setItemMeta(leggingsItemMeta);

        LeatherArmorMeta chestplateItemMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplateItemMeta.setColor(armorColor);
        chestplate.setItemMeta(chestplateItemMeta);

        LeatherArmorMeta helmetItemMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetItemMeta.setColor(armorColor);
        helmetItemMeta.addEnchant(Enchantment.AQUA_AFFINITY, 1, true);
        helmet.setItemMeta(helmetItemMeta);




        for (PlayerClass player : players) {
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
