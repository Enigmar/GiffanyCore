package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.internal.KeywordContainer;
import de.linzn.aiCore.internal.ObjectContainer;
import de.linzn.aiCore.internal.ResultContainer;

public class DBResult {
	
	private MySQLDatabase mysqlsb;
	
	public DBResult(MySQLDatabase mysqlsb){
		this.mysqlsb = mysqlsb;
	}

	public ResultContainer getResult(ObjectContainer objectCon, KeywordContainer keywordCon) {
		ResultContainer objectCont = null;
		try {

			Connection con = this.mysqlsb.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_result WHERE objectid ='" + objectCon.objectID + "' AND keywordid = '"+keywordCon.keywordID+"';");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				objectCont = new ResultContainer(rs.getInt("objectid"), rs.getInt("keywordid"), rs.getString("result"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objectCont;
	}

}
