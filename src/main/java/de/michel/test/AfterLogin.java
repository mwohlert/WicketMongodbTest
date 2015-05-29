package de.michel.test;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilent on 29.05.15.
 */
public class AfterLogin extends WebPage {

    MongoClient mongoClient;
    MongoDatabase Wicket;
    MongoCollection Testcoll;

    //TextFields

    TextField userid;
    TextField password;

    //BronySelect Values
    private static final List<String> bronyState = new ArrayList<String>(){{
        add("Sure bro");
        add("Dude wtf nooo");
    }};
    String defaultSelected = "Sure bro";

    public AfterLogin(){
        initDBConnection();


        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        DropDownChoice<String> bronyChoice = new DropDownChoice<String>(
                "brony", new PropertyModel<String>(this, "defaultSelected"), bronyState);


        CompoundPropertyModel<FormBean> formModel = new CompoundPropertyModel<FormBean>(new FormBean());
        Form<FormBean> form = new Form<FormBean>("form",formModel)
        {
           /* @Override
            protected void onSubmit()
            {
                onSubmitHandler();
            }*/
        };


        form.add(bronyChoice);

        userid = new TextField<String>("userid");
        form.add(userid);

        password = new TextField<String>("password");
        form.add(password);



        Button submit = new Button("submit"){
            @Override
            public void onSubmit() {
                onSubmitHandler();
            }
        };
        form.add(submit);



        add(form);

    }


    final protected void onSubmitHandler(){
        Document insertUserCredentials = new Document();
        insertUserCredentials.put("user_id", userid.getModelObject());
        insertUserCredentials.put("password", password.getModelObject());
       /* DBCursor cursor = Testcoll.find(userLoginquery);
        if(cursor.count() >0){
            info("Success");
            getRequestCycle().setResponsePage(AfterLogin.class);
        }*/
        if(defaultSelected.equals(bronyState.get(0))){
            insertUserCredentials.put("brony",true);
            info("You're a brony");
        }else if (defaultSelected.equals(bronyState.get(1))){
            insertUserCredentials.put("brony", false);
            info("You're not a brony");
        }
        Testcoll.insertOne(insertUserCredentials);

        info("Successfully inserted user entry");
    }

    private void initDBConnection(){
        mongoClient = new MongoClient("localhost", 27017);
        Wicket = mongoClient.getDatabase("Wicket");
        Testcoll = Wicket.getCollection("Test", Document.class);
    }
}
