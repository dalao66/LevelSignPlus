package com.licrafter.levelSign.listeners;

import com.licrafter.levelSign.SignExtend;
import com.licrafter.levelSign.events.PlayerUpgradeEvent;
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
    public void onPlayerUpgrade(PlayerUpgradeEvent event) {
        //一些升级后的操作
    }
}
