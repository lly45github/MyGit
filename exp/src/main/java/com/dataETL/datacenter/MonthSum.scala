package com.dataETL

import java.util.Properties

import com.utils.PropertiesReader
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object MonthSum {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("monthSum").setMaster("local");
    val sc = new SparkContext(conf);
    sc.setLogLevel("WARN");

    val sqlContext = new SQLContext(sc);
    val propReader = new PropertiesReader();
    val dbProp = propReader.loadDBProperties("database");
    val jdbcProp = new Properties();
    jdbcProp.put("user", dbProp.getProperty("jdbc.master.username"));
    jdbcProp.put("password", dbProp.getProperty("jdbc.master.password"));
    val url = dbProp.getProperty("jdbc.master.url");
    val testDB = sqlContext.read.jdbc(url, "accept_association_delete_record", jdbcProp);
    testDB.show();
    /*val jdbcDF = sqlContext.read.format("jdbc").options(Map("url" -> url, "user" -> "root", "password" -> "qaz2wsx", "dbtable" -> "accept_association_delete_record")).load();
    jdbcDF.show();*/
  }

}
