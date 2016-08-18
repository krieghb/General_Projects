package com.krieghb.general.projects.database.daos;


import com.krieghb.general.projects.utilities.Utilities;
import com.krieghb.general.projects.utilities.constants.ScriptConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by Wartree on 8/13/16.
 *
 */
public class TestDB {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDB.class);

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static String classDriver = "com.mysql.jdbc.Driver";

    private boolean isConnected = false;

    public TestDB() {

        this(classDriver);

    }

    public TestDB(String cDriver) {
        classDriver = cDriver;
        try {
            Class.forName(classDriver);
            LOGGER.info("Driver loaded!");
        }
        catch (ClassNotFoundException e) {
            LOGGER.info("Yikes, the driver '" + classDriver + "' does not exist in the classpath.");
        }
    }

    public boolean schemaExists(String databaseUrl, String options, String schemaName, String username, String password) {

        boolean schemaExists;

        try {
            //  Attempting to connect to table
            connect = DriverManager.getConnection(databaseUrl + schemaName + options, username, password);

            schemaExists = true;

            // This is not to open the db, just to check that it exists so closing.
            connect.close();
        }

        catch (SQLException e) {
            System.out.println("E:  " );
            e.printStackTrace();
            LOGGER.info("E:  " + Utilities.stackTraceToString(e));
            schemaExists = false;
        }


        return schemaExists;
    }


    /*
     *
     *
     */
    public boolean connectToDb (String url, String options, String username, String password) {

        LOGGER.info("Connecting database...");

        try {
            connect = DriverManager.getConnection(url + options, username, password);
            Properties prop = new Properties();

            isConnected = true;
            LOGGER.info("Database connected!");

        }
        catch (SQLException e) {
            isConnected = false;
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        return isConnected;
    }




    public void readFromDb(String mainSchemaTable) {

        // Statements allow to issue SQL queries to the database
        try {
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from " + mainSchemaTable);

            writeResultSet(resultSet);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void insertToDb(String mainSchemaTable, String[] columnList, String[] inputList) {

        int columnSize = columnList.length;
        int counter = 1;
        StringBuilder defaultQs = new StringBuilder("?");

        // PreparedStatements can use variables and are more efficient
        for (int i = 0; i < columnSize - 1; i++)
        {
            defaultQs.append(", ?");
        }

        LOGGER.info("");

        try{
            preparedStatement = connect.prepareStatement("insert into " + mainSchemaTable + " values (default, " + defaultQs + ")");

            for (int i = 0; i < columnSize; i++,counter++)
            {
                LOGGER.info(counter + "  " + columnList[i]);
                preparedStatement.setString(counter, inputList[i]);
            }
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new IllegalStateException("Failed to insert into the database!", e);
        }
    }



    public boolean createDatabase() {
        boolean didExecute = false;

        if (! isConnected) {
            LOGGER.info("Cannot create database, not connected to a database server.");

            return didExecute;
        }

        try {

            statement = connect.createStatement();

            didExecute = true;


            //  Since the sql string is a collection of executions (creating database and tables),
            //  Need to execute it as a batch by splitting based on the colon and adding each execution
            //  individually.
            StringTokenizer sqlTokenList = new StringTokenizer(ScriptConstants.CREATE_DATABASE_VTI, ";");
            String sqlStatement;

            while (sqlTokenList.hasMoreTokens()) {
                sqlStatement = sqlTokenList.nextToken();
                try {
                    statement.addBatch(sqlStatement);
                }
                catch (SQLException e) {
                    LOGGER.info("Bad batch addition");
                    e.printStackTrace();
                }
            }
            try {
                statement.executeBatch();
            }
            catch (SQLException e) {

                didExecute = false;
                LOGGER.info("Bad execution of batch.");
                e.printStackTrace();

            }

        }
        catch (SQLException e) {
            didExecute = false;

            LOGGER.info("Failed to create connection staetment (createStatment).");
            e.printStackTrace();

        }


        return didExecute;
    }





    /*
     *
     */
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String head = resultSet.getString("v_sng_c_head");
            String dur = resultSet.getString("v_sng_c_dur");
            String last = resultSet.getString("v_sng_c_comp-l");
            String first = resultSet.getString("v_sng_c_comp-f");



            LOGGER.info("%-35s", "Head:  " + head);
            LOGGER.info("      %-17s", "Dur:  " + dur);
            LOGGER.info("      %-30s", "Last:  " + last);
            LOGGER.info("      %-30s\n", "First:  " + first);



        }
    }




    public boolean disconnectFromDb() {

        try{
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }

            LOGGER.info("Database disconnected!");
            isConnected = true;
        }
        catch (SQLException e) {

            isConnected = false;
            throw new IllegalStateException("Failed to disconnect from the database!", e);

        }


        return isConnected;
    }

}

