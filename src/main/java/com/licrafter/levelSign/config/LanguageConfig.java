package com.licrafter.levelSign.config;

import com.licraft.apt.config.ConfigValue;

/**
 * Created by shell on 2018/4/3.
 * <p>
 * Github: https://github.com/shellljx
 */
public class LanguageConfig {

    @ConfigValue(path = "warning.moneyNotEnough",defaultsTo = "抱歉,你的金币不足")
    public String moneyNotEnough;
    @ConfigValue(path = "warning.playerNotFound",defaultsTo = "抱歉,没有找到该玩家")
    public String playerNotFound;
    @ConfigValue(path = "warning.addPointsEventCancelled",defaultsTo = "添加等级点数的事件被取消")
    public String addPointsEventCancelled;
    @ConfigValue(path = "level.upgradeMessage",defaultsTo = "启动升级程序!")
    public String upgradeMessage;
    @ConfigValue(path = "level.addedPermission",defaultsTo = "成功添加权限")
    public String addedPermission;
    @ConfigValue(path = "level.levelUpSuccess")
    public String levelUpSuccess;
    @ConfigValue(path = "level.addedPoints")
    public String addedPoints;
    @ConfigValue(path = "level.alreadyHighest")
    public String alreadyHighest;

}
