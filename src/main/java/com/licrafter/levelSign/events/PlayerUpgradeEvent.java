package com.licrafter.levelSign.events;

import com.licrafter.levelSign.config.Level;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by shell on 2018/2/25.
 * <p>
 * Github: https://github.com/shellljx
 */
public class PlayerUpgradeEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private OfflinePlayer player;
    private Level fromLevel;
    private Level toLevel;

    public PlayerUpgradeEvent(OfflinePlayer player, Level fromLevel, Level toLevel) {
        this.player = player;
        this.toLevel = toLevel;
        this.fromLevel = fromLevel;
    }

    public Level getFromLevel() {
        return fromLevel;
    }

    public Level getToLevel() {
        return toLevel;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
