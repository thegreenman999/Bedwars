package me.thegreenman.bedwars.listeners.eventListener;

import me.thegreenman.bedwars.PlayerClass;
import org.bukkit.Material;
import org.bukkit.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static me.thegreenman.bedwars.Bedwars.debug;
import static me.thegreenman.bedwars.Bedwars.shopConfig;
import static me.thegreenman.bedwars.menys.ShopMeny.*;

public class MenyListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        String title = event.getView().getTitle();
        if (!title.equals("Shop") && !title.equals("Blocks") && !title.equals("Utilitys") && !title.equals("Tools") && !title.equals("Weapons")) {
            return;
        }

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
//        if (event.getClickedInventory().)



//          ===================
//                Menys
//          ===================
        if (item.equals(quick)) {
            openShop(playerClass, true);
        }
        else if (item.equals(blocks)) {
            openBlocksMeny(playerClass);
        }
        else if (item.equals(tools)) {
            openToolsMeny(playerClass);
        }
        else if (item.equals(utilitys)) {
            openUtilityMeny(playerClass);
        }
        else if (item.equals(weapens)) {
            openWeaponsMeny(playerClass);
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

                openShop(playerClass, true);
            }
        }
        else if (item.equals(ironleg)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.ironleg.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.ironleg.price"));

                playerClass.setArmorLevel(3);
                playerClass.updateArmor();


                openShop(playerClass, true);

            }
        }
        else if (item.equals(dialeg)) {
            if (player.getInventory().contains(Material.EMERALD, shopConfig.getInt("Items.dialeg.price"))) {

                removeItems(player, Material.EMERALD, shopConfig.getInt("Items.dialeg.price"));

                playerClass.setArmorLevel(4);
                playerClass.updateArmor();

                openShop(playerClass, true);
            }
        }
//        =============
//            Axes
//        =============
        else if (item.equals(woodaxe)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.woodAxe.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.woodAxe.price"));

                playerClass.addAxeLevel();
                playerClass.updateAxe();

                openShop(playerClass, true);
            }
        }
        else if (item.equals(ironaxe)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.ironAxe.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.ironAxe.price"));

                playerClass.addAxeLevel();
                playerClass.updateAxe();

                openShop(playerClass, true);

            }
        }
        else if (item.equals(diaaxe)) {
            if (player.getInventory().contains(Material.EMERALD, shopConfig.getInt("Items.diaAxe.price"))) {

                removeItems(player, Material.EMERALD, shopConfig.getInt("Items.diaAxe.price"));

                playerClass.addAxeLevel();
                playerClass.updateAxe();

                openShop(playerClass, true);
            }
        }
//        ===============
//            pickaxes
//        ===============
        else if (item.equals(woodPick)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.woodPick.price"))) {


                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.woodPick.price"));

                playerClass.addPickaxeLevel();
                playerClass.updatePickAxe();

                openShop(playerClass, true);
            }
        }
        else if (item.equals(ironPick)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.ironPick.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.ironPick.price"));

                playerClass.addPickaxeLevel();
                playerClass.updatePickAxe();


                openShop(playerClass, true);

            }
        }
        else if (item.equals(diaPick)) {
            if (player.getInventory().contains(Material.EMERALD, shopConfig.getInt("Items.diaPick.price"))) {

                removeItems(player, Material.EMERALD, shopConfig.getInt("Items.diaPick.price"));

                playerClass.addPickaxeLevel();
                playerClass.updatePickAxe();

                openShop(playerClass, true);
            }
        }
//        ===============
//             Sword
//        ===============
        else if (item.equals(stonesword)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.stoneSword.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.stoneSword.price"));

                player.getInventory().remove(Material.WOODEN_SWORD);
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));

            }
        }
        else if (item.equals(ironsword)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.ironSword.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.ironSword.price"));

                player.getInventory().remove(Material.STONE_SWORD);
                player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

            }
        }
        else if (item.equals(diasword)) {
            if (player.getInventory().contains(Material.EMERALD, shopConfig.getInt("Items.diaSword.price"))) {

                removeItems(player, Material.EMERALD, shopConfig.getInt("Items.diaSword.price"));

                player.getInventory().remove(Material.IRON_SWORD);
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));

            }
        }
//        =====================
//              Utilitys
//        =====================
        else if (item.equals(shears)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.shears.price")) || !player.getInventory().contains(Material.SHEARS)) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.shears.price"));

                player.getInventory().addItem(new ItemStack(Material.SHEARS));

            }
        }
        else if (item.equals(fireCharge)) {
            if (player.getInventory().contains(Material.IRON_INGOT, shopConfig.getInt("Items.fireCharge.price"))) {

                removeItems(player, Material.IRON_INGOT, shopConfig.getInt("Items.fireCharge.price"));

                player.getInventory().addItem(new ItemStack(Material.FIRE_CHARGE));
            }
        }
        else if (item.equals(gapple)) {
            if (player.getInventory().contains(Material.GOLD_INGOT, shopConfig.getInt("Items.goldApple.price"))) {

                removeItems(player, Material.GOLD_INGOT, shopConfig.getInt("Items.goldApple.price"));

                player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
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
