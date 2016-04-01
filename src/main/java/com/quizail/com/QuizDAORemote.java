package com.quizail.com;

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