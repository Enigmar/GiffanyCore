package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.App;
import de.linzn.aiCore.internal.KeywordContainer;
import de.linzn.aiCore.internal.ObjectContainer;

public class DBKeyword {

	public static KeywordContainer getKeyword(ObjectContainer objectCon, String keyword) {
		KeywordContainer keywordCont = null;
		try {

			Connection con = App.appInstance.mysqlData.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_keyword WHERE objectid ='" + objectCon.objectID + "' AND keyword ='"
					+ keyword + "';");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				keywordCont = new KeywordContainer(rs.getInt("id"), rs.getInt("objectid"), rs.getString("keyword"),
						rs.getString("function"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keywordCont;
	}

}
