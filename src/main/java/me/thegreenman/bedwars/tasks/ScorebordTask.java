package me.thegreenman.bedwars.tasks;

import me.thegreenman.bedwars.GameStart;
import me.thegreenman.bedwars.PlayerClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScorebordTask implements Runnable {
    private final static ScorebordTask instance = new ScorebordTask();

    private ScorebordTask() {

    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getScoreboard();
            if (player.getScoreboard().getObjective("Bedwars") != null) {
                if(PlayerClass.findplayer(player.getUniqueId()) != null) {
                    updateScoreBoard(PlayerClass.findplayer(player.getUniqueId()));
                }
            }
            else {
                createNewScoreboard(player);
            }
        }
    }

    private void createNewScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Bedwars", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Bedwars");

        Team Blue = scoreboard.registerNewTeam("blue");
        String blueTeamKey = ChatColor.BLUE.toString();

        Blue.addEntry(blueTeamKey);
        Blue.setPrefix(ChatColor.BLUE + "B" + ChatColor.RESET + " Blue: ");
        Blue.setSuffix("✓");


        Team red = scoreboard.registerNewTeam("red");
        String redTeamKey = ChatColor.RED.toString();

        red.addEntry(redTeamKey);
        red.setPrefix(ChatColor.RED + "R" + ChatColor.RESET + " Red: ");
        red.setSuffix("✓");


        Team pink = scoreboard.registerNewTeam("pink");
        String pinkTeamKey = ChatColor.WHITE.toString();

        pink.addEntry(pinkTeamKey);
        pink.setPrefix(ChatColor.LIGHT_PURPLE + "P" + ChatColor.RESET + " Pink: ");
        pink.setSuffix("✓");


        Team green = scoreboard.registerNewTeam("green");
        String greenTeamKey = ChatColor.LIGHT_PURPLE.toString();

        green.addEntry(greenTeamKey);
        green.setPrefix(ChatColor.GREEN + "G" + ChatColor.RESET + " Green: ");
        green.setSuffix("✓");


        Team kills = scoreboard.registerNewTeam("kills");
        String killsTeamKey = ChatColor.GOLD.toString();

        kills.addEntry(killsTeamKey);
        kills.setPrefix("kills: ");
        kills.setSuffix("0");

        Team space = scoreboard.registerNewTeam("space");
        String spaceTeamKey = ChatColor.DARK_PURPLE.toString();

        space.addEntry(spaceTeamKey);

        objective.getScore(blueTeamKey).setScore(6);
        objective.getScore(redTeamKey).setScore(5);
        objective.getScore(pinkTeamKey).setScore(4);
        objective.getScore(greenTeamKey).setScore(3);
        objective.getScore(spaceTeamKey).setScore(2);
        objective.getScore(killsTeamKey).setScore(1);

        player.setScoreboard(scoreboard);
    }

    public void updateScoreBoard(PlayerClass player) {
        Scoreboard scoreboard = player.getPlayer().getScoreboard();

        Team blue = scoreboard.getTeam("blue");
        if (GameStart.TeamBlue != null && !GameStart.TeamBlue.getPlayers().isEmpty()) {
            blue.setSuffix(GameStart.TeamBlue.getBed() ? ChatColor.GREEN + "✓" : ChatColor.RED + String.valueOf(GameStart.TeamBlue.getPlayers().size()));
        }
        else {
            blue.setSuffix(ChatColor.RED + "x");
        }

        Team pink = scoreboard.getTeam("pink");
        if (GameStart.TeamPink != null && !GameStart.TeamPink.getPlayers().isEmpty()) {
            pink.setSuffix(GameStart.TeamPink.getBed() ? ChatColor.GREEN + "✓" : ChatColor.RED + String.valueOf(GameStart.TeamPink.getPlayers().size()));
        }
        else {
            pink.setSuffix(ChatColor.RED + "x");
        }

        Team green = scoreboard.getTeam("green");
        if (GameStart.TeamGreen != null && !GameStart.TeamGreen.getPlayers().isEmpty()) {
            green.setSuffix(GameStart.TeamGreen.getBed() ? ChatColor.GREEN + "✓" : ChatColor.RED + String.valueOf(GameStart.TeamGreen.getPlayers().size()));
        }
        else {
            green.setSuffix(ChatColor.RED + "x");
        }


        Team red = scoreboard.getTeam("red");
        if (GameStart.TeamRed != null && !GameStart.TeamRed.getPlayers().isEmpty()) {
            red.setSuffix(GameStart.TeamRed.getBed() ? ChatColor.GREEN + "✓" : ChatColor.RED + String.valueOf(GameStart.TeamRed.getPlayers().size()));
        }
        else {
            red.setSuffix(ChatColor.RED + "x");
        }

        Team kills = scoreboard.getTeam("kills");
        kills.setSuffix(String.valueOf(player.getKills()));

        
    }

    public static ScorebordTask getInstace() {
        return instance;
    }
}
