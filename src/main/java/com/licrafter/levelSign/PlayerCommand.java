package com.licrafter.levelSign;

import com.licraft.apt.command.Command;
import com.licrafter.levelSign.config.Level;
import org.bukkit.command.CommandSender;

/**
 * Created by lijx on 16/5/19.
 */
public class PlayerCommand {

    private SignExtend plugin;

    public PlayerCommand(SignExtend plugin) {
        this.plugin = plugin;
    }

    @Command
    public void simple(CommandSender sender){
        Level.Points points = new Level.Points();
        points.price = 0;
        points.amount = 300;
        plugin.getLevelManager().addLevelPoints(sender,"shellljx",points);
    }

//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        if (args.length == 1 && args[0].equals("reload") && sender.isOp()) {
//            sender.sendMessage(ChatColor.RED + "[LEVEL]" + ChatColor.AQUA + "reloading config....");
//            plugin.reload();
//            sender.sendMessage(ChatColor.RED + "[LEVEL]" + ChatColor.AQUA + "config reloaded!");
//            return true;
//        }
//        if (args.length == 1 && args[0].equals("list")) {
//
//            String message = "";
//            Iterator<String> levels = plugin.getDefaultConfig().levelMap.keySet().iterator();
//            while (levels.hasNext()) {
//                message += plugin.getDefaultConfig().levelMap.get(levels.next());
//                if (levels.hasNext()) {
//                    message += "&7->";
//                }
//            }
//            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
//            return true;
//        }
//
////        if (args.length == 2 && args[0].equals("move") && sender.isOp()) {
////            Player player = (Player) sender;
////            plugin.setPlayerLevel(player.getUniqueId(), args[1]);
////            return true;
////        }
//
//        if (args.length == 3 && args[0].equals("add") && sender.isOp()) {
//            Player player = Bukkit.getPlayer(args[1]);
//            if (player == null) {
//                sender.sendMessage(ChatColor.RED + "[LEVEL]玩家不存在或者不在线!");
//                return true;
//            }
//            try {
//               // plugin.setPlayerPoint(player.getUniqueId(), Integer.parseInt(args[2]));
//            } catch (NumberFormatException e) {
//                sender.sendMessage(ChatColor.RED + "[LEVEL]经验必须为一个大于0的整数!");
//                return true;
//            }
//            sender.sendMessage(ChatColor.AQUA + "[LEVEL]为玩家" + args[1] + "增加了经验:" + args[2]);
//            return true;
//        }
//
//        if (args.length == 1 && args[0].equals("me") && sender instanceof Player) {
//            Player player = (Player) sender;
//            List<String> messages = plugin.getConfig().getStringList("setting.message");
//            for (String msg : messages) {
//               // msg = replace(player, msg);
//                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
//            }
//            return true;
//        }
//
//        if (args.length==1&&sender.isOp()){
//            Player player = Bukkit.getPlayer(args[0]);
//            if (player==null){
//                sender.sendMessage(ChatColor.RED + "[LEVEL]玩家不存在或者不在线!");
//                return true;
//            }
//            List<String> messages = plugin.getConfig().getStringList("setting.message");
//            for (String msg : messages) {
//              //  msg = replace(player, msg);
//                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
//            }
//            return true;
//        }
//
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("setting.title")));
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/level list  &7- 查看等级顺序"));
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/level me  &7- 查看自己的等级信息"));
//        if (sender.isOp()) {
//            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/level add [player] [点数]  &7- 为玩家增加经验点数(必须为正整数)"));
//        }
//        if (sender.isOp()) {
//            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/level [player]  &7- 查看某个玩家的等级信息"));
//        }
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/level reload  &7-  重载配置文件"));
//        return true;
//    }

//    public String replace(Player player, String msg) {
//        UUID uuid = player.getUniqueId();
//        RecordConfig.LevelRecord record = plugin.getRecordConfig().getRecordByUUID(uuid);
//        DefaultConfig.Level level = plugin.getDefaultConfig().getLevelByName(record.level);
//        msg = msg.replaceAll("%player%", player.getName()).replaceAll("%level%", record.level)
//                .replaceAll("%point%", record.point+"").replaceAll("%maxPoint%", String.valueOf(level.upgradePoints))
//                .replaceAll("%maxHealth%", String.valueOf(plugin.getPlayerMaxHealth(uuid))).replaceAll("%resCount%", String.valueOf(plugin.getPlayerMaxResCount(uuid)))
//                .replaceAll("%resSize%", String.valueOf(plugin.getPlayerMaxResSize(uuid))).replaceAll("%killMob%", String.valueOf(plugin.getKillMob(player.getName())))
//                .replaceAll("%attack%", String.valueOf(plugin.getAttackPlus(player.getUniqueId())));
//        return msg;
//    }

}
