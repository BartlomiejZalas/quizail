package com.quizali.com.web;

import com.quizali.com.dao.QuizDAO;
import com.quizali.com.domain.Quiz;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class QuizBackendBean {
    @EJB
    private QuizDAO quizDAO;
    private Quiz quiz = new Quiz();
    private List<Quiz> quizList = new ArrayList<>();

    private UIComponent createButton;


    @PostConstruct
    public void init() {
        quizList = quizDAO.findQuizzes();
    }

    public String doCreateQuiz() {
        try {
            quiz = quizDAO.createQuiz(quiz);
            quizList = quizDAO.findQuizzes();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(e.getLocalizedMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(createButton.getClientId(context), message);
        }
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