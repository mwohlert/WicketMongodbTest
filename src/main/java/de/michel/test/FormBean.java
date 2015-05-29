package de.michel.test;

import java.io.Serializable;

/**
 * Created by xilent on 29.05.15.
 */
public class FormBean implements Serializable {
    String _userid;
    String _password;
    boolean _brony;

    public String getUserid(){
        return _userid;
    }

    public void setUserid(String userid){
        _userid = userid;
    }

    public String getPassword(){
        return _password;
    }

    public void setPassword(String passwordInput){
        _password = passwordInput;
    }

    public boolean getBrony(){
        return _brony;
    }

    public void setBrony(boolean brony){
        _brony = brony;
    }
}
