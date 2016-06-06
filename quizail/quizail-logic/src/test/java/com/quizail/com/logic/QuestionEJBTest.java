package com.quizail.com.logic;

import com.quizali.com.dao.QuestionDAO;
import com.quizali.com.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class QuestionEJBTest {

    public static final Question QUESTION = new Question();
    @Mock
    private QuestionDAO questionDAO;


    private QuestionEJB questionEJB;

    @Before
    public void setUp() throws Exception {
        questionEJB = new QuestionEJB(questionDAO);
    }

    @Test
    public void testCreateQuestion() throws Exception {
        questionEJB.createQuestion(QUESTION);

        then(questionDAO).should().createQuestion(QUESTION);
    }

    @Test
    public void testEditQuestion() throws Exception {
        questionEJB.editQuestion(QUESTION);

        then(questionDAO).should().updateQuiz(QUESTION);
    }

    @Test
    public void testGetQuestion() throws Exception {
        questionEJB.getQuestion(4l);

        then(questionDAO).should().findQuestionById(4l);
    }

    @Test
    public void testRemoveQuestion() throws Exception {
        questionEJB.removeQuestion(QUESTION);

        then(questionDAO).should().deleteQuestion(QUESTION);
    }
}