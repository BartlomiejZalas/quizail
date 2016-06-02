package com.quizali.com.web;

import com.quizail.com.logic.QuestionEJBRemote;
import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Option;
import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class QuestionsBackendBean {

    @EJB
    private QuizEJBRemote quizEJB;
    @EJB
    private QuestionEJBRemote questionEJB;

    private Question question = new Question();
    private List<Option> options = new ArrayList<>();

    private String newOptionContent;
    private Boolean newOptionCorrect;
    private Quiz quiz;

    @PostConstruct
    public void init() {
    }

    public String displayForm(Long quizId) {
        quiz = quizEJB.getQuiz(quizId);
        return "addQuestions";
    }

    public String doCreateQuestion() {
        Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long param = Long.parseLong(parameterMap.get("quizId"));
        Quiz quiz = quizEJB.getQuiz(param);

        quiz.addQuestion(question);
        quiz = quizEJB.editQuiz(quiz);


        List<Question> questions = quiz.getQuestions();
        Question addedQuestion = questions.get(questions.size() - 1);

        for (Option option : options) {
            option.setQuestion(addedQuestion);
        }
        addedQuestion.setOptions(options);
        questionEJB.editQuestion(addedQuestion);


        question = new Question();
        options = new ArrayList<>();
        newOptionContent = null;
        this.quiz = quizEJB.getQuiz(param);

        return displayForm(quiz.getId());
    }

    public void addOption() {
        Option option = new Option();
        option.setContent(newOptionContent);
        option.setCorrect(newOptionCorrect);
        options.add(option);
    }


    public QuizEJBRemote getQuizEJBRemote() {
        return quizEJB;
    }

    public void setQuizEJBRemote(QuizEJBRemote quizDAO) {
        this.quizEJB = quizDAO;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getNewOptionContent() {
        return newOptionContent;
    }

    public void setNewOptionContent(String newOptionContent) {
        this.newOptionContent = newOptionContent;
    }

    public Boolean getNewOptionCorrect() {
        return newOptionCorrect;
    }

    public void setNewOptionCorrect(Boolean newOptionCorrect) {
        this.newOptionCorrect = newOptionCorrect;
    }

    public void validateIfOptionsAdded(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (options.size() < 2) {
            String message = "You cannot create question without any options specified.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }
    }

    public void validateIfOptionsHasCorrectAnswer(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (computeCorrectAnswers() != 1) {
            String message = "At least one option has to be assigned as correct";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }
    }

    public void validateCorrectAnswerDuplication(FacesContext facesContext, UIComponent uiComponent, Object value) {
        Boolean isCorrect = (Boolean) value;
        if (computeCorrectAnswers() > 0 && isCorrect) {
            String message = "Only one option may be added as correct";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
        }
    }

    private int computeCorrectAnswers() {
        int correctAnswers = 0;
        for (Option option : options) {
            if (option.getCorrect()) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

}