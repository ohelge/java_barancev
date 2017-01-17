package ru.stqa.ol.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;
import ru.stqa.ol.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by A546902 on 2017-01-16.
 */
public class HibernateConnecionTest { //l7_m1 new Class. Ne nado nasledovat' TestBase t.k. browser ne trebuetsq
  private SessionFactory sessionFactory;
  @BeforeClass
  protected void setUp() throws Exception { //l7_m2 kopiruem primer iz "Example code" http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory(); //l7_m2 Alt+Enter SessionFactory->create field, sm.viwe
    }
    catch (Exception e) {
      e.printStackTrace(); //l7_m2 OBS!4tobi owibki vivodilos' na konsol' vivodim soobwenie ob owibke na konsol'
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test
  public void testHbConnection () {//l7_m2 sna4ala nado sozdat' config file sm. http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#tutorial-native. Ska4ivaem hibernate-tutorials.zip i v papke Annotation/src/test/resources berem hibernate.cfg.xml
    Session session = sessionFactory.openSession();//l7_m2 s.Example 6 " Obtaining a list of entities" iz http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
    session.beginTransaction();
    /*List<GroupData> resultGroup = session.createQuery( "from GroupData" ).list();//l7_m2 menqem zapros k bd. Event zamenqem na GroupData.OBS. qzik zaprosom ne SQL, a OQL(object query lang)!Vozvrawaetsq spisok obektov tipa GroupData
    for ( GroupData group : resultGroup ) { //l7_m2 OBS vmesto "Event" iz primera budet GroupData. Idem v GroupData i dobavlqem annotaciu @Entity..
      System.out.println( group );
    }*/
    List<ContactData> resultContact = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();//l7_m3 menqem zapros na ContactData)!Vozvrawaetsq spisok obektov tipa ContactData
    for ( ContactData contact : resultContact ) { //l7_m2 OBS vmesto "Event" iz primera budet GroupData. Idem v GroupData i dobavlqem annotaciu @Entity..
      System.out.println( contact ); //l7_m3 OBS!v baze mozhet bit' nogo kontaktov a v webe men'we: v webe vidni kontakti u kogo pole "depricated" = "0000-00-00 00:00:00" data udaleniq. Sm viwe.
    }
    session.getTransaction().commit();
    session.close();
  }
}
