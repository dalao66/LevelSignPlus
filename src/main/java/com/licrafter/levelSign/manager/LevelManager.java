package com.licrafter.levelSign.manager;

import com.licrafter.levelSign.SignExtend;
import com.licrafter.levelSign.config.LanguageConfig;
import com.licrafter.levelSign.config.Level;
import com.licrafter.levelSign.events.PlayerAddPointsEvent;
import com.licrafter.levelSign.events.PlayerUpgradeEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by lijx on 16/7/28.
 */
public class LevelManager {

    private SignExtend plugin;
    private LanguageConfig languageConfig;

    public LevelManager(SignExtend plugin) {
        this.plugin = plugin;
        languageConfig = plugin.getLanguageConfig();
    }

    public boolean addLevelPoints(Player player) {
        if (player == null) {
            return false;
        }
        LevelPlayer levelPlayer = plugin.getPlayerManager().getLevelPlayer(player);
        return addLevelPoints(player, player.getName(), levelPlayer.getLevel().points);
    }

    public boolean addLevelPoints(CommandSender sender, String player, Level.Points points) {
        OfflinePlayer offlinePlayer = plugin.getOfflinePlayer(player);
        if (offlinePlayer == null) {
            sender.sendMessage(languageConfig.playerNotFound);
            return false;
        }
        PlayerAddPointsEvent addPointsEvent = new PlayerAddPointsEvent(offlinePlayer, points);
        Bukkit.getPluginManager().callEvent(addPointsEvent);
        if (addPointsEvent.isCancelled()) {
            sender.sendMessage(languageConfig.addPointsEventCancelled);
            return false;
        }
        boolean success = plugin.withDraw(offlinePlayer, addPointsEvent.getCost());
        if (success) {
            LevelPlayer levelPlayer = plugin.getPlayerManager().getLevelPlayer(player);
            levelPlayer.getRecord().addPoint(points.amount);
            if (levelPlayer.canLevelUp()) {
                PlayerUpgradeEvent upgradeEvent = new PlayerUpgradeEvent(offlinePlayer, levelPlayer.getLevel(), levelPlayer.getNextLevel());
                Bukkit.getPluginManager().callEvent(upgradeEvent);
                sender.sendMessage(languageConfig.upgradeMessage);
            }
            return true;
        } else {
            sender.sendMessage(languageConfig.moneyNotEnough);
            return false;
        }
    }
}
