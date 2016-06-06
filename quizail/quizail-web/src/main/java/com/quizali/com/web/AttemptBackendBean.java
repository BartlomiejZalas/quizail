package com.quizali.com.web;

import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Option;
import com.quizali.com.domain.Question;
import com.quizali.com.domain.Quiz;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class AttemptBackendBean {

    @EJB
    private QuizEJBRemote quizEJB;

    private List<Quiz> quizList = new ArrayList<>();

    private Quiz quizToSolve;

    private Question currentQuestion;

    private int optionToSaveIndex = 0;

    private int currentQuestionIndex;

    private Map<Question, Option> results = new HashMap<>();

    private int totalQuestions;
    private String totalScore;

    @PostConstruct
    public void init() {
        quizList = quizEJB.getQuizzes();
    }

    public String doQuizList() {
        System.out.println("refreshing");
        quizList = quizEJB.getQuizzes();
        return "attemptQuizzes";
    }

    public String doQuiz() {
        Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long param = Long.parseLong(parameterMap.get("quizId"));
        this.quizToSolve = quizEJB.getQuiz(param);
        this.currentQuestionIndex = 0;
        this.currentQuestion = quizToSolve.getQuestions().get(currentQuestionIndex);
        this.totalQuestions = quizToSolve.getQuestions().size();
        this.results = new HashMap<>();
        return "attemptQuiz";
    }

    public void nextQuestion() {
        results.put(currentQuestion, currentQuestion.getOptions().get(optionToSaveIndex));
        currentQuestionIndex++;
        this.currentQuestion = quizToSolve.getQuestions().get(currentQuestionIndex);
    }

    public void previousQuestion() {
        results.put(currentQuestion, currentQuestion.getOptions().get(optionToSaveIndex));
        currentQuestionIndex--;
        this.currentQuestion = quizToSolve.getQuestions().get(currentQuestionIndex);
    }

    public String finishQuiz() {
        results.put(currentQuestion, currentQuestion.getOptions().get(optionToSaveIndex));

        double result = calculateScore();
        totalScore = String.format("%.2f", result) + "%";
        System.out.println(results);
        return "finishQuiz";
    }

    private double calculateScore() {
        double correctPoints = 0;
        for (Question question : results.keySet()) {
            if (results.get(question).getCorrect()) {
                correctPoints += question.getPoints();
            }
        }
        return (correctPoints / calculateAllPoints()) * 100.0;
    }

    private double calculateAllPoints() {
        double totalPoints = 0;
        for( Question q : quizToSolve.getQuestions()) {
            totalPoints += q.getPoints();
        }
        return totalPoints;
    }

    public QuizEJBRemote getQuizEJBRemote() {
        return quizEJB;
    }

    public void setQuizEJBRemote(QuizEJBRemote quizDAO) {
        this.quizEJB = quizDAO;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }


    public Quiz getQuizToSolve() {
        return quizToSolve;
    }

    public void setQuizToSolve(Quiz quizToSolve) {
        this.quizToSolve = quizToSolve;
    }


    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setOptionToSaveIndex(int optionToSave) {
        this.optionToSaveIndex = optionToSave;
    }

    public int getOptionToSaveIndex() {
        return optionToSaveIndex;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Map<Question, Option> getResults() {
        return results;
    }

    public void setResults(Map<Question, Option> results) {
        this.results = results;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getTotalScore() {
        return totalScore;
    }
}