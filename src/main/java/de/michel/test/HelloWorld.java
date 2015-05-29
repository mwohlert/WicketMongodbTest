package de.michel.test;


import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by xilent on 27.05.15.
 */
public class HelloWorld extends WebPage {

    public HelloWorld(final PageParameters parameters) {

        add(new Label("message", "Hello World, Wicket. This is a Test"));

    }
}
