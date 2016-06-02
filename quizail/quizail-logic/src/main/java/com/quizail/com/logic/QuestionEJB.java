package com.quizail.com.logic;

import com.quizali.com.dao.QuestionDAO;
import com.quizali.com.dao.QuizDAO;
import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class QuestionEJB implements QuestionEJBRemote {

    @EJB
    private QuestionDAO questionDAO;

    public QuestionEJB() {
    }

    @Override
    public Question createQuestion(Question question) {
        return questionDAO.createQuestion(question);
    }

    @Override
    public Question editQuestion(Question question) {
        return questionDAO.updateQuiz(question);
    }


}
