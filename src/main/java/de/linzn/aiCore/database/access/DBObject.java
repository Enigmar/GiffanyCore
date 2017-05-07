package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.internal.ObjectContainer;

public class DBObject {

	private MySQLDatabase mysqlsb;

	public DBObject(MySQLDatabase mysqlsb) {
		this.mysqlsb = mysqlsb;
	}

	public ObjectContainer getObject(String objectName) {
		ObjectContainer objectCont = null;
		try {

			Connection con = this.mysqlsb.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_object WHERE objectname ='" + objectName + "'");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				objectCont = new ObjectContainer(rs.getInt("id"), rs.getString("objectname"), rs.getString("class"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objectCont;
	}

}
