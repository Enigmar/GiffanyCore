package de.linzn.aiCore.database.access;

import de.linzn.aiCore.database.DatabaseModule;
import de.linzn.aiCore.internal.container.ObjectContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBObject {

    private DatabaseModule mysqldb;

    public DBObject(DatabaseModule mysqlsb) {
        this.mysqldb = mysqlsb;
    }

    public ObjectContainer getObject(String objectName) {
        ObjectContainer objectCont = null;
        try {

            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_object WHERE objectname ='" + objectName + "'");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                objectCont = new ObjectContainer(rs.getInt("id"), rs.getString("objectname"), rs.getString("class"));
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectCont;
    }

}
