package com.quizail.com.logic;

import com.quizali.com.dao.QuizDAO;
import com.quizali.com.domain.Quiz;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class QuizEJB implements QuizEJBRemote {

    @EJB
    private QuizDAO quizDAO;

    public QuizEJB() {
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizDAO.findQuizzes();
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizDAO.findQuizById(id);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizDAO.createQuiz(quiz);
    }

    @Override
    public void removeQuiz(Quiz quiz) {
        quizDAO.deleteQuiz(quiz);
    }

    QuizEJB(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }


}
