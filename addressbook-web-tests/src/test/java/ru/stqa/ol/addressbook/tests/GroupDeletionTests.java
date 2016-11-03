package ru.stqa.ol.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    
    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        // try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }


}
