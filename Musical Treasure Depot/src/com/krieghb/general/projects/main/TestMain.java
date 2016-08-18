package com.krieghb.general.projects.main;

import com.krieghb.general.projects.database.daos.TestDB;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wartree on 8/13/16.
 *
 */
public class TestMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMain.class);
//    private static final Logger LOGGER = LogManager.getLogger(TestMain.class);

    public static void main( String[] args ) {


        LOGGER.info( "Hello World!\n\n" );



        String mainSchema = "v_t_i";
        String mainTable = "v_sng";

        String databaseUrl = "jdbc:mysql://localhost:3306/";
//        String dbOptions = "?autoReconnect=true&useSSL=true";
        String dbOptions = "?useSSL=true";
        String username = "root";
        String password = "root123";
//        String column_list[] = new String[] {"v_sng_c_head", "v_sng_c_dur", "v_sng_c_comp-l", "v_sng_c_comp-f"};
//        String input_list[] = new String[] {"Test Header", "13:26", "Fours", "Hubert"};




        TestDB newConn =  new TestDB();
        newConn.connectToDb(databaseUrl, dbOptions, username, password);

        if (newConn.schemaExists(databaseUrl, dbOptions, mainSchema, username, password)) {
            LOGGER.info("Yea, the schema '" + mainSchema + "' exists!");
        }
        else {
            LOGGER.info("Aww, the schema '" + mainSchema + "' does not exist, creating . . .");
            newConn.createDatabase();

            if (newConn.schemaExists(databaseUrl, dbOptions, mainSchema, username, password)) {
                LOGGER.info("Yea, the schema '" + mainSchema + "' now exists!");
            }
            else {
                LOGGER.info("Aww, the schema '" + mainSchema + "' failed to be created.");
            }

        }


        newConn.disconnectFromDb();


        LOGGER.info("");
        LOGGER.info("");
        LOGGER.info("Good bye!\n");

    }
}
