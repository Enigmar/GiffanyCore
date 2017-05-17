package de.linzn.aiCore.database.access;

import de.linzn.aiCore.database.DatabaseModule;
import de.linzn.aiCore.internal.container.SentenceContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSentence {
    private DatabaseModule mysqldb;

    public DBSentence(DatabaseModule mysqlsb) {
        this.mysqldb = mysqlsb;
    }

    public SentenceContainer getSentence(String sentence) {
        SentenceContainer sentenceCont = null;
        try {

            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_sentence WHERE sentence ='" + sentence + "';");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                sentenceCont = new SentenceContainer(rs.getInt("id"), rs.getString("sentence"));
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sentenceCont;
    }

}
