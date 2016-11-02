package ru.stqa.ol.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    
    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        app.selectGroup();
        // try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }
        app.deleteSelectedGroups();
        app.returtToGroupPage();
    }


}
