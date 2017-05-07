package de.linzn.aiCore.database.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.internal.SentenceContainer;

public class DBSentence {
	private MySQLDatabase mysqlsb;

	public DBSentence(MySQLDatabase mysqlsb) {
		this.mysqlsb = mysqlsb;
	}

	public SentenceContainer getSentence(String sentence) {
		SentenceContainer sentenceCont = null;
		try {

			Connection con = this.mysqlsb.getConnection();
			Statement st = con.createStatement();
			String sql = ("SELECT * FROM aicore_sentence WHERE sentence ='" + sentence + "';");
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				sentenceCont = new SentenceContainer(rs.getInt("id"), rs.getString("sentence"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sentenceCont;
	}

}
