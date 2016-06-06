package com.quizail.com.logic;

import com.quizali.com.dao.QuestionDAO;
import com.quizali.com.dao.QuizDAO;
import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

import static com.sun.corba.se.impl.util.Utility.clearCaches;

@Stateless
@Local
public class QuestionEJB implements QuestionEJBRemote {

    @EJB
    private QuestionDAO questionDAO;

    public QuestionEJB() {
    }

    public QuestionEJB(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionDAO.createQuestion(question);
    }

    @Override
    public Question editQuestion(Question question) {
        return questionDAO.updateQuiz(question);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionDAO.findQuestionById(id);
    }

    @Override
    public void removeQuestion(Question question) {
        questionDAO.deleteQuestion(question);
        clearCaches();
    }


}
