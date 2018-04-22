package com.licrafter.levelSign;

import com.licraft.apt.PluginAnnotations;
import com.licrafter.levelSign.config.*;
import com.licrafter.levelSign.lib.Holder;
import com.licrafter.levelSign.lib.PtlManager;
import com.licrafter.levelSign.listeners.LevelListener;
import com.licrafter.levelSign.listeners.PlayerListener;
import com.licrafter.levelSign.listeners.ResListener;
import com.licrafter.levelSign.manager.LevelManager;
import com.licrafter.levelSign.manager.PlayerManager;
import com.licrafter.lib.eco.EcoSetup;
import com.licrafter.lib.eco.EconomyInterface;
import com.licrafter.lib.log.LicraftLog;
import com.licrafter.lib.permissions.PermissionSetup;
import com.licrafter.lib.permissions.PermissionsInterface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lijx on 16/5/16.
 */
public class SignExtend extends JavaPlugin {

    private static SignExtend INSTANCE = null;

    private PtlManager ptlManager;
    private LevelManager levelManager;
    private PlayerManager playerManager;
    private MobConfig mobConfig;
    private RecordConfig recordConfig;
    private DefaultConfig defaultConfig;
    private LanguageConfig languageConfig;
    private EconomyInterface economy;
    private PermissionsInterface permission;

    private ConcurrentHashMap<String, OfflinePlayer> offlinePlayerList = new ConcurrentHashMap<>();
    private String[] langFiles = new String[]{"zh", "en"};


    @Override
    public void onEnable() {
        INSTANCE = this;
        LicraftLog.printEnableInfo(this);
        economy = EcoSetup.onEnable(this);
        permission = PermissionSetup.onEnable(this);
        ptlManager = new PtlManager(this);
        ptlManager.onEnable();
        saveDefaultConfig();
        saveDefaultLangFile();
        loadConfigFiles();
        loadLangFile();
        levelManager = new LevelManager(this);
        playerManager = new PlayerManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new ResListener(this), this);
        getServer().getPluginManager().registerEvents(new LevelListener(this), this);
        PluginAnnotations.COMMAND.load(this, new PlayerCommand(this));

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                if (player == null) {
                    continue;
                }
                String name = player.getName();
                if (name == null) {
                    continue;
                }
                offlinePlayerList.put(player.getName().toLowerCase(), player);
            }
        });

        initHolder();
    }

    private void saveDefaultLangFile() {
        for (String lang : langFiles) {
            File file = new File("languages" + File.separator + lang + ".yml");
            if (!file.exists()) {
                saveResource(file.getPath(), false);
            }
        }
    }

    private void loadLangFile() {
        if (defaultConfig == null) {
            return;
        }
        languageConfig = PluginAnnotations.CONFIG
                .loadValues(this, "languages" + File.separator + defaultConfig.lang + ".yml", LanguageConfig.class);
    }

    public UUID getPlayerUUID(String playerName) {
        Player player = getServer().getPlayer(playerName);
        if (player != null) {
            return player.getUniqueId();
        }
        OfflinePlayer offlinePlayer = offlinePlayerList.get(playerName.toLowerCase());
        if (offlinePlayer != null) {
            return offlinePlayer.getUniqueId();
        }
        return null;
    }

    public OfflinePlayer getOfflinePlayer(String playerName) {
        if (playerName == null) {
            return null;
        }
        OfflinePlayer offlinePlayer = offlinePlayerList.get(playerName.toLowerCase());
        if (offlinePlayer != null) {
            return offlinePlayer;
        }
        Player player = getServer().getPlayer(playerName);
        if (player != null) {
            return player;
        }
        return null;
    }

    public OfflinePlayer getOfflinePlayer(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer != null) {
            return offlinePlayer;
        }
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            return player;
        }
        return null;
    }

    public RecordConfig.LevelRecord getPlayerRecord(UUID uuid) {
        return getPlayerRecord(getOfflinePlayer(uuid));
    }

    public RecordConfig.LevelRecord getPlayerRecord(String player) {
        return getPlayerRecord(getOfflinePlayer(player));
    }

    public RecordConfig.LevelRecord getPlayerRecord(OfflinePlayer offlinePlayer) {
        if (offlinePlayer != null) {
            for (Map.Entry<String, RecordConfig.LevelRecord> entry : recordConfig.recordMap.entrySet()) {
                if (entry.getKey().equals(offlinePlayer.getUniqueId().toString())) {
                    return entry.getValue();
                } else if (entry.getValue().player.equals(offlinePlayer.getName())) {
                    RecordConfig.LevelRecord record = recordConfig.recordMap.remove(entry.getKey());
                    recordConfig.recordMap.put(offlinePlayer.getUniqueId().toString(), record);
                    return record;
                }
            }

            RecordConfig.LevelRecord record = new RecordConfig.LevelRecord();
            record.player = offlinePlayer.getName();
            record.level = "default";
            record.point = 0;
            recordConfig.recordMap.put(offlinePlayer.getUniqueId().toString(), record);
            return record;
        }
        return null;
    }

    public static SignExtend getInstance() {
        return INSTANCE;
    }

    private void initHolder() {
        new Holder(this, "[NAME]", "sign.create") {
            @Override
            public String getValue(Player player, Location location, String originalLine) {
                return ChatColor.GREEN + player.getName();
            }

        };

        new Holder(this, "[LEVEL]", "sign.create") {
            @Override
            public String getValue(Player player, Location location, String originalLine) {
                return centerText(ChatColor.translateAlternateColorCodes('&'
                        , "&l等级:" + "&a&l" + playerManager.getLevelPlayer(player).getLevel().nick));
            }

        };
        new Holder(this, "[POINT]", "sign.create") {
            @Override
            public String getValue(Player player, Location location, String originalLine) {
                return centerText(ChatColor.translateAlternateColorCodes('&'
                        , "&l点数:" + "&a&l" + getPlayerRecord(player).point));
            }
        };

        new Holder(this, "[MAXPOINT]", "sign.create") {
            @Override
            public String getValue(Player player, Location location, String originalLine) {
                return centerText(ChatColor.translateAlternateColorCodes('&'
                        , "&l上限:" + "&a&l" + playerManager.getLevelPlayer(player).getLevel().upgradePoints));
            }
        };

        new Holder(this, "[ONE]", "sign.create") {

            @Override
            public String getValue(Player var1, Location var2, String var3) {
                String name = mobConfig.getRankOne().name;
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (name == null ? "暂无" : name));
            }
        };
        new Holder(this, "[TWO]", "sign.create") {
            @Override
            public String getValue(Player var1, Location var2, String var3) {
                String name = mobConfig.getRankTwo().name;
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (name == null ? "暂无" : name));
            }
        };
        new Holder(this, "[THREE]", "sign.create") {
            @Override
            public String getValue(Player var1, Location var2, String var3) {
                String name = mobConfig.getRankThree().name;
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (name == null ? "暂无" : name));
            }
        };

        new Holder(this, "[COUNT1]", "sign.create") {
            @Override
            public String getValue(Player var1, Location var2, String var3) {
                MobConfig.MobRecord record = mobConfig.getRankOne();
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (record == null ? 0 : record.amount) + "只");
            }
        };

        new Holder(this, "[COUNT2]", "sign.create") {
            @Override
            public String getValue(Player var1, Location var2, String var3) {
                MobConfig.MobRecord record = mobConfig.getRankTwo();
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (record == null ? 0 : record.amount) + "只");
            }
        };

        new Holder(this, "[COUNT3]", "sign.create") {
            @Override
            public String getValue(Player var1, Location var2, String var3) {
                MobConfig.MobRecord record = mobConfig.getRankThree();
                return ChatColor.translateAlternateColorCodes('&'
                        , "&a&l" + (record == null ? 0 : record.amount) + "只");
            }
        };
    }

    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    public LanguageConfig getLangConfig() {
        return languageConfig;
    }

    public RecordConfig getRecordConfig() {
        return recordConfig;
    }

    public MobConfig getMobConfig() {
        return mobConfig;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public PermissionsInterface getPermission() {
        return permission;
    }

    public String centerText(String text) {
        StringBuilder sb = new StringBuilder(text);
        return sb.append("               ").toString();
    }

    public boolean withDraw(OfflinePlayer player, double cost) {
        if (economy.canAfford(player, cost)) {
            economy.subtract(player, cost);
            return true;
        } else {
            return false;
        }
    }

    public void reload() {
        loadConfigFiles();
        playerManager.reload();
    }

    private void loadConfigFiles() {
        defaultConfig = loadValues(DefaultConfig.class);
        recordConfig = loadValues(RecordConfig.class);
        mobConfig = loadValues(MobConfig.class);
    }

    private <T> T loadValues(Class<T> tClass) {
        return PluginAnnotations.CONFIG.loadValues(this, tClass);
    }

    @Override
    public void onDisable() {
        PluginAnnotations.CONFIG.saveValues(this, recordConfig);
        PluginAnnotations.CONFIG.saveValues(this, mobConfig);
        LicraftLog.printDisableInfo(this);
        ptlManager.onDisable();
        ptlManager = null;
    }
}
