package com.licrafter.levelSign.listeners;

import com.licrafter.levelSign.SignExtend;
import com.licrafter.levelSign.config.Level;
import com.licrafter.levelSign.events.PlayerAddPointsEvent;
import com.licrafter.levelSign.events.PlayerUpgradeEvent;
import com.licrafter.lib.log.LicraftLog;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by shell on 2018/4/6.
 * <p>
 * Github: https://github.com/shellljx
 */
public class LevelListener implements Listener {

    private SignExtend plugin;

    public LevelListener(SignExtend plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPointsAdd(PlayerAddPointsEvent event) {
        sendBuyPointsMsg(event.getPlayer(), event.getBuyCount(), event.getCost());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerUpgrade(PlayerUpgradeEvent event) {

        //添加权限
        Level toLevel = event.getToLevel();
        for (String perm : toLevel.permList) {
            String[] array = perm.split(",");
            String[] worlds;
            if (array.length == 1) {
                //使用默认世界列表
                worlds = new String[plugin.getDefaultConfig().defaultWorlds.size()];
                plugin.getDefaultConfig().defaultWorlds.toArray(worlds);
            } else {
                //使用 Level 设置的 worlds
                worlds = new String[array.length - 1];
                System.arraycopy(array, 1, worlds, 0, array.length - 1);
            }
            for (String world : worlds) {
                plugin.getPermission().playerAdd(world, event.getPlayer(), array[0]);
            }
            sendaddedPermMsg(event.getPlayer(), array[0]);
        }

        //后台执行命令
        for (String cmd : toLevel.cmdList) {
            Bukkit.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), cmd);
        }

        //全服播放
        sendLevelupMsg(event.getPlayer(), event.getToLevel().nick);
    }

    private void sendBuyPointsMsg(OfflinePlayer player, int amount, double cost) {
        String msg = plugin.getLangConfig().addedPoints
                .replaceAll("%amount%", amount + "")
                .replaceAll("%cost%", cost + "");
        sendMsg(player, msg);
    }

    private void sendaddedPermMsg(OfflinePlayer player, String perm) {
        String msg = plugin.getLangConfig().addedPermission
                .replaceAll("%player%", player.getName())
                .replaceAll("%perm%", perm);
        sendMsg(player, msg);
    }

    private void sendLevelupMsg(OfflinePlayer player, String level) {
        String msg = plugin.getLangConfig().levelUpSuccess
                .replaceAll("%player%", player.getName())
                .replaceAll("%level%", level);
        sendMsg(player, msg);
        if (plugin.getDefaultConfig().broadCast) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    private void sendMsg(OfflinePlayer player, String msg) {
        LicraftLog.consoleMessage(plugin.getName(), msg);
        if (player.isOnline()) {
            player.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}
