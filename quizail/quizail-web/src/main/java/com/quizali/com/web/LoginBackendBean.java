package com.quizali.com.web;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBackendBean {

    private String userType;



    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public String doLogin() {
        if (userType.equals("teacher")) {
            return "mainPage";
        } else {
            return "mainPageStudent";
        }
    }
}
