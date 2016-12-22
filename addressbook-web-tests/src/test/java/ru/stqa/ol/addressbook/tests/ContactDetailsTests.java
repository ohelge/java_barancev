package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ol.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by A546902 on 2016-12-14.
 */
public class ContactDetailsTests extends TestBase {

  @Test(enabled = true)
  public void testContactDetails() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPnones(), equalTo(mergePhones(contactInfoFromEditForm))); //l5_m10 delaem vspomogat metod mergePhones
    assertThat(cleanedAddress(contact.getAddress()), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    /*l5_m10: Rabotaet esli vse 3 tel zapolneni ne null
    assertThat(contact.getHomePhone(), equalTo(cleanedPnones(contactInfoFromEditForm.getHomePhone())) );
    assertThat(contact.getMobilePhone(), equalTo(cleanedPnones(contactInfoFromEditForm.getMobilePhone())) );
    assertThat(contact.getWorkPhone(), equalTo(cleanedPnones(contactInfoFromEditForm.getWorkPhone())) );*/
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactDetailsTests::cleanedEmails)
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) { //l5_m11:Metod obratnih proverok: ne mojem razrezat' stroki/objekti, zna4it skleivaem ih
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals("")) //Preobrazovivaem massiv v potok. Otsekaem(fil'truem) iz spiska te elementi kot = null("")
            .map(ContactDetailsTests::cleanedPnones)  //Posle fil'tracii potoka primenqem funkciu kot vipolnqet ot4istku ot levih simvolov. map():primenit' k potoku kakyu-to funkciu i vernut' potok ix rezultatov etoi funkcii
            .collect(Collectors.joining("\n")); // Dalee sobiraem kollector s soedineniem s razdeleniem stroki. Resultat funkcii - stroka (return)

    /*l5_m11 Prostoi sposob do java 8
   String result = "";
    if (contact.getHomePhone() != null ) {
      result = result + contact.getHomePhone();
    } i tak dalee */
  }
public static String cleanedEmails (String email) {
  return email.replaceAll("\\s", "");
}
  public static String cleanedPnones(String phone) { // l5_m10: Vibros simvolov +,(,),- i probela iz stroki telefonov. Telefoni: Home="+7(911)", Mobile="22-22", Work="33 33 33"
    return phone.replaceAll("\\s", "").replaceAll("[-()]", ""); //replaceAll- zamena simvolov na simvoli(v nawem slu4ae na pustuu stroku
    // \\s - zna4it luboi probel'nii simvol(probel, tabulqciq, perenos stroki)
    // v kvadr skobkah [-()] simvoli kot nado zamenit'/ubrat': -,( i )
    // nado stavit' "-" v na4ale t.k. [a-z] zna4it zamenit' vse bukvi(minus mezhdu). [-az]: zamenit' -,a,z
  }

  public static String cleanedAddress(String address) {
    return address.replaceAll("\\s", "").replaceAll("[\n]", "");
  }
}
