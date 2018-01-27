package com.licrafter.levelSign.listeners;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.event.ResidenceCreationEvent;
import com.bekvon.bukkit.residence.event.ResidenceOwnerChangeEvent;
import com.bekvon.bukkit.residence.protection.CuboidArea;
import com.licrafter.levelSign.SignExtend;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by lijx on 16/5/19.
 */
public class ResListener implements Listener {
    private SignExtend plugin;

    public ResListener(SignExtend plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onResCreate(ResidenceCreationEvent event) {
//        if (!plugin.getDefaultConfig().enableRes) {
//            return;
//        }
//        CuboidArea area = events.getPhysicalArea();
//        Player player = events.getPlayer();
//        int count = ResidenceApi.getPlayerManager().getResidencePlayer(player.getName()).getResAmount();
//        int xSize = area.getXSize();
//        int zSize = area.getZSize();
//        int maxResCount = plugin.getPlayerMaxResCount(player.getUniqueId());
//        int maxResSize = plugin.getPlayerMaxResSize(player.getUniqueId());
//        if ((count >= maxResCount) && (!player.isOp())) {
//            player.sendMessage(ChatColor.DARK_RED + "[LEVEL]" + ChatColor.AQUA + "你的领地数量已经达到上限,请先升级你的爵位再购买领地!");
//            events.setCancelled(true);
//        }
//        if (((xSize > maxResSize) || (zSize > maxResSize)) && (!player.isOp())) {
//            player.sendMessage(ChatColor.DARK_RED + "[LEVEL]" + ChatColor.AQUA + "你圈的领地太大了,请先升级你的爵位再购买领地,或者缩小领地长度!");
//            events.setCancelled(true);
//        }
    }

    @EventHandler
    public void resChangeOwnerEvent(ResidenceOwnerChangeEvent event) {
//        if (!plugin.getDefaultConfig().enableRes) {
//            return;
//        }
//        Player player = plugin.getServer().getPlayer(events.getNewOwner());
//        int count = ResidenceApi.getPlayerManager().getResidencePlayer(events.getNewOwner()).getResAmount();
//        int maxResCount = plugin.getPlayerMaxResCount(player.getUniqueId());
//        if (count >= maxResCount && !player.isOp()) {
//            player.sendMessage(ChatColor.DARK_RED + "[LEVEL]" + ChatColor.AQUA + "你的领地数量已经达到上限,请先升级你的爵位再接收领地!");
//            events.getResidence().remove();
//            return;
//        }
    }
}

