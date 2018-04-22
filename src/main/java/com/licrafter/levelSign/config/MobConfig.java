package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigBean;
import com.licraft.apt.config.ConfigSection;
import com.licraft.apt.config.ConfigValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shell on 2018/1/27.
 * <p>
 * Github: https://github.com/shellljx
 */
@ConfigBean(file = "mob.yml")
public class MobConfig {

    @ConfigSection(path = "mobs")
    public Map<String, MobRecord> mobRecordMap = new HashMap<>();

    public static class MobRecord implements Comparable<MobRecord> {
        @ConfigValue(path = "name")
        public String name;
        @ConfigValue(path = "amount")
        public int amount;

        @Override
        public int compareTo(MobRecord o) {
            return o.amount - amount;
        }
    }

    public MobRecord getRankOne() {
        MobRecord[] array = getRecordByDesc();
        if (array == null || array.length < 1) {
            return null;
        }
        return array[0];
    }

    public MobRecord getRankTwo() {
        MobRecord[] array = getRecordByDesc();
        if (array == null || array.length < 2) {
            return null;
        }
        return array[1];
    }

    public MobRecord getRankThree() {
        MobRecord[] array = getRecordByDesc();
        if (array == null || array.length < 3) {
            return null;
        }
        return array[2];
    }

    /**
     * 降序获取杀怪记录
     *
     * @return
     */
    public MobRecord[] getRecordByDesc() {
        if (mobRecordMap == null) {
            return null;
        }
        MobRecord[] values = (MobRecord[]) mobRecordMap.values().toArray();
        Arrays.sort(values);
        return values;
    }

    /**
     * 根据 record获取uuid
     *
     * @param record
     * @return
     */
    public String getUUID(MobRecord record) {
        String uuid = "";
        for (String key : mobRecordMap.keySet()) {
            if (mobRecordMap.get(key).name.equals(record.name)) {
                uuid = key;
                break;
            }
        }
        return uuid;
    }
}
