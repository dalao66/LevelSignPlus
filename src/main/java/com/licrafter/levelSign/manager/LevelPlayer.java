package com.licrafter.levelSign.manager;

import com.licrafter.levelSign.SignExtend;
import com.licrafter.levelSign.config.Level;
import com.licrafter.levelSign.config.RecordConfig;
import org.bukkit.OfflinePlayer;

/**
 * Created by shell on 2018/4/5.
 * <p>
 * player's uuid is different between online-mode and offline-mode
 * <p>
 * Github: https://github.com/shellljx
 */
public class LevelPlayer {

    // TODO: 2018/4/10 可能uuid不变名字变了要更换名字? 
    private OfflinePlayer offlinePlayer;
    private RecordConfig.LevelRecord record;
    private Level level;
    private Level nextLevel;

    public LevelPlayer(OfflinePlayer player) {
        this.offlinePlayer = player;
        if (offlinePlayer == null) {
            return;
        }
        refreshLevel();
    }

    public LevelPlayer(String player) {
        this(SignExtend.getInstance().getOfflinePlayer(player));
    }

    /**
     * player quit game and login again player object will change;
     *
     * @param player
     */
    public void updatePlayer(OfflinePlayer player) {
        updatePlayer(player, false);
    }

    public void updatePlayer(OfflinePlayer player, boolean refresh) {
        this.offlinePlayer = player;
        if (refresh) {
            refreshLevel();
        }
    }

    private void refreshLevel() {
        record = SignExtend.getInstance().getPlayerRecord(offlinePlayer);
        level = SignExtend.getInstance().getDefaultConfig().getLevelByName(record.level);
        nextLevel = SignExtend.getInstance().getDefaultConfig().getNextLevel(level);
    }

    public Level getLevel() {
        // TODO: 2018/4/22 直接暴露level可能会被外部修改
        return level;
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void addPoints(int point) {
        record.addPoint(point);
    }

    public boolean canLevelUp() {
        return record.point > level.upgradePoints;
    }

    public void levelUp() {
        record.level = nextLevel.nick;
        level = nextLevel;
        nextLevel = SignExtend.getInstance().getDefaultConfig().getNextLevel(level);
    }
}
