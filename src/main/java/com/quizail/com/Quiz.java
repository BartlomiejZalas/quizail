package com.quizail.com;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "findAllQuizzes", query = "SELECT q FROM Quiz q")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 2000)
    private String description;

    public Quiz() {
    }

    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
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
}