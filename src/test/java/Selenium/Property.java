package Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class Property {
	Properties property;
	FileInputStream fs;
	
  @Test
  public void testProperty() throws IOException {
	  
	  fs = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
	  
	  property = new Properties();
	  property.load(fs);
	  System.out.println(property.getProperty("username"));
	  System.out.println(property.getProperty("password"));
	  System.out.println(property.getProperty("url"));
	  System.out.println(property.getProperty("browser"));
	  System.out.println(property.getProperty("version"));
	  
	  
  }
  
  
}
