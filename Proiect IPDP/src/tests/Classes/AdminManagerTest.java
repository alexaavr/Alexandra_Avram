package Classes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AdminManagerTest {
    AdminManager am = new AdminManager();
    Item it = new Item("Something");
    User us = new User("something wrong");

    @Test
    public void findItem() {

        assertEquals(am.findItem(it), false);
    }

    @Test
    public void displayItem() {
        assertEquals(am.displayItem(it), it.toString());
    }

    @Test
    public void findUser() {
        assertEquals(am.findUser(us), false);
    }
}