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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoteDatabaseTest {
    static HttpClientMock httpMock;
    static com.incredibly_humble.app.util.impl.RemoteDatabase db;
    @BeforeClass
    public static void setUp(){
        Module m = new Module();
        Injector injector = Guice.createInjector(m);
        HttpClient c = injector.getInstance(HttpClient.class);
        httpMock = (HttpClientMock) c;
        db = new com.incredibly_humble.app.util.impl.RemoteDatabase();
        injector.injectMembers(db);
    }

    @Test
    public  void testGetUsers(){
        db.getWaterQualityReports();
        assertNull("Parm shouldn't be set in a get request.",httpMock.param);
        assertEquals("Get request should be made to the correct url.",httpMock.url,"http://localhost:4567/getWaterQualityReports");
        assertEquals("Request made should be a get request", httpMock.call, "GET");
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

