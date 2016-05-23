package com.quizali.com.web;

import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AttemptBackendBeanTest {

    private static final ArrayList<Quiz> QUIZZES_LIST = newArrayList(new Quiz(), new Quiz());

    @Mock
    private QuizEJBRemote quizEJB;

    private AttemptBackendBean attemptBackendBean;

    @Before
    public void setUp() throws Exception {
        this.attemptBackendBean = new AttemptBackendBean();
        this.attemptBackendBean.setQuizEJBRemote(quizEJB);
    }

    @Test
    public void testInit() throws Exception {
        given(quizEJB.getQuizzes()).willReturn(QUIZZES_LIST);

        this.attemptBackendBean.init();

        assertEquals(QUIZZES_LIST, this.attemptBackendBean.getQuizList());
    }
}