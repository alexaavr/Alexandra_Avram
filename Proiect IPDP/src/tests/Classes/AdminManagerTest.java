package Classes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AdminManagerTest {
    AdminManager am = new AdminManager();
    Item it = new Item("Something", 123, 123, 123);

    @Test
    public void findItem() {

        assertEquals(am.findItem(it), false);//////nu merge cum trb
    }

    @Test
    public void displayItem() {
        assertEquals(am.displayItem(it), it.toString());
    }

    @Test
    public void findUser() {
    }
}