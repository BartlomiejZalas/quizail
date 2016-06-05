package com.quizali.com.dao;

import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuestionDAORemote {
    Question createQuestion(Question quiz);

    Question updateQuiz(Question question);

    Question findQuestionById(Long id);

    void deleteQuestion(Question question);
}