package ru.stqa.ol.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    
    @Test
    public void testGroupDeletion() {
        gotoGroupPage();
        selectGroup();
        try { Thread.sleep(5000l); } catch (Exception e) { throw new RuntimeException(e); }
        deleteSelectedGroups();
        returtToGroupPage();
    }


}
