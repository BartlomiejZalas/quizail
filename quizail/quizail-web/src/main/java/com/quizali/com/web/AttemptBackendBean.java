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
public class AttemptBackendBean {

    @EJB
    private QuizEJBRemote quizEJB;

    private List<Quiz> quizList = new ArrayList<>();

    @PostConstruct
    public void init() {
        quizList = quizEJB.getQuizzes();
    }

    public QuizEJBRemote getQuizEJBRemote() {
        return quizEJB;
    }

    public void setQuizEJBRemote(QuizEJBRemote quizDAO) {
        this.quizEJB = quizDAO;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}