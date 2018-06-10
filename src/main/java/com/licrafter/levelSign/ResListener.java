package com.licrafter.levelSign;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.event.ResidenceCreationEvent;
import com.bekvon.bukkit.residence.event.ResidenceOwnerChangeEvent;
import com.bekvon.bukkit.residence.protection.CuboidArea;
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
        if (!plugin.isEnableRes()) {
            return;
        }
        CuboidArea area = event.getPhysicalArea();
        Player player = event.getPlayer();
        int count = ResidenceApi.getPlayerManager().getResidencePlayer(player.getName()).getResAmount();
        int xSize = area.getXSize();
        int zSize = area.getZSize();
        int maxResCount = plugin.getPlayerMaxResCount(player.getUniqueId());
        int maxResSize = plugin.getPlayerMaxResSize(player.getUniqueId());
        if ((count >= maxResCount) && (!player.isOp())) {
            player.sendMessage(ChatColor.DARK_RED + "��a ʱ��ȼ� ��b��l>> ��f" + ChatColor.AQUA + "�����������Ѿ��ﵽ����,����������ľ�λ�ٹ������!");
            event.setCancelled(true);
        }
        if (((xSize > maxResSize) || (zSize > maxResSize)) && (!player.isOp())) {
            player.sendMessage(ChatColor.DARK_RED + "��a ʱ��ȼ� ��b��l>> ��f" + ChatColor.AQUA + "��Ȧ�����̫����,����������ľ�λ�ٹ������,������С��س���!");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void resChangeOwnerEvent(ResidenceOwnerChangeEvent event) {
        if (!plugin.isEnableRes()) {
            return;
        }
        Player player = plugin.getServer().getPlayer(event.getNewOwner());
        int count = ResidenceApi.getPlayerManager().getResidencePlayer(event.getNewOwner()).getResAmount();
        int maxResCount = plugin.getPlayerMaxResCount(player.getUniqueId());
        if (count >= maxResCount && !player.isOp()) {
            player.sendMessage(ChatColor.DARK_RED + "��a ʱ��ȼ� ��b��l>> ��f" + ChatColor.AQUA + "�����������Ѿ��ﵽ����,����������ľ�λ�ٽ������!");
            event.getResidence().remove();
            return;
        }
    }
}

