package com.quizali.com.web;

import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class QuizBackendBeanTest {

    private static final ArrayList<Quiz> QUIZZES_LIST = newArrayList(new Quiz(), new Quiz());
    public static final Quiz QUIZ = new Quiz();

    @Mock
    private QuizEJBRemote quizEJB;

    private QuizBackendBean quizBackendBean;

    @Before
    public void setUp() throws Exception {
        this.quizBackendBean = new QuizBackendBean();
        this.quizBackendBean.setQuizEJB(quizEJB);
    }

    @Test
    public void testInit() throws Exception {
        given(quizEJB.getQuizzes()).willReturn(QUIZZES_LIST);

        this.quizBackendBean.init();

        assertEquals(QUIZZES_LIST, this.quizBackendBean.getQuizList());
    }

    @Test
    public void testDoCreateQuiz() throws Exception {
        this.quizBackendBean.setQuiz(QUIZ);

        String pageToRedirect = this.quizBackendBean.doCreateQuiz();

        then(quizEJB).should().createQuiz(QUIZ);
        then(quizEJB).should().getQuizzes();
        assertEquals("listQuizzes.xhtml", pageToRedirect);

    }
}