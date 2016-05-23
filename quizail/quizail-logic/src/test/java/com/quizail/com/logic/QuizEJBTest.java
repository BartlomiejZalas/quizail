package com.quizail.com.logic;

import com.quizali.com.dao.QuizDAO;
import com.quizali.com.domain.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class QuizEJBTest {

    private static final Quiz QUIZ = new Quiz();

    @Mock
    private QuizDAO quizDAO;

    private QuizEJB quizEJB;

    @Before
    public void setUp() throws Exception {
        quizEJB = new QuizEJB(quizDAO);
    }

    @Test
    public void defaultConstructor_shouldBePresent() throws Exception {
        QuizEJB quizEJB = new QuizEJB();
        assertNotNull(quizEJB);
    }

    @Test
    public void testGetQuizzes() throws Exception {
        quizEJB.getQuizzes();

        then(quizDAO).should().findQuizzes();
    }

    @Test
    public void testGetQuiz() throws Exception {
        quizEJB.getQuiz(42L);

        then(quizDAO).should().findQuizById(42L);
    }

    @Test
    public void testCreateQuiz() throws Exception {
        quizEJB.createQuiz(QUIZ);

        then(quizDAO).should().createQuiz(QUIZ);
    }

    @Test
    public void testRemoveQuiz() throws Exception {
        quizEJB.removeQuiz(QUIZ);

        then(quizDAO).should().deleteQuiz(QUIZ);
    }
}