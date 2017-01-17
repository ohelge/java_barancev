package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.Contacts;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by A546902 on 2017-01-12.
 */
public class DbConnecionTest { //l7_m1 new Class. Ne nado nasledovat' TestBase t.k. browser ne trebuetsq

  @Test
  public void testDbConnecion() { //l7_m1 Primer iz https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" + //l7_m1 Port 3306 standartnii, mozhno ne pisat'. Dalee /nazvanie bd.
              "user=root&password="); //rott, parol' pustoi

      Statement st = conn.createStatement(); //l7_m1 Pitaemsq izvle4 info iz db. Dlq etogo sozdaem objekt st iz klassa Statement
      ResultSet resultSet = st.executeQuery("select group_id, group_name, group_header, group_footer from  group_list"); //l7_m1 ResultSet eto analog kollekcii, nabor strok kot izvlekautsq iz tablici
      Groups groups = new Groups();
      while (resultSet.next()) { //poka est' elementi v resultSet
        groups.add(new GroupData().withId(resultSet.getInt("group_id")) //l7_m1 sm video na 09:57. resultSet ukazivaet na stroku v kajdii moment vremeni , a v celom eto vsq tabliza
                .withName(resultSet.getString("group_name"))
                .withHeader(resultSet.getString("group_header"))
                .withFooter(resultSet.getString("group_footer")));
      }
      resultSet.close(); // nado zakrit'
      //st.close();
      // conn.close();

      System.out.println(groups);

      ResultSet resultSetContact = st.executeQuery("select id, firstname from  addressbook where deprecated = '0000-00-00'"); //l7_m1 ResultSet eto analog kollekcii, nabor strok kot izvlekautsq iz tablici
      Contacts contacts = new Contacts();
      while (resultSetContact.next()) { //poka est' elementi v resultSet
        contacts.add(new ContactData().withId(resultSetContact.getInt("id")) //l7_m1 sm video na 09:57. resultSet ukazivaet na stroku v kajdii moment vremeni , a v celom eto vsq tabliza
                .withFirstname(resultSetContact.getString("firstname")));

      }
      resultSetContact.close(); // nado zakrit'
      st.close();
      System.out.println(contacts);
      conn.close();

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
