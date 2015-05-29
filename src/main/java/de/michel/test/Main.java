package de.michel.test;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Created by xilent on 27.05.15.
 */
public class Main extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return HelloWorld.class; //return default page
    }

}
