package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigBean;
import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by shell on 2018/1/27.
 * <p>
 * Github: https://github.com/shellljx
 */
@ConfigBean
public class DefaultConfig {
    @ConfigValue(path = "setting.language")
    public String lang;
    @ConfigValue(path = "setting.broadCast")
    public boolean broadCast;
    @ConfigValue(path = "setting.enableHealth")
    public boolean enableHealth;
    @ConfigValue(path = "setting.enableAttack")
    public boolean enableAttack;
    @ConfigValue(path = "setting.defaultWorlds")
    public List<String> defaultWorlds;
    @ConfigValue(path = "setting.levelProfile")
    public List<String> levelProfile;
    @ConfigSection(path = "setting.levels")
    public Map<String, Level> levelMap;

    private List<Level> levels;

    public List<Level> getLevelList() {
        if (levels == null) {
            levels = new ArrayList<>(levelMap.values());
            Collections.sort(levels);
        }
        return levels;

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

    public Level getNextLevel(Level level) {
        int next = getLevelList().indexOf(level) + 1;
        return next >= getLevelList().size() ? level : getLevelList().get(next);
    }

    public Level getPreLevel(Level level) {
        int pre = getLevelList().indexOf(level) - 1;
        return pre < 0 ? level : getLevelList().get(pre);
    }
}
