package de.linzn.aiCore.database.access;

import de.linzn.aiCore.database.MySQLDatabase;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBKeyword {
    private MySQLDatabase mysqlsb;

    public DBKeyword(MySQLDatabase mysqlsb) {
        this.mysqlsb = mysqlsb;
    }

    public KeywordContainer getKeyword(ObjectContainer objectCon, String keyword) {
        KeywordContainer keywordCont = null;
        try {

            Connection con = this.mysqlsb.getConnection();
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
