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
public class ContactPhoneTests extends TestBase {

  @Test(enabled= true)
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPnones(), equalTo(mergePhones(contactInfoFromEditForm))); //l5_m10 delaem vspomogat metod mergePhones

    /*l5_m10: Rabotaet esli vse 3 tel zapolneni ne null
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())) );
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())) );
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())) );*/
  }

  private String mergePhones(ContactData contact) { //l5_m11:Metod obratnih proverok: ne mojem razrezat' stroki/objekti, zna4it skleivaem ih
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s)-> ! s.equals("")) //Preobrazovivaem massiv v potok. Otsekaem(fil'truem) iz spiska te elementi kot = null("")
            .map(ContactPhoneTests::cleaned)  //Posle fil'tracii potoka primenqem funkciu kot vipolnqet ot4istku ot levih simvolov. map():primenit' k potoku kakyu-to funkciu i vernut' potok ix rezultatov etoi funkcii
            .collect(Collectors.joining("\n")); // Dalee sobiraem kollector s soedineniem s razdeleniem stroki. Resultat funkcii - stroka (return)

    /*l5_m11 Prostoi sposob do java 8
   String result = "";
    if (contact.getHomePhone() != null ) {
      result = result + contact.getHomePhone();
    } i tak dalee */
      }

  public static String cleaned(String phone) { // l5_m10: Vibros simvolov +,(,),- i probela iz stroki telefonov. Telefoni: Home="+7(911)", Mobile="22-22", Work="33 33 33"
    return phone.replaceAll("\\s", "").replaceAll("[-()]",""); //replaceAll- zamena simvolov na simvoli(v nawem slu4ae na pustuu stroku
    // \\s - zna4it luboi probel'nii simvol(probel, tabulqciq, perenos stroki)
    // v kvadr skobkah [-()] simvoli kot nado zamenit'/ubrat': -,( i )
    // nado stavit' "-" v na4ale t.k. [a-z] zna4it zamenit' vse bukvi(minus mezhdu). [-az]: zamenit' -,a,z
  }
}
