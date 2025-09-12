package me.thegreenman.bedwars.commands;

import me.thegreenman.bedwars.GameStart;
import static me.thegreenman.bedwars.Bedwars.gameOn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!gameOn){
            GameStart gameSetup = new GameStart();
        }
        else {
            player.sendMessage("Game is already on");
        }



        return true;
    }
}
