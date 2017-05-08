package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.internal.KeywordContainer;
import de.linzn.aiCore.internal.ObjectContainer;
import de.linzn.aiCore.internal.ResultContainer;
import de.linzn.aiCore.internal.SentenceContainer;

public class DBResult {

	private MySQLDatabase mysqldb;

	public DBResult(MySQLDatabase mysqldb) {
		this.mysqldb = mysqldb;
	}

	public ResultContainer getResultByObjects(ObjectContainer objectCon, KeywordContainer keywordCon) {
		ResultContainer resultCont = null;
		try {

			Connection con = this.mysqldb.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_resultindex WHERE objectid ='" + objectCon.objectID
					+ "' AND keywordid = '" + keywordCon.keywordID + "';");
			ResultSet rs = st.executeQuery(sql);
			int resultid = 0;
			int objectid = 0;
			int keywordid = 0;
			if (rs.next()) {
				resultid = rs.getInt("resultid");
				objectid = rs.getInt("objectid");
				keywordid = rs.getInt("keywordid");
			}

			sql = ("SELECT * FROM aicore_result WHERE id ='" + resultid + "';");
			rs = st.executeQuery(sql);
			if (rs.next()) {
				resultCont = new ResultContainer(objectid, keywordid, rs.getString("value"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultCont;
	}

	public ResultContainer getResultByText(SentenceContainer sentenceCon) {
		ResultContainer resultCont = null;
		try {

			Connection con = this.mysqldb.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_resultindex WHERE sentenceid = '" + sentenceCon.sentenceID + "';");
			ResultSet rs = st.executeQuery(sql);
			int resultid = 0;
			int sentenceID = 0;
			if (rs.next()) {
				resultid = rs.getInt("resultid");
				sentenceID = rs.getInt("sentenceid");
			}

			sql = ("SELECT * FROM aicore_result WHERE id = '" + resultid + "';");
			rs = st.executeQuery(sql);
			if (rs.next()) {
				resultCont = new ResultContainer(sentenceID, rs.getString("value"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultCont;
	}

}
