package de.michel.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by xilent on 27.05.15.
 */
public class Page2 extends WebPage {
    long productId;

    public Page2(final PageParameters parameters) {

        add(new Label("pageTitle", "Welcome to Page 2"));
        if(parameters.getNamedKeys().contains("productId")) {
            try {
                productId = parameters.get("productId").toLong();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Product Id: " + productId);
        add(new Label("pageParam", String.valueOf(productId)));

    }
}
