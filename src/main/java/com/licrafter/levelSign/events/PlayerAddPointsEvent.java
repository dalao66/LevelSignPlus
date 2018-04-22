package com.licrafter.levelSign.events;

import com.licrafter.levelSign.config.Level;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by shell on 2018/2/25.
 * <p>
 * Github: https://github.com/shellljx
 */
public class PlayerAddPointsEvent extends Event implements Cancellable {

    private OfflinePlayer player;
    private boolean cancel = false;
    private Level.Points points;
    private static final HandlerList handlers = new HandlerList();

    public PlayerAddPointsEvent(OfflinePlayer player, Level.Points points) {
        this.points = points;
        this.player = player;
    }

    public double getCost() {
        return points.amount * points.price;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public int getBuyCount() {
        return points.amount;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }
}
