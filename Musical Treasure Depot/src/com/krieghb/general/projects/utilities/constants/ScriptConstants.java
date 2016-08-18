package com.krieghb.general.projects.utilities.constants;

/**
 * Created by Wartree on 8/13/16.
 */
public class ScriptConstants {
    public final static String CREATE_DATABASE_VTI = "CREATE DATABASE `v_t_i` /*!40100 DEFAULT CHARACTER SET utf8 */;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_albums` (\n" +
            "  `albums_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `albums_name` varchar(45) NOT NULL,\n" +
            "  `albums_year` varchar(4) NOT NULL DEFAULT '????',\n" +
            "  `albums_numsongs` int(11) NOT NULL,\n" +
            "  `albums_songs` varchar(45) DEFAULT NULL COMMENT 'placeholder for song list (table)\\n',\n" +
            "  `albums_group` varchar(45) DEFAULT NULL COMMENT 'placeholder for group list (table)',\n" +
            "  PRIMARY KEY (`albums_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_albgroup_ref` (\n" +
            "  `albgrp_ref_albumid` int(11) NOT NULL,\n" +
            "  `albgrp_ref_groupid` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`albgrp_ref_albumid`,`albgrp_ref_groupid`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_albsng_ref` (\n" +
            "  `albsng_ref_albumid` int(11) NOT NULL,\n" +
            "  `albsng_ref_sngid` int(11) NOT NULL,\n" +
            "  `albsng_ref_sngnum` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`albsng_ref_albumid`,`albsng_ref_sngid`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_comp` (\n" +
            "  `comp_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `comp_name (last)` varchar(45) NOT NULL,\n" +
            "  `comp_name (first)` varchar(45) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`comp_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_group` (\n" +
            "  `group_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `group_name(last)` varchar(100) NOT NULL DEFAULT 'Various',\n" +
            "  `group_name(first)` varchar(45) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`group_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_set` (\n" +
            "  `set_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `set_name` varchar(45) NOT NULL,\n" +
            "  `set_numalbums` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`set_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_setalb_ref` (\n" +
            "  `setalb_ref_setid` int(11) NOT NULL,\n" +
            "  `setalb_ref_albumid` int(11) NOT NULL,\n" +
            "  `setalb_ref_albumnum` int(11) DEFAULT NULL,\n" +
            "  PRIMARY KEY (`setalb_ref_setid`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`sngcom_ref` (\n" +
            "  `sngcom_ref_sngid` int(11) NOT NULL,\n" +
            "  `sngcom_ref_compid` int(11) NOT NULL,\n" +
            "  PRIMARY KEY (`sngcom_ref_sngid`,`sngcom_ref_compid`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n" +

            "CREATE TABLE `v_t_i`.`t_vin_song` (\n" +
            "  `song_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `song_title` varchar(100) NOT NULL,\n" +
            "  `song_duration` varchar(5) DEFAULT '00:00',\n" +
            "  `song_genre` varchar(45) NOT NULL DEFAULT 'Misc',\n" +
            "  `song_comp` varchar(45) DEFAULT NULL COMMENT 'placehoder for composer list (table)',\n" +
            "  PRIMARY KEY (`song_id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"

            ;


}
