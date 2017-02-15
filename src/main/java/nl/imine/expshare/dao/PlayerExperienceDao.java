package nl.imine.expshare.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import nl.imine.expshare.modal.PlayerExperience;
import nl.imine.expshare.service.MySQLService;
import nl.imine.expshare.util.Instances;

public class PlayerExperienceDao {

	private MySQLService mySQLService = Instances.mySQLService;

	public void insert(PlayerExperience playerExperience) {
		try {
			PreparedStatement statement = mySQLService.getConnection().prepareStatement("INSERT INTO `ExpShare` VALUES (?, ?, ?) ");
			statement.setString(1, playerExperience.getPlayerId().toString());
			statement.setInt(2, playerExperience.getLevels());
			statement.setFloat(3, playerExperience.getExp());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(PlayerExperience playerExperience) {
		try {
			PreparedStatement statement = mySQLService.getConnection().prepareStatement("UPDATE `ExpShare` SET `Level`= ?, `Progress`=? WHERE `UUID` LIKE ?");
			statement.setInt(1, playerExperience.getLevels());
			statement.setFloat(2, playerExperience.getExp());
			statement.setString(3, playerExperience.getPlayerId().toString());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PlayerExperience getById(UUID player) {
		try {
			PlayerExperience playerExperience = null;

			PreparedStatement statement = mySQLService.getConnection().prepareStatement("SELECT `Level`, `Progress` FROM `ExpShare` WHERE `UUID` LIKE ?");
			statement.setString(1, player.toString());

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.first()) {
				playerExperience = new PlayerExperience(player,
						resultSet.getInt("Level"),
						resultSet.getFloat("Progress")
				);
			}

			resultSet.close();
			statement.close();

			return playerExperience;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
