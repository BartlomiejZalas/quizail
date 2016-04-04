package com.quizail.com;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class QuizController {
    @EJB
    private QuizDAO quizDAO;
    private Quiz quiz = new Quiz();
    private List<Quiz> quizList = new ArrayList<>();

    @PostConstruct
    public void init() {
        quizList = quizDAO.findQuizzes();
    }

    public String doCreateQuiz() {
        quiz = quizDAO.createQuiz(quiz);
        quizList = quizDAO.findQuizzes();
        return "listQuizzes.xhtml";
    }

    public QuizDAO getQuizDAO() {
        return quizDAO;
    }

    public void setQuizDAO(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}