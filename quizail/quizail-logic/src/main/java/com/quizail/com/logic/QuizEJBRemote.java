package com.quizail.com.logic;

import com.quizali.com.domain.Quiz;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuizEJBRemote {
    List<Quiz> getQuizzes();
    Quiz getQuiz(Long id);
    Quiz createQuiz(Quiz quiz);
    void removeQuiz(Quiz quiz);
}
