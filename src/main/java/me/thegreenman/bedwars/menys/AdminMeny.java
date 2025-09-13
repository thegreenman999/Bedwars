package me.thegreenman.bedwars.menys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class AdminMeny implements Listener {
    Inventory meny = Bukkit.createInventory(null, 9*4, "Admin Meny");

    public AdminMeny() {

    }

    public void openMenu(Player player) {



        player.openInventory(meny);
    }

}
