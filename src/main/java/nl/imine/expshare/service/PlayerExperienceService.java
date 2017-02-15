package nl.imine.expshare.service;

import org.bukkit.entity.Player;

import nl.imine.expshare.dao.PlayerExperienceDao;
import nl.imine.expshare.modal.PlayerExperience;
import nl.imine.expshare.util.Instances;

public class PlayerExperienceService {

	private PlayerExperienceDao playerExperienceDao = Instances.playerExperienceDao;

	public PlayerExperience create(Player player){
		PlayerExperience playerExperience = new PlayerExperience(player.getUniqueId(), player.getLevel(), player.getExp());
		playerExperienceDao.insert(playerExperience);
		return playerExperience;
	}

	public void save(Player player){
		playerExperienceDao.update(new PlayerExperience(player.getUniqueId(), player.getLevel(), player.getExp()));
	}

	public PlayerExperience load(Player player){
		System.out.println(player);
		System.out.println(playerExperienceDao);
		return playerExperienceDao.getById(player.getUniqueId());
	}
}
