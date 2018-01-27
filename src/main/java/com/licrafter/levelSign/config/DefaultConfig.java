package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigBean;
import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.List;
import java.util.Map;

/**
 * Created by shell on 2018/1/27.
 * <p>
 * Github: https://github.com/shellljx
 */
@ConfigBean
public class DefaultConfig {
    @ConfigValue(path = "setting.broadCast")
    public boolean broadCast;
    @ConfigValue(path = "setting.enableHealth")
    public boolean enableHealth;
    @ConfigValue(path = "setting.res.enable")
    public boolean enableRes;
    @ConfigValue(path = "setting.res.enabledWorlds")
    public List<String> resEnabledWorlds;
    @ConfigValue(path = "setting.levelProfile")
    public List<String> levelProfile;
    @ConfigSection(path = "setting.levels")
    public Map<String, Level> levelMap;

    /**
     * 是否可以升级到下一级
     *
     * @param nowLevel
     * @param points
     * @return
     */
    public boolean canLevelUp(String nowLevel, int points) {
        Level level = levelMap.get(nowLevel);
        return points > level.upgradePoints;
    }

    /**
     * 获取level
     *
     * @param level
     * @return
     */
    public Level getLevelByName(String level) {
        return levelMap.get(level);
    }
}
