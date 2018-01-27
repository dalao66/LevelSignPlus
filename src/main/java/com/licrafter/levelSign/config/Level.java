package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.List;

/**
 * Created by shell on 2018/4/2.
 * <p>
 * Github: https://github.com/shellljx
 */
public class Level {

    @ConfigValue(path = "nick")
    public String nick;
    @ConfigValue(path = "health")
    public int health;
    @ConfigValue(path = "resGroup")
    public String resGroup;
    @ConfigValue(path = "upgradePoints")
    public int upgradePoints;
    @ConfigValue(path = "attack")
    public int attack;
    @ConfigValue(path = "nextLevel")
    public String nextLevel;
    @ConfigValue(path = "cmds")
    public List<String> cmdList;
    @ConfigValue(path = "permissions")
    public List<String> permList;
    @ConfigSection(path = "levelPoints")
    public Points points;

    public static class Points {
        @ConfigValue(path = "price")
        public double price;
        @ConfigValue(path = "amount")
        public int amount;
    }
}
