package de.linzn.aiCore.database.access;

import de.linzn.aiCore.database.DatabaseModule;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;
import de.linzn.aiCore.internal.container.ResultContainer;
import de.linzn.aiCore.internal.container.SentenceContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResult {

    private DatabaseModule mysqldb;

    public DBResult(DatabaseModule mysqldb) {
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

            // Release connection.
            this.mysqldb.releaseConnection(con);
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
            // Release connection.
            this.mysqldb.releaseConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCont;
    }

}
