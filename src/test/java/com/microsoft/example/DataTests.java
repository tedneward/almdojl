package com.microsoft.example;

import java.sql.*;
import javax.sql.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.microsoft.example.models.*;

public class DataTests {
  @Test
  public void seeIfFredCanLogin() {
	 assertEquals(true, DataAccess.loginSuccessful("fred", "fredpassword"));
  }
  
  @Test
  public void viewFredFares() {
    try (ResultSet rs = DataAccess.employeeFares(1)) {
      while (rs.next()) {
        // Not much to test here, other than to make sure it's Fred's fare
        assertEquals(1, rs.getInt("emp_id"));
      }
    }
    catch (SQLException sqlEx) {
      fail(sqlEx.toString());
    }
  }
}