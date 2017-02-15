package nl.imine.expshare.controller;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.imine.expshare.BukkitStarter;
import nl.imine.expshare.modal.PlayerExperience;
import nl.imine.expshare.service.PlayerExperienceService;
import nl.imine.expshare.util.Instances;

public final class PlayerController implements Listener {

	private PlayerExperienceService playerExperienceService = Instances.playerExperienceService;

	public static void init(){
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerController(), BukkitStarter.getInstance());
	}

	private PlayerController(){}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent evt) {
		PlayerExperience playerExperience = playerExperienceService.load(evt.getPlayer());
		if(playerExperience == null){
			playerExperience = playerExperienceService.create(evt.getPlayer());
		}

		evt.getPlayer().setLevel(playerExperience.getLevels());
		evt.getPlayer().setExp(playerExperience.getExp());

	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent evt){
		playerExperienceService.save(evt.getPlayer());
	}
}
