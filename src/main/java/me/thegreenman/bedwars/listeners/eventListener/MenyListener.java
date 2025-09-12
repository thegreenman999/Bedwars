package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.PlayerClass;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.thegreenman.bedwars.Bedwars.shopConfig;
import static me.thegreenman.bedwars.Menys.ShopMeny.*;

public class MenyListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());

        if (playerClass == null) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.isShiftClick()) {
            event.setCancelled(true);
        }


        ItemStack item = event.getCurrentItem();
        String title = event.getView().getTitle();
//        if (event.getClickedInventory().)
        if (!title.equals("Shop") && !title.equals("Blocks") && !title.equals("Utilitys") && !title.equals("Tools")) {
            return;
        }


//          ===================
//                Menys
//          ===================
        if (item.equals(quick)) {
            player.closeInventory();
            openShop(playerClass);
        }
        else if (item.equals(blocks)) {
            player.closeInventory();
            openBlocksMeny(playerClass);
        }
        else if (item.equals(tools)) {
            player.closeInventory();
            openToolsMeny(playerClass);
        }
        else if (item.equals(utilitys)) {
            player.closeInventory();
            openUtilityMeny(playerClass);
        }

//      =======================
//             Blocks
//      =======================
        else if (item.equals(wool)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.wool.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.wool.price"));

                player.getInventory().addItem(switch (playerClass.Team.toLowerCase()) {
                    case "green" -> new ItemStack(Material.GREEN_WOOL, shopConfig.getInt("Items.wool.amount"));
                    case "red" -> new ItemStack(Material.RED_WOOL, shopConfig.getInt("Items.wool.amount"));
                    case "pink" -> new ItemStack(Material.PINK_WOOL, shopConfig.getInt("Items.wool.amount"));
                    case "blue" -> new ItemStack(Material.BLUE_WOOL, shopConfig.getInt("Items.wool.amount"));
                    default -> throw new IllegalArgumentException("Shop wool IllegalArgumentException");
                });
            }

        }
        else if (item.equals(terracotta)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.terracotta.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.terracotta.price"));

                player.getInventory().addItem(switch (playerClass.Team.toLowerCase()) {
                    case "green" ->
                            new ItemStack(Material.GREEN_TERRACOTTA, shopConfig.getInt("Items.terracotta.amount"));
                    case "red" -> new ItemStack(Material.RED_TERRACOTTA, shopConfig.getInt("Items.terracotta.amount"));
                    case "pink" ->
                            new ItemStack(Material.PINK_TERRACOTTA, shopConfig.getInt("Items.terracotta.amount"));
                    case "blue" ->
                            new ItemStack(Material.BLUE_TERRACOTTA, shopConfig.getInt("Items.terracotta.amount"));
                    default -> throw new IllegalArgumentException("Shop terracotta IllegalArgumentException");
                });
            }

        }
        else if (item.equals(endstone)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.endstone.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.endstone.price"));

                player.getInventory().addItem(new ItemStack(Material.END_STONE, shopConfig.getInt("Items.endstone.amount")));

            }

        }
        else if (item.equals(wood)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.wood.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.wood.price"));

                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, shopConfig.getInt("Items.wood.amount")));

            }
        }
//      ==================
//            Armor
//      ==================
        else if (item.equals(chainmailLeg)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.chainmailleg.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.chainmailleg.price"));

                playerClass.setArmorLevel(2);
                playerClass.updateArmor();
                player.closeInventory();
                openShop(playerClass);
            }
        }
        else if (item.equals(ironleg)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.ironleg.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.ironleg.price"));

                playerClass.setArmorLevel(3);
                playerClass.updateArmor();
                player.closeInventory();
                openShop(playerClass);

            }
        }
        else if (item.equals(dialeg)) {
            if (player.getInventory().contains(Material.EMERALD, shopConfig.getInt("Items.dialeg.price"))) {

                removeItems(player, Material.EMERALD, shopConfig.getInt("Items.dialeg.price"));

                playerClass.setArmorLevel(4);
                playerClass.updateArmor();
                player.closeInventory();
                openShop(playerClass);
            }
        }
        else if (item.equals(woodaxe)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.woodaxe.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.woodaxe.price"));

                playerClass.addAxeLevel();
                playerClass.updateAxe();
                player.closeInventory();
                openShop(playerClass);
            }
        }


        event.setCancelled(true);

    }

    public static void removeItems(Player player, Material material, int amountToRemove) {
        ItemStack[] contents = player.getInventory().getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == material) {
                int itemAmount = item.getAmount();
                if (itemAmount <= amountToRemove) {
                    amountToRemove -= itemAmount;
                    player.getInventory().setItem(i, null);
                }
                else {
                    item.setAmount(itemAmount - amountToRemove);
                    amountToRemove = 0;
                    break;
                }
            }
        }

        player.updateInventory();
    }
}
