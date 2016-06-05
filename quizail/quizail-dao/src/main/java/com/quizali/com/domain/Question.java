package com.quizali.com.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Objects.*;

@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private double points;

    @ManyToOne(fetch=FetchType.EAGER)
    private Quiz quiz;

    @OrderBy("id ASC")
    @OneToMany(mappedBy="question", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Option> options;

    public Question() {
        options = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", points=" + points +
                ", quiz=" + quiz +
                ", options=" + options +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Double.compare(question.points, points) == 0 &&
                equal(id, question.id) &&
                equal(content, question.content) &&
                equal(options, question.options);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, content, points, options);
    }
}
