package Selenium;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class Assignment2 {
  @Test(dataProvider = "credentials")
  public void ParameterUsingDataProvider(String username, String password) {
	  System.out.println(username);
	  System.out.println(password);
  }

  @DataProvider(name = "credentials")
  public Object[][] getData() {
    Object[][] data = new Object[3][2];
    
    data[0][0] = "user1";
    data[0][1] = "user@123";
    
    data[1][0] = "user2";
    data[1][1] = "user2@123";
    
    data[2][0] = "user3";
    data[2][1] = "user3@123";
    
    return data;
  }
}
