package com.incredibly_humble.app.tests;
import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.incredibly_humble.app.tests.mocks.HttpClientMock;
import com.incredibly_humble.app.util.Database;
import com.incredibly_humble.app.util.HttpClient;
import com.incredibly_humble.app.util.Login;
import com.incredibly_humble.app.util.impl.DatabaseLogin;
import com.incredibly_humble.app.util.impl.ScreenSwitch;
import org.junit.*;
import org.junit.Assert.*;

import com.incredibly_humble.models.User;
import static com.incredibly_humble.models.User.AccountType.USER;
import com.incredibly_humble.app.util.impl.exceptions.DatabaseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * a class to test the remote database
 */
public class RemoteDatabaseTest {
    static HttpClientMock httpMock;
    static com.incredibly_humble.app.util.impl.RemoteDatabase db;

    User test = new User("Maddie", "maddie@gatech.edu", "password", USER);
    User test2 = new User("Test", "test@gatech.edu", "password", USER);

    @BeforeClass
    public static void setUp(){
        Module m = new Module();
        Injector injector = Guice.createInjector(m);
        HttpClient c = injector.getInstance(HttpClient.class);
        httpMock = (HttpClientMock) c;
        db = new com.incredibly_humble.app.util.impl.RemoteDatabase();
        injector.injectMembers(db);
    }

    // Written by Noam
    @Test
    public void testGetWaterQualityReports(){
        db.getWaterQualityReports();
        assertNull("Param shouldn't be set in a get request.",httpMock.param);
        assertEquals("Get request should be made to the correct url.",httpMock.url,"http://localhost:4567/getWaterQualityReports");
        assertEquals("Request made should be a get request", httpMock.call, "GET");
    }

    //Written by Ashley
    @Test
    public void testGetSourceReports() {
        db.getWaterSourceReports();
        assertNull("Null param for get request", httpMock.param);
        assertEquals("request sent to correct url.", httpMock.url, "http://localhost:4567/getWaterSourceReports");
        assertEquals("should make a call to a get request", httpMock.call, "GET");
    }

    //Written by Maddie
    @Test(expected=Exception.class)
    public void testGetCurrentUser() throws Exception {
        db.addUser(test);
        User u = db.getCurrentUser();
        assertNull("null parameter should be used for get methods", httpMock.param);
        assertEquals("Should not be same person", false, u.equals(test2));
        assertEquals("Should be same person", true, u.equals(test));
        assertEquals("should make a call to a get request", httpMock.call, "GET");
        assertEquals("request sent to correct url.", httpMock.url, "http://localhost:4567/getCurrentUser");
    }

    private static class Module extends AbstractModule {
        @Override
        protected void configure() {
            bind(Login.class).to(DatabaseLogin.class);
            bind(ScreenSwitch.class);
            bind(Database.class).toInstance(new com.incredibly_humble.app.util.impl.RemoteDatabase());
            bind(HttpClient.class).toInstance(new HttpClientMock());
        }
    }
}

