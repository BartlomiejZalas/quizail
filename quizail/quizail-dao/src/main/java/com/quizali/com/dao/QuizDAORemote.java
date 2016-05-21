package com.quizali.com.dao;

import com.quizali.com.domain.Quiz;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuizDAORemote {
    List<Quiz> findQuizzes();

    Quiz findQuizById(Long id);

    Quiz createQuiz(Quiz quiz);

    void deleteQuiz(Quiz quiz);

    Quiz updateQuiz(Quiz quiz);
}