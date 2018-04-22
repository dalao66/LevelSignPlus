package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shell on 2018/4/2.
 * <p>
 * Github: https://github.com/shellljx
 */
public class Level implements Comparable<Level> {

    @ConfigValue(path = "nick")
    public String nick;
    @ConfigValue(path = "health")
    public int health;
    @ConfigValue(path = "upgradePoints")
    public int upgradePoints;
    @ConfigValue(path = "attack")
    public int attack;
    @ConfigValue(path = "priority")
    public int priority;
    @ConfigValue(path = "cmds")
    public List<String> cmdList = new ArrayList<>();
    @ConfigValue(path = "permissions")
    public List<String> permList = new ArrayList<>();
    @ConfigSection(path = "levelPoints")
    public Points points;

    @Override
    public int compareTo(Level o) {
        return priority - o.priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Level) {
            return this.priority == ((Level) obj).priority;
        }
        return false;
    }

    public static class Points {
        @ConfigValue(path = "price")
        public double price;
        @ConfigValue(path = "amount")
        public int amount;
    }
}
