package com.licrafter.levelSign.listeners;

import com.licrafter.levelSign.SignExtend;
import com.licrafter.levelSign.events.PlayerAddPointsEvent;
import com.licrafter.levelSign.events.PlayerUpgradeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;

/**
 * Created by lijx on 16/5/16.
 */
public class PlayerListener implements Listener {

    private SignExtend plugin;


    public PlayerListener(SignExtend plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null) {
            Block block = event.getClickedBlock();
            Material blockType = block.getType();
            if (blockType == Material.WALL_SIGN || blockType == Material.SIGN_POST || blockType == Material.SIGN) {
                Sign sign = (Sign) block.getState();
                for (int i = 0; i < 4; i++) {
                    String line = sign.getLine(i);
                    if (line.contains("[LEVEL]")) {
                        plugin.getLevelManager().addLevelPoints(event.getPlayer());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerUpgrade(PlayerUpgradeEvent event) {
//        event.getPlayer().sendMessage(ChatColor.GREEN + "[LEVEL]" + ChatColor.GRAY + "你的爵位已经升级到 " + event.getToLevel().nick + ",继续努力哦!");
//        if (plugin.getDefaultConfig().broadCast) {
//            plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
//                    "&a[LEVEL]&7恭喜&b" + event.getPlayer().getName() + "&7的爵位升级到" + event.getToLevel() + "&7!"));
//        }

    }

    @EventHandler
    public void onPlayerBuyPoints(PlayerAddPointsEvent event) {
//        double cost = plugin.getDefaultConfig().perPointPrice * event.getBuyCount();
//        boolean success = event.isFree() || plugin.withDraw(event.getPlayer(), cost);
//
//        if (success) {
//            plugin.getLevelManager().addPointsByUUID(event.getPlayer().getUniqueId(), event.getBuyCount());
//            event.getPlayer().sendMessage(ChatColor.GREEN + "[LEVEL]" + ChatColor.GRAY + "你成功购买了"
//                    + event.getBuyCount() + "点数,花费" + cost + "游戏币");
//        } else {
//            event.getPlayer().sendMessage(ChatColor.RED + "购买失败,或许你的游戏币不够用了,快去赚钱吧!");
//            event.setCancelled(true);
//        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        Entity killer = event.getEntity().getKiller();
        if (killer instanceof Projectile) {
            Player player = (Player) ((Projectile) killer).getShooter();
            // plugin.updateMobCount(player.getName());
        }
        if (killer instanceof Player) {
            Player player = (Player) killer;
            // plugin.updateMobCount(player.getName());
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        //近战伤害
//        if (events.getDamager() instanceof Player) {
//            Player player = (Player) events.getDamager();
//            double newDamage = events.getDamage() + plugin.getAttackPlus(player.getUniqueId());
//            events.setDamage(newDamage);
//        }
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        setMaxHealth(event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        setMaxHealth(event.getPlayer());
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        setMaxHealth(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String format = event.getFormat();
        // events.setFormat(ChatColor.translateAlternateColorCodes('&', "&l" + plugin.getPlayerLevelNick(p.getUniqueId())));
        String pformat = event.getFormat();
        event.setFormat("[" + pformat + ChatColor.WHITE + "]" + format);
        event.setFormat(event.getFormat());
    }

    public void setMaxHealth(Player player) {
//        if (!plugin.isEnableHealth()) {
//            return;
//        }
//        Double maxHealth = plugin.getPlayerMaxHealth(player.getUniqueId());
//        player.setHealthScale(maxHealth);
//        player.setMaxHealth(maxHealth);
//        player.setHealthScaled(true);
    }

}
