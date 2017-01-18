package ru.stqa.ol.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;
import ru.stqa.ol.addressbook.model.Groups;

import java.util.List;

/**
 * Created by A546902 on 2017-01-18.
 */
public class DbHelper {
  private final SessionFactory sessionFactory; //l7_m4 Novii klass.Ne nado nasledovanie HelperBase i ne nado peredavat' WebDriver t.k. ne budet vzaimodeistviq s browserom. No inicializirovat' nado:dobavlqem dbHelper v ApplicationManafer

  public DbHelper() { //l7_m4 delaem konstruktor v kot budet inicilizirovat'sq fabrika sessii. I kopiruem iz @BeforeClass iz HbConnectionTest, ubiraem catch i try(ni4ego perehvativat' ne budem,esli ne udalos' soedinit'sq s bd-pust test padaet)

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory(); //l7_m2 Alt+Enter SessionFactory->create field, sm.viwe
  }

  public Groups groups() { //l7_m4 delaem 2 metoda:polu4it' spisok grupp i kontaktov. Kopiruem @Test iz HbConnectionTest, ubiraem liwnee:
    Session session = sessionFactory.openSession();//l7_m2 s.Example 6 " Obtaining a list of entities" iz http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();//l7_m3 menqem zapros na ContactData)!Vozvrawaetsq spisok obektov tipa ContactData
    session.getTransaction().commit();
    session.close();
    return new Groups(result); //l7_m4 Alt+Enter->create constructor. Sm. klass Groups
  }

}
