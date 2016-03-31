package com.quizail.com;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuizEJBRemote {
    List<Quiz> findQuizzes();

    Quiz findQuizById(Long id);

    Quiz createQuiz(Quiz book);

    void deleteQuiz(Quiz book);

    Quiz updateQuiz(Quiz book);
}