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

    public static final String LIST_QUIZZES_PAGE = "listQuizzes";
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

    public void editQuiz(long quizId) {
        System.out.println("Edit"+quizId);

    }

    public void addQuestions(long quizId) {
        System.out.println("ADD Q"+quizId);

    }
}