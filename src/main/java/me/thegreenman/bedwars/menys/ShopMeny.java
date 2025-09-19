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
    public static ItemStack diasword = new ItemStack(Material.DIAMOND_SWORD);

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
    public static ItemStack fireCharge = new ItemStack(Material.FIRE_CHARGE);
    public static ItemStack windCharge = new ItemStack(Material.WIND_CHARGE);
    public static ItemStack tnt = new ItemStack(Material.TNT);
    public static ItemStack shears = new ItemStack(Material.SHEARS);
    public static ItemStack ender = new ItemStack(Material.ENDER_PEARL);
    public static ItemStack water = new ItemStack(Material.WATER_BUCKET);

    // meny Items
    public static ItemStack quick = new ItemStack(Material.NETHER_STAR);
    public static ItemStack blocks = new ItemStack(Material.TERRACOTTA);
    public static ItemStack tools = new ItemStack(Material.STONE_PICKAXE);
    public static ItemStack utilitys = new ItemStack(Material.TNT);
    public static ItemStack weapens = new ItemStack(Material.STONE_SWORD);

    //potions
    public static ItemStack invsee = new ItemStack(Material.POTION);
    public static ItemStack jump = new ItemStack(Material.POTION);


    public static void openShop(PlayerClass player, Boolean fromInventory) {
        Inventory meny = null;
        if (fromInventory) {
            player.getPlayer().getOpenInventory().getTopInventory().clear();
            meny = player.getPlayer().getOpenInventory().getTopInventory();
        }
        else {
            meny = Bukkit.createInventory(player.getPlayer(), 6 * 9, "Shop");
        }

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);
        meny.setItem(4, weapens);

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
        meny.setItem(38, diasword);

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
        meny.setItem(43, fireCharge);
        meny.setItem(25, tnt);
        meny.setItem(34, water);

//        meny.setItem(24, invsee);
        meny.setItem(33, jump);

        meny.setItem(23, bow);
        meny.setItem(32, arrows);

        if (fromInventory){
            player.getPlayer().updateInventory();
        }
        else {
            player.getPlayer().openInventory(meny);
        }
    }

    public static void openBlocksMeny(PlayerClass player) {
        player.getPlayer().getOpenInventory().getTopInventory().clear();

        Inventory meny = player.getPlayer().getOpenInventory().getTopInventory();

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);
        meny.setItem(4, weapens);

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


        player.getPlayer().updateInventory();
    }

    public static void openToolsMeny(PlayerClass player) {
        player.getPlayer().getOpenInventory().getTopInventory().clear();

        Inventory meny = player.getPlayer().getOpenInventory().getTopInventory();

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);
        meny.setItem(4, weapens);

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



        player.getPlayer().updateInventory();
    }

    public static void openUtilityMeny(PlayerClass player) {
        player.getPlayer().getOpenInventory().getTopInventory().clear();

        Inventory meny = player.getPlayer().getOpenInventory().getTopInventory();

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);
        meny.setItem(4, weapens);

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
        meny.setItem(20, fireCharge);
        meny.setItem(21, tnt);
        meny.setItem(22, ender);
        meny.setItem(23, water);


        player.getPlayer().updateInventory();
    }
    public static void openWeaponsMeny(PlayerClass player) {
        player.getPlayer().getOpenInventory().getTopInventory().clear();

        Inventory meny = player.getPlayer().getOpenInventory().getTopInventory();

        // menys
        meny.setItem(0, quick);
        meny.setItem(1, blocks);
        meny.setItem(2, tools);
        meny.setItem(3, utilitys);
        meny.setItem(4, weapens);

        // Glass panes
        meny.setItem(9, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(10, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(11, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(12, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(13, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        meny.setItem(14, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(15, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(16, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        meny.setItem(17, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));

        // Swords
        meny.setItem(19, stonesword);
        meny.setItem(20, ironsword);
        meny.setItem(21, diasword);

        meny.setItem(28, bow);

        player.getPlayer().updateInventory();
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

        ItemMeta weaponsMeta = weapens.getItemMeta();
        if (lang.getString("Shop.Menys.Weapons-name") != null){
            weaponsMeta.setItemName(lang.getString("Shop.Menys.Weapons-name"));
        }
        weapens.setItemMeta(weaponsMeta);

        PotionMeta invseeMeta = (PotionMeta) invsee.getItemMeta();
        invseeMeta.setBasePotionType(PotionType.INVISIBILITY);
        invsee.setItemMeta(invseeMeta);

        PotionMeta jumpMeta = (PotionMeta) jump.getItemMeta();
        jumpMeta.setBasePotionType(PotionType.LEAPING);
        jump.setItemMeta(jumpMeta);

        setBlocksLore();
        setToolsLore();
        setUtilitysLore();
        setWeapensLore();

        ItemMeta chainLegMeta = chainmailLeg.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.chainmailleg.price-type") != null){
                chainLegMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.chainmailleg.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.chainmailleg.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " chainmail leggings");
            }
        }
        chainmailLeg.setItemMeta(chainLegMeta);

        ItemMeta ironLegMeta = ironleg.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.ironleg.price-type") != null){
                ironLegMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.ironleg.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.ironleg.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " iron leggings");
            }
        }
        ironleg.setItemMeta(ironLegMeta);

        ItemMeta diaLegMeta = dialeg.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.dialeg.price-type") != null){
                diaLegMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.dialeg.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.dialeg.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " dia leggings");
            }
        }
        dialeg.setItemMeta(diaLegMeta);
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
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.ironAxe.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.ironAxe.price-type")))
                        ));
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
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.diaAxe.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " dia axe");
            }
        }
        diaaxe.setItemMeta(diaAxeMeta);

        ItemMeta shearsMeta = shears.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.shears.price-type") != null) {
                shearsMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.shears.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.shears.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " shears");
            }
        }
        shears.setItemMeta(shearsMeta);
    }

    public void setUtilitysLore() {
        ItemMeta gappleMeta = gapple.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.goldApple.price-type") != null) {
                gappleMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.goldApple.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.goldApple.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " gold apple");
            }
        }
        gapple.setItemMeta(gappleMeta);

        ItemMeta fireChargeMeta = fireCharge.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.fireCharge.price-type") != null) {
                fireChargeMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.fireCharge.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.fireCharge.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " fire charge");
            }
        }
        fireCharge.setItemMeta(fireChargeMeta);
    }

    public void setWeapensLore() {
        ItemMeta stoneSwordMeta = stonesword.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.stoneSword.price-type") != null) {
                stoneSwordMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.stoneSword.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.stoneSword.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " stone Sword");
            }
        }
        stonesword.setItemMeta(stoneSwordMeta);

        ItemMeta ironSwordMeta = ironsword.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.ironSword.price-type") != null) {
                ironSwordMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.ironSword.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.ironSword.price-type")))
                ));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " iron Sword");
            }
        }
        ironsword.setItemMeta(ironSwordMeta);

        ItemMeta diaSwordMeta = diasword.getItemMeta();
        if (lang.getString("Shop-Price-massage") != null) {
            if (lang.getString("Shop-Price-massage").contains("<price>") && shopConfig.getString("Items.diaSword.price-type") != null) {
                diaSwordMeta.setLore(List.of(ChatColor.GRAY + lang.getString("Shop-Price-massage")
                        .replace("<price>", String.valueOf(shopConfig.getInt("Items.diaSword.price")))
                        .replace("<type>", Objects.requireNonNull(shopConfig.getString("Items.diaSword.price-type")))));
            }
            else {
                main.getLogger().severe(lang.getString("Shop-setLore-massage") + " dia Sword");
            }
        }
        diasword.setItemMeta(diaSwordMeta);

    }

}