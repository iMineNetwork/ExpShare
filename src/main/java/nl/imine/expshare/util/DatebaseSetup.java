package nl.imine.expshare.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.imine.expshare.service.MySQLService;

public class DatebaseSetup {

	private MySQLService mySQLService = Instances.mySQLService;

	public void setup(){
		try {
			PreparedStatement statement = mySQLService.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `ExpShare` (`UUID` VARCHAR(36) NOT NULL, `Level` INTEGER NOT NULL, `Progress` FLOAT NOT NULL) ");
			statement.execute();
		} catch (SQLException e) {
			System.err.println("Could not create tables | " + e.getMessage());
		}
	}
}
