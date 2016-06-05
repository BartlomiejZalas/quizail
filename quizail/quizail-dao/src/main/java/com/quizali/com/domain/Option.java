package com.quizali.com.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Option implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isCorrect;

    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;

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

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equal(id, option.id) &&
                Objects.equal(content, option.content) &&
                Objects.equal(isCorrect, option.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, content, isCorrect);
    }
}
