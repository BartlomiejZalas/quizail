package com.quizali.com.dao;

import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class QuestionDAO implements QuestionDAORemote {

    @PersistenceContext(unitName = "mainDatabase")
    private EntityManager entityManager;

    public QuestionDAO() {
    }

    public QuestionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Question createQuestion(Question question) {
        entityManager.persist(question);
        return question;
    }

}