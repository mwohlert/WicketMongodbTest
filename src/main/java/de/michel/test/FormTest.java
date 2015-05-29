package de.michel.test;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilent on 29.05.15.
 */
public class FormTest extends WebPage {
    int _counter;
    MongoClient mongoClient;
    MongoDatabase Wicket;
    MongoCollection Testcoll;
    //TextFields

    TextField userid;
    TextField password;



    public FormTest(final PageParameters parameters) {

        initDBConnection();

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);

        CompoundPropertyModel<FormBean> formModel = new CompoundPropertyModel<FormBean>(new FormBean());
        Form<FormBean> form = new Form<FormBean>("form",formModel)
        {
            @Override
            protected void onSubmit()
            {
                onSubmitHandler();
            }
        };

        userid = new TextField<String>("userid");
        form.add(userid);

        password = new TextField<String>("password");
        form.add(password);

        Button defaultSubmit = new Button("defaultSubmit"){
            @Override
            public void onSubmit() {
                onSubmitHandler();
            }
        };
        form.add(defaultSubmit);


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
        info("Form Submit: "+_counter++);
        List<BsonDocument> responseList = new ArrayList<BsonDocument>();
        Document userLoginquery = new Document();
        userLoginquery.put("user_id",userid.getModelObject());
        userLoginquery.put("password",password.getModelObject());
        Testcoll.find(userLoginquery).into(responseList);
        if(responseList.size() >0){
            info("Success");
            getRequestCycle().setResponsePage(AfterLogin.class);
        }
    }

    private void initDBConnection(){
        mongoClient = new MongoClient("localhost", 27017);
        Wicket = mongoClient.getDatabase("Wicket");
        Testcoll = Wicket.getCollection("Test", Document.class);
    }


    @Override
    protected void onBeforeRender()
    {
        _counter=0;
        super.onBeforeRender();
    }
}
