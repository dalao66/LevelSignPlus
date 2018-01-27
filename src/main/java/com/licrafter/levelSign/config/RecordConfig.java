package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigBean;
import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.Map;
import java.util.UUID;

/**
 * Created by shell on 2018/1/27.
 * <p>
 * Github: https://github.com/shellljx
 */
@ConfigBean(file = "level.yml")
public class RecordConfig {

    @ConfigSection(path = "levels")
    public Map<String, LevelRecord> recordMap;

    public static class LevelRecord {
        @ConfigValue(path = "player")
        public String player;
        @ConfigValue(path = "level")
        public String level;
        @ConfigValue(path = "point")
        public int point;

        public void addPoint(int point) {
            this.point += point;
        }
    }
}
