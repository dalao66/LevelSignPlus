package com.licrafter.levelSign.manager;

import com.licrafter.levelSign.SignExtend;
import org.bukkit.OfflinePlayer;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shell on 2018/4/5.
 * <p>
 * Github: https://github.com/shellljx
 */
public class PlayerManager {
    //<UUID,LevelPlayer>
    private ConcurrentHashMap<UUID, LevelPlayer> playerMap = new ConcurrentHashMap<>();
    private SignExtend plugin;

    public PlayerManager(SignExtend plugin) {
        this.plugin = plugin;
    }

    public LevelPlayer playerJoin(OfflinePlayer player) {
        LevelPlayer levelPlayer = playerMap.get(player.getUniqueId());
        if (levelPlayer == null) {
            levelPlayer = new LevelPlayer(player);
            playerMap.put(player.getUniqueId(), levelPlayer);
        } else {
            levelPlayer.updatePlayer(player);
        }
        return levelPlayer;
    }

    public LevelPlayer getLevelPlayer(String player) {
        return getLevelPlayer(SignExtend.getInstance().getOfflinePlayer(player));
    }

    public LevelPlayer getLevelPlayer(OfflinePlayer player) {
        LevelPlayer levelPlayer = null;
        if (player == null) {
            return null;
        }
        if (playerMap.containsKey(player.getUniqueId())) {
            levelPlayer = playerMap.get(player.getUniqueId());
            levelPlayer.updatePlayer(player);
        } else {
            levelPlayer = playerJoin(player);
        }
        return levelPlayer;
    }

    public void reload() {

    }
}
