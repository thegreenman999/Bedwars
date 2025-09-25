package me.thegreenman.bedwars.commands;

import me.thegreenman.bedwars.Bedwars;
import me.thegreenman.bedwars.PlayerClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HubCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        PlayerClass playerClass = PlayerClass.findplayer(player.getUniqueId());

        if (playerClass != null && playerClass.Team != null) {
            return true;
        }

        player.teleport(Bedwars.world.getSpawnLocation());


        return true;
    }
}
