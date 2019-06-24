package com.utils

import java.io.{File, FileInputStream}
import java.util.Properties

class PropertiesReader {

  def loadPropertiesByPath(filePath: String):Properties = {
    val props = new Properties();
    val fileInputStream = new FileInputStream(filePath);
    /*if( !filePath.exists()){
      throw new Exception("文件不存在，filePath:"+filePath);
    }*/
    props.load(fileInputStream);
    return props;
  }

  def loadDBProperties(fileName: String):Properties = {
    val props = this.loadPropertiesByPath((this.getClass.getClassLoader.getResource("") + "prop/"+fileName+".properties").substring(5));
    return props;
  }
}

object PropertiesReader{
  def main(args: Array[String]): Unit = {
    val tmp = new PropertiesReader();
    val props = tmp.loadPropertiesByPath((this.getClass.getClassLoader.getResource("") + "prop/database.properties").substring(5));
    //val props = tmp.loadProperties("E:/Lyne/myGit/exp/target/classes/prop/database.properties");
    println(props.getProperty("jdbc.master.url"));
  }
}
