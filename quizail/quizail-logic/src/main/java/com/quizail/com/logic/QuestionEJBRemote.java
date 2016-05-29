package com.quizail.com.logic;

import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface QuestionEJBRemote {
    Question createQuestion(Question quiz);

}
