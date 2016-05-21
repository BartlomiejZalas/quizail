package com.quizali.com.web;

import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class QuizBackendBean {

    @EJB
    private QuizEJBRemote quizEJB;

    private Quiz quiz = new Quiz();
    private List<Quiz> quizList = new ArrayList<>();

    @PostConstruct
    public void init() {
        quizList = quizEJB.getQuizzes();
    }

    public String doCreateQuiz() {
        quiz = quizEJB.createQuiz(quiz);
        quizList = quizEJB.getQuizzes();
        return "listQuizzes.xhtml";
    }

    public QuizEJBRemote getQuizEJB() {
        return quizEJB;
    }

    public void setQuizEJB(QuizEJBRemote quizEJB) {
        this.quizEJB = quizEJB;
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