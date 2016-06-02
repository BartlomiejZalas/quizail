package com.quizali.com.web;

import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class QuizBackendBean {

    public static final String LIST_QUIZZES_PAGE = "listQuizzes";
    public static final String EDIT_QUIZ_PAGE = "editQuiz";

    @EJB
    private QuizEJBRemote quizEJB;

    private Quiz quiz = new Quiz();
    private List<Quiz> quizList = new ArrayList<>();

    @PostConstruct
    public void init() {
        quizList = quizEJB.getQuizzes();
    }

    public String doCreateQuiz() {
        quizEJB.createQuiz(quiz);
        quizList = quizEJB.getQuizzes();
        quiz = new Quiz();

        return "listQuizzes";
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

    public String deleteQuiz(Long id) {
        quizEJB.removeQuiz(quizEJB.getQuiz(id));
        quizList = quizEJB.getQuizzes();
        return LIST_QUIZZES_PAGE;
    }

    public String editQuiz(long quizId) {
        quiz = quizEJB.getQuiz(quizId);
        return EDIT_QUIZ_PAGE;
    }

    public String saveEditedQuiz() {
        quizEJB.editQuiz(quiz);
        quizList = quizEJB.getQuizzes();
        return LIST_QUIZZES_PAGE;
    }
}