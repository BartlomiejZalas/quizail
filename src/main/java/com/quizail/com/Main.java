package com.quizail.com;

import javax.ejb.EJB;

public class Main {
    @EJB
    private static QuizDAORemote quizEJB;

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.setTitle("The Hitchhiker's Guide to the Galaxy");
        quiz.setDescription("Scifi quiz created by Douglas Adams");
        quiz = quizEJB.createQuiz(quiz);
        quiz.setTitle("H2G2");
        quizEJB.updateQuiz(quiz);

//        quizEJB.deleteQuiz(quiz);
    }
}