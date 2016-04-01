package com.quizail.com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class QuizDAOTest {

    private static final Quiz QUIZ = new Quiz("Quiz1", "description");
    private static final Long ID = 8l;

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Quiz> typedQuery;

    private QuizDAO quizDAO;

    @Before
    public void setUp() throws Exception {
        quizDAO = new QuizDAO(entityManager);
    }

    @Test
    public void noArgConstructor_shouldBePresentInEJB() throws Exception {
        QuizDAO quizDAO = new QuizDAO();
        assertNotNull(quizDAO);
    }

    @Test
    public void createQuiz_shouldCallPersistOperation() throws Exception {
        quizDAO.createQuiz(QUIZ);

        then(entityManager).should().persist(QUIZ);
    }

    @Test
    public void deleteQuiz_shouldCallDeleteOperation() throws Exception {
        given(entityManager.merge(QUIZ)).willReturn(QUIZ);

        quizDAO.deleteQuiz(QUIZ);

        then(entityManager).should().remove(QUIZ);
    }

    @Test
    public void findQuizzes_shouldCallNamedQueryForQuizClass() throws Exception {
        given(entityManager.createNamedQuery("findAllQuizzes", Quiz.class)).willReturn(typedQuery);

        quizDAO.findQuizzes();

        then(entityManager).should().createNamedQuery("findAllQuizzes", Quiz.class);
        then(typedQuery).should().getResultList();
    }

    @Test
    public void findQuizById_shouldCallFind() throws Exception {
        quizDAO.findQuizById(ID);

        then(entityManager).should().find(Quiz.class, ID);
    }


    @Test
    public void updateQuiz_shouldCallMerge() throws Exception {
        quizDAO.updateQuiz(QUIZ);

        then(entityManager).should().merge(QUIZ);
    }
}