package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by A546902 on 2016-12-14.
 */
public class ContactPhoneTests extends TestBase {

  @Test(enabled= true)
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())) );
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())) );
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())) );
  }

  public String cleaned(String phone) { // l5_m10: Vibros simvolov +,(,),- i probela iz stroki telefonov. Telefoni: Home="+7(911)", Mobile="22-22", Work="33 33 33"
    return phone.replaceAll("\\s", "").replaceAll("[-()]",""); //replaceAll- zamena simvolov na simvoli(v nawem slu4ae na pustuu stroku
    // \\s - zna4it luboi probel'nii simvol(probel, tabulqciq, perenos stroki)
    // v kvadr skobkah [-()] simvoli kot nado zamenit'/ubrat': -,( i )
    // nado stavit' "-" v na4ale t.k. [a-z] zna4it zamenit' vse bukvi(minus mezhdu). [-az]: zamenit' -,a,z
  }
}
