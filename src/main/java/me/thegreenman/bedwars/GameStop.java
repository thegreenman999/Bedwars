package me.thegreenman.bedwars;

import me.thegreenman.bedwars.commands.ResetCommand;
import me.thegreenman.bedwars.tasks.RespawnTask;
import me.thegreenman.bedwars.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameStop {

    public static void Check() {
        TeamClass[] teamLista = { GameStart.TeamRed, GameStart.TeamPink, GameStart.TeamBlue, GameStart.TeamGreen };

        int numberNotEmpty = 0;
        TeamClass notEmptyTeam = null;

        for (TeamClass teamClass : teamLista) {
            if (teamClass != null && teamClass.getPlayers() != null && !teamClass.getPlayers().isEmpty()) {
                numberNotEmpty++;
                notEmptyTeam = teamClass;
            }
        }

        if (numberNotEmpty == 1) {
            // Stops the Game
            GameStop.Stop(notEmptyTeam);
        }
    }

    public static void Stop(TeamClass winningTeam) {
        Logger.log("Games has ended");

        // cancel all respawn Tasks
        if (!RespawnTask.respawnTaskList.isEmpty()) {
            for (BukkitTask bukkitTask : RespawnTask.respawnTaskList) {
                if (bukkitTask != null && !bukkitTask.isCancelled()){
                    bukkitTask.cancel();
                }
            }
        }

        GameStart.TeamGreen = null;
        GameStart.TeamRed = null;
        GameStart.TeamPink = null;
        GameStart.TeamBlue = null;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setGameMode(GameMode.SPECTATOR);
        }

        // reset the arena
        Bedwars.main.reset();
        ResetCommand.reset();



        new BukkitRunnable() {
            @Override
            public void run() {
                tphub();
                broadcast(winningTeam);
            }
        }.runTaskLater(Bedwars.main, 1L);
    }

    private static void tphub() {
        // tp all player to hub
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(Bedwars.world.getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
        }
    }

    private static void broadcast(TeamClass winningTeam) {
        // Broadcast the winners/winningTeam
        if (Bedwars.lang.getString("Team-win-massage") == null) {
            return;
        }

        String winMassage = Bedwars.lang.getString("Team-win-massage");
        winMassage.replace("<team>", winningTeam.getColor());

        Bukkit.broadcastMessage(winMassage);
    }


}
