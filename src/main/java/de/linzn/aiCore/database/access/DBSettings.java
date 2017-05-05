package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.App;

public class DBSettings {
	public static String aicore_name;

	public static boolean loadSettings() {
		Connection con = App.appInstance.mysqlData.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_setting;");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("key").equalsIgnoreCase("aicore_name")) {
					aicore_name = rs.getString("value");
					App.logger("Setting aicore_name: " + aicore_name);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
