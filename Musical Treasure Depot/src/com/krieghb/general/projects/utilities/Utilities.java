package com.krieghb.general.projects.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wartree on 8/17/16.
 *
 */
public class Utilities {
    private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);



    public static String stackTraceToString(Exception e) {
        StringBuilder sb = new StringBuilder();

        for (StackTraceElement st : e.getStackTrace()) {
            sb.append("\t").append(st).append("\n");
//            LOGGER.info("ST:  {}", st);
        }




        return sb.toString();
    }

}
