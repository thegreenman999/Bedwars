package me.thegreenman.bedwars.menys;

import me.thegreenman.bedwars.PlayerClass;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Objects;

import static me.thegreenman.bedwars.Bedwars.*;

public class ShopMeny {

    // blocks
    public static ItemStack wool = new ItemStack(Material.WHITE_WOOL, shopConfig.getInt("Items.wool.amount"));
    public static ItemStack terracotta = new ItemStack(Material.TERRACOTTA, shopConfig.getInt("Items.terracotta.amount"));
    public static ItemStack endstone = new ItemStack(Material.END_STONE, shopConfig.getInt("Items.endstone.amount"));
    public static ItemStack wood = new ItemStack(Material.OAK_PLANKS, shopConfig.getInt("Items.wood.amount"));

    // swords
    public static ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
    public static ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
    public static ItemStack diaesword = new ItemStack(Material.DIAMOND_SWORD);

    // leggings
    public static ItemStack chainmailLeg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    public static ItemStack ironleg = new ItemStack(Material.IRON_LEGGINGS);
    public static ItemStack dialeg = new ItemStack(Material.DIAMOND_LEGGINGS);

    // Pickas
    public static ItemStack woodPick = new ItemStack(Material.WOODEN_PICKAXE);
    public static ItemStack ironPick = new ItemStack(Material.IRON_PICKAXE);
    public static ItemStack diaPick = new ItemStack(Material.DIAMOND_PICKAXE);

    // axes
    public static ItemStack woodaxe = new ItemStack(Material.WOODEN_AXE);
    public static ItemStack ironaxe = new ItemStack(Material.IRON_AXE);
    public static ItemStack diaaxe = new ItemStack(Material.DIAMOND_AXE);

    public static ItemStack bow = new ItemStack(Material.BOW);
    public static ItemStack arrows = new ItemStack(Material.ARROW, 6);

    // Utilitys
    public static ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
    public static ItemStack charge = new ItemStack(Material.FIRE_CHARGE);
    public static ItemStack tnt = new ItemStack(Material.TNT);
    public static ItemStack shears = new ItemStack(Material.SHEARS);
    public static ItemStack ender = new ItemStack(Material.ENDER_PEARL);
    public static ItemStack water = new ItemStack(Material.WATER_BUCKET);

    // meny Items
    public static ItemStack quick = new ItemStack(Material.NETHER_STAR);
    public static ItemStack blocks = new ItemStack(Material.TERRACOTTA);
    public static ItemStack tools = new ItemStack(Material.STONE_PICKAXE);
    public static ItemStack utilitys = new ItemStack(Material.TNT);

    //potions
    public static ItemStack invsee = new ItemStack(Material.POTION);
    public static ItemStack jump = new ItemStack(Material.POTION);


    public static void openShop(PlayerClass player) {
        Inventory meny = Bukkit.createInventory(player.getPlayer(), 6*9, "Shop");

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);

        // Glass panes
        meny.setItem(9, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        meny.setItem(10, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(11, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(12, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(14, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));


        // blocks
        meny.setItem(19, wool);
        meny.setItem(28, terracotta);
        meny.setItem(37, endstone);

        // Swords
        meny.setItem(20, stonesword);
        meny.setItem(29, ironsword);
        meny.setItem(38, diaesword);

        if (config.getBoolean("armor-leveling")) {
            meny.setItem(21, switch (player.getArmorLevel()) {
                case 1 -> chainmailLeg;
                case 2 -> ironleg;
                case 3 -> dialeg;
                case 4 -> null;
                default -> throw new IllegalStateException("Unexpected value: " + player.getArmorLevel());
            });
        }
        else {
            meny.setItem(21, chainmailLeg);
            meny.setItem(30, ironleg);
            meny.setItem(39, dialeg);
        }

        meny.setItem(22, switch (player.getPickaxeLevel()) {
            case 0 -> woodPick;
            case 1 -> ironPick;
            case 2 -> diaPick;
            case 3 -> null;
            default -> throw new IllegalStateException("Unexpected value: " + player.getPickaxeLevel());
        });
        meny.setItem(31, shears);
        meny.setItem(40, switch (player.getAxeLevel()) {
            case 0 -> woodaxe;
            case 1 -> ironaxe;
            case 2 -> diaaxe;
            case 3 -> null;
            default -> throw new IllegalStateException("Unexpected value: " + player.getAxeLevel());
        });

        meny.setItem(41, gapple);
        meny.setItem(42, ender);
        meny.setItem(43, charge);
        meny.setItem(25, tnt);
        meny.setItem(34, water);

        meny.setItem(24, invsee);
        meny.setItem(33, jump);

        meny.setItem(23, bow);
        meny.setItem(32, arrows);

        player.getPlayer().openInventory(meny);
    }

    public static void openBlocksMeny(PlayerClass player) {
        Inventory meny = Bukkit.createInventory(player.getPlayer(), 6*9, "Shop");

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);


        // Glass panes
        meny.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(10, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        meny.setItem(11, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(12, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(14, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));


        // blocks
        meny.setItem(19, wool);
        meny.setItem(20, terracotta);
        meny.setItem(21, endstone);
        meny.setItem(22, wood);


        player.getPlayer().openInventory(meny);
    }

    public static void openToolsMeny(PlayerClass player) {
        Inventory meny = Bukkit.createInventory(player.getPlayer(), 6*9, "Shop");

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);

        // Glass panes
        meny.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(10, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(11, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        meny.setItem(12, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(14, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));


        meny.setItem(19, switch (player.getPickaxeLevel()) {
            case 0 -> woodPick;
            case 1 -> ironPick;
            case 2 -> diaPick;
            case 3 -> null;
            default -> throw new IllegalStateException("Unexpected value: " + player.getPickaxeLevel());
        });
        meny.setItem(20, shears);
        meny.setItem(21, switch (player.getAxeLevel()) {
            case 0 -> woodaxe;
            case 1 -> ironaxe;
            case 2 -> diaaxe;
            case 3 -> null;
            default -> throw new IllegalStateException("Unexpected value: " + player.getAxeLevel());
        });

        // Swords
        meny.setItem(22, stonesword);
        meny.setItem(23, ironsword);
        meny.setItem(24, diaesword);

        player.getPlayer().openInventory(meny);
    }

    public static void openUtilityMeny(PlayerClass player) {
        Inventory meny = Bukkit.createInventory(player.getPlayer(), 6*9, "Shop");

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);

        // Glass panes
        meny.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(10, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(11, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(12, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        meny.setItem(13, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(14, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));

        meny.setItem(19, gapple);
        meny.setItem(20, charge);
        meny.setItem(21, tnt);
        meny.setItem(22, ender);
        meny.setItem(23, water);


        player.getPlayer().openInventory(meny);
    }

    public ShopMeny() {
        ItemMeta quickMeta = quick.getItemMeta();
        if (lang.getString("Shop.Menys.Quick-name") != null){
            quickMeta.setItemName(lang.getString("Shop.Menys.Quick-name"));
        }
        quick.setItemMeta(quickMeta);

        ItemMeta blocksMeta = blocks.getItemMeta();
        if (lang.getString("Shop.Menys.Blocks-name") != null){
            blocksMeta.setItemName(lang.getString("Shop.Menys.Blocks-name"));
        }
        blocks.setItemMeta(blocksMeta);

        ItemMeta toolsMeta = tools.getItemMeta();
        if (lang.getString("Shop.Menys.Tools-name") != null){
            toolsMeta.setItemName(lang.getString("Shop.Menys.Tools-name"));
        }
        tools.setItemMeta(toolsMeta);

        ItemMeta utilitysMeta = utilitys.getItemMeta();
        if (lang.getString("Shop.Menys.Utility-name") != null){
            utilitysMeta.setItemName(lang.getString("Shop.Menys.Utility-name"));
        }
        utilitys.setItemMeta(utilitysMeta);

        setBlocksLore();
        setToolsLore();

        PotionMeta invseeMeta = (PotionMeta) invsee.getItemMeta();
        invseeMeta.setBasePotionType(PotionType.INVISIBILITY);
        invsee.setItemMeta(invseeMeta);

        PotionMeta jumpMeta = (PotionMeta) jump.getItemMeta();
        jumpMeta.setBasePotionType(PotionType.LEAPING);
        jump.setItemMeta(jumpMeta);
    }

    public void setBlocksLore() {
        ItemMeta woolMeta = wool.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.wool.price-type") != null){
                woolMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.wool.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.wool.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " wool");
            }
        }
        wool.setItemMeta(woolMeta);

        ItemMeta terracottaMeta = terracotta.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.terracotta.price-type") != null){
                terracottaMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.terracotta.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.terracotta.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " terracotta");
            }
        }
        terracotta.setItemMeta(terracottaMeta);

        ItemMeta endstoneMeta = endstone.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.endstone.price-type") != null){
                endstoneMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.endstone.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.endstone.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " endstone");
            }
        }
        endstone.setItemMeta(endstoneMeta);

        ItemMeta woodMeta = wood.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.wood.price-type") != null){
                woodMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.wood.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.wood.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " wood");
            }
        }
        wood.setItemMeta(woodMeta);
    }

    public void setToolsLore() {
        ItemMeta woodenPickMeta = woodPick.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && lang.getString("Shop-Price-massage").contains("<type>") && shopConfig.getString("Items.woodPick.price-type") != null){
                woodenPickMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.woodPick.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.woodPick.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " wooden pickaxe");
            }
        }
        woodPick.setItemMeta(woodenPickMeta);

        ItemMeta ironPickMeta = ironPick.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.ironPick.price-type") != null){
                ironPickMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.ironPick.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.ironPick.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " iron pickaxe");
            }
        }
        ironPick.setItemMeta(ironPickMeta);

        ItemMeta diaPickMeta = diaPick.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.diaPick.price-type") != null){
                ironPickMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.diaPick.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.diaPick.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " dia pickaxe");
            }
        }
        diaPick.setItemMeta(diaPickMeta);

        ItemMeta woodenAxeMeta = woodaxe.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.woodAxe.price-type") != null){
                woodenAxeMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.woodAxe.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.woodAxe.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " wooden axe");
            }
        }
        woodaxe.setItemMeta(woodenAxeMeta);

        ItemMeta ironAxeMeta = ironaxe.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.ironAxe.price-type") != null){
                ironAxeMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.ironAxe.price"))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.ironAxe.price-type")))
                        )));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " iron axe");
            }
        }
        ironaxe.setItemMeta(ironAxeMeta);

        ItemMeta diaAxeMeta = diaaxe.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.diaAxe.price-type") != null) {
                diaAxeMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.diaAxe.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.diaAxe.price-type")))));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " dia axe");
            }
        }
        diaaxe.setItemMeta(diaAxeMeta);

        ItemMeta shearsMeta = shears.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.diaAxe.price-type") != null) {
                shearsMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.diaAxe.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.diaAxe.price-type")))));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " shears");
            }
        }
        shears.setItemMeta(shearsMeta);
    }
}
