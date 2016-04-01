package com.quizail.com;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class QuizDAO implements QuizDAORemote {

    @PersistenceContext(unitName = "mainDatabase")
    private EntityManager entityManager;

    public QuizDAO() {
    }

    public QuizDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Quiz> findQuizzes() {
        TypedQuery<Quiz> query = entityManager.createNamedQuery("findAllQuizzes", Quiz.class);
        return query.getResultList();
    }

    public Quiz findQuizById(Long id) {
        return entityManager.find(Quiz.class, id);
    }

    public Quiz createQuiz(Quiz quiz) {
        entityManager.persist(quiz);
        return quiz;
    }

    public void deleteQuiz(Quiz quiz) {
        entityManager.remove(entityManager.merge(quiz));
    }

    public Quiz updateQuiz(Quiz quiz) {
        return entityManager.merge(quiz);
    }
}