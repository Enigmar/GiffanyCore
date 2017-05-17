package de.linzn.aiCore.database.insert;

import de.linzn.aiCore.database.DatabaseModule;
import de.linzn.aiCore.internal.container.KeywordContainer;
import de.linzn.aiCore.internal.container.ObjectContainer;
import de.linzn.aiCore.internal.container.ResultContainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInsert {
    private DatabaseModule mysqldb;

    public DBInsert(DatabaseModule mysqldb) {
        this.mysqldb = mysqldb;
    }

    public ObjectContainer insertObject(String objectname, String classname) {
        ObjectContainer objectCont = null;
        try {

            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_object WHERE objectname ='" + objectname + "'");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                objectCont = new ObjectContainer(rs.getInt("id"), rs.getString("objectname"), rs.getString("class"));
            } else {
                sql = ("insert into aicore_object (objectname, class) values ('" + objectname + "', '" + classname
                        + "')");
                st.execute(sql);
                sql = ("SELECT * FROM aicore_object WHERE objectname ='" + objectname + "'");
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    objectCont = new ObjectContainer(rs.getInt("id"), rs.getString("objectname"),
                            rs.getString("class"));
                }
            }
            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectCont;
    }

    public KeywordContainer insertKeyword(ObjectContainer objectCon, String keyword, String function) {
        KeywordContainer keywordCont = null;
        try {

            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_keyword WHERE objectid ='" + objectCon.objectID + "' AND keyword ='"
                    + keyword + "';");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                keywordCont = new KeywordContainer(rs.getInt("id"), rs.getInt("objectid"), rs.getString("keyword"),
                        rs.getString("function"));
            } else {
                sql = ("insert into aicore_keyword (objectid, keyword, function) values ('" + objectCon.objectID
                        + "', '" + keyword + "', '" + function + "')");
                st.execute(sql);
                sql = ("SELECT * FROM aicore_keyword WHERE objectid ='" + objectCon.objectID + "' AND keyword ='"
                        + keyword + "';");
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    keywordCont = new KeywordContainer(rs.getInt("id"), rs.getInt("objectid"), rs.getString("keyword"),
                            rs.getString("function"));
                }
            }

            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keywordCont;
    }

    public ResultContainer insertResultObject(ObjectContainer objectCon, KeywordContainer keywordCon,
                                              String resultText) {
        ResultContainer resultCont = null;
        try {

            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_result WHERE value ='" + resultText + "';");
            ResultSet rs = st.executeQuery(sql);
            int resultID = 0;
            if (rs.next()) {
                resultID = rs.getInt("id");
            } else {
                sql = ("insert into aicore_result (value) values ('" + resultText + "')");
                st.execute(sql);

                sql = ("SELECT * FROM aicore_result WHERE value ='" + resultText + "';");
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    resultID = rs.getInt("id");
                }
            }

            sql = ("SELECT * FROM aicore_resultindex WHERE objectid ='" + objectCon.objectID + "' AND keywordid ='"
                    + keywordCon.keywordID + "';");
            rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Already exist??? What happend");
            } else {
                sql = ("insert into aicore_resultindex (objectid, keywordid, resultid) values ('" + objectCon.objectID
                        + "', '" + keywordCon.keywordID + "', '" + resultID + "')");
                st.execute(sql);
            }

            sql = ("SELECT * FROM aicore_result WHERE id ='" + resultID + "';");
            rs = st.executeQuery(sql);

            if (rs.next()) {
                resultCont = new ResultContainer(objectCon.objectID, keywordCon.keywordID, rs.getString("value"));
            }

            // Release connection.
            this.mysqldb.releaseConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultCont;
    }

    public ResultContainer insertResultText(String sentence, String resultText) {
        ResultContainer resultCont = null;
        try {
            int sentenceID = 0;
            Connection con = this.mysqldb.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM aicore_sentence WHERE sentence ='" + sentence + "';");
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                sql = ("insert into aicore_sentence (sentence) values ('" + sentence + "')");
                st.execute(sql);
            }
            sql = ("SELECT * FROM aicore_sentence WHERE sentence ='" + sentence + "';");
            rs = st.executeQuery(sql);
            if (rs.next()) {
                sentenceID = rs.getInt("id");
            }

            int resultID = 0;
            sql = ("SELECT * FROM aicore_result WHERE value ='" + resultText + "';");
            rs = st.executeQuery(sql);
            if (!rs.next()) {
                sql = ("insert into aicore_result (value) values ('" + resultText + "')");
                st.execute(sql);
            }
            sql = ("SELECT * FROM aicore_result WHERE value ='" + resultText + "';");
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultID = rs.getInt("id");
            }

            sql = ("SELECT * FROM aicore_resultindex WHERE sentenceid ='" + sentenceID + "' AND resultid ='" + resultID
                    + "';");
            rs = st.executeQuery(sql);
            if (!rs.next()) {
                sql = ("insert into aicore_resultindex (sentenceid, resultid) values ('" + sentenceID + "', '"
                        + resultID + "')");
                st.execute(sql);
            }

            sql = ("SELECT * FROM aicore_result WHERE id ='" + resultID + "';");
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
