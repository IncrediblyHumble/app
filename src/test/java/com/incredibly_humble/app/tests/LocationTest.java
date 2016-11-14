package test.java.com.incredibly_humble.app.tests;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.incredibly_humble.models.Location;

import org.junit.*;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * class to test Location class's methods
 */

public class LocationTest {
    private Location un = new Location(33.745, -82.932);
    private Location deux = new Location(27.319, 70.003);
    private Location trois = new Location(33.745, 70.003);

    //Written by Emily
    @Test
    public void testEquals(){
        assertTrue("Valid location should not equal null",!un.equals(null));
        assertTrue("Location should equal new location with same longitude and lattidude", un.equals(new Location(33.745, -82.932)));
        assertEquals("location should equal itself", un, un);
        assertTrue("location should not equal another location w/ different params", !un.equals(deux));
        assertTrue("locations with diff longitudes should not be equal", !un.equals(trois));
        assertTrue("locations w/ diff lattidueds should not be equal", !deux.equals(trois));
    }

}
