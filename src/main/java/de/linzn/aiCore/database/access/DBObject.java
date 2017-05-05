package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.ObjectContainer;

public class DBObject {

	public static ObjectContainer getObject(String objectName) {
		ObjectContainer objectCont = null;
		try {

			Connection con = App.appInstance.mysqlData.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_object WHERE call ='" + objectName + "';");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				objectCont = new ObjectContainer(rs.getInt("id"), rs.getString("call"), rs.getString("class"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objectCont;
	}

}
