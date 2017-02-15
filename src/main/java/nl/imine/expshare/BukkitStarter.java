package nl.imine.expshare;

import java.util.UUID;

import nl.imine.expshare.config.MySQLConfig;
import nl.imine.expshare.controller.PlayerController;
import nl.imine.expshare.dao.PlayerExperienceDao;
import nl.imine.expshare.modal.PlayerExperience;
import nl.imine.expshare.service.MySQLService;
import nl.imine.expshare.service.PlayerExperienceService;
import nl.imine.expshare.util.DatebaseSetup;
import nl.imine.expshare.util.Instances;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitStarter extends JavaPlugin{

    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        initDatabase();
        PlayerController.init();
    }

    @Override
    public void onDisable() {
        plugin = null;
        Instances.mySQLConfig = null;
        Instances.mySQLService = null;
        Instances.playerExperienceService = null;
        Instances.playerExperienceDao = null;
    }

    private static void initDatabase(){
        Instances.mySQLConfig = new MySQLConfig();
        Instances.mySQLConfig.loadConfigFile();

        Instances.mySQLService = new MySQLService(Instances.mySQLConfig.getUser(), Instances.mySQLConfig.getPassword(), Instances.mySQLConfig.getJdbcUrl());
        Instances.mySQLService.connect();
        new DatebaseSetup().setup();

        Instances.playerExperienceDao = new PlayerExperienceDao();
        Instances.playerExperienceService = new PlayerExperienceService();

        PlayerExperience playerExperience = new PlayerExperience();
        playerExperience.setPlayerId(UUID.randomUUID());
        playerExperience.setLevels(5);
        playerExperience.setExp(4.5f);
        Instances.playerExperienceDao.insert(playerExperience);
    }

    public static Plugin getInstance(){
        return plugin;
    }

}
