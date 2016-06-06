package com.quizali.com.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginBackendBeanTest {

    private LoginBackendBean loginBackendBean;

    @Before
    public void setUp() throws Exception {
        this.loginBackendBean = new LoginBackendBean();
    }

    @Test
    public void testSetUserType() throws Exception {
        loginBackendBean.setUserType("TYPE");

        assertEquals("TYPE", loginBackendBean.getUserType());
    }

    @Test
    public void testDoLogin_whenStudent() throws Exception {
        loginBackendBean.setUserType("student");

        String page = loginBackendBean.doLogin();

        assertEquals("mainPageStudent", page);
    }
    @Test
    public void testDoLogin_whenTeacher() throws Exception {
        loginBackendBean.setUserType("teacher");

        String page = loginBackendBean.doLogin();

        assertEquals("mainPage", page);
    }
}