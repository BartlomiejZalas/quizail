package com.quizali.com.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "findAllQuizzes", query = "SELECT q FROM Quiz q")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 120, nullable = false)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(nullable = false)
    private int time;

    @OneToMany(mappedBy="quiz", fetch=FetchType.EAGER)
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public Quiz(String title, String description, Date date, int time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public void addQuestion(Question question) {
        if (!getQuestions().contains(question)) {
            getQuestions().add(question);
            if (question.getQuiz() != null) {
                question.getQuiz().getQuestions().remove(question);
            }
            question.setQuiz(this);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getGetQuestionsSize() {
        getQuestions();
        if (questions == null) {
            return 0;
        }
        return questions.size();
    }
}