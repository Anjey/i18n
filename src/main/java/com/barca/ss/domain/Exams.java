package com.barca.ss.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exams")
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    private Subjects subject;

    @Column
    private Double mark;

    @ManyToOne(optional = true/*, cascade = CascadeType.REMOVE*/)
    @JoinColumn(name = "user_id")
    private User user;

    public Exams() {    }

    public Exams(Subjects subject, Double mark, User user) {
        this.subject = subject;
        this.mark = mark;
        this.user = user;
    }

    public Exams(Integer id, Subjects subject, Double mark, User user) {
        this.id = id;
        this.subject = subject;
        this.mark = mark;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exams exams = (Exams) o;
        return subject == exams.subject &&
                mark.equals(exams.mark) &&
                user.equals(exams.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, mark, user);
    }

    @Override
    public String toString() {
        return "Exams{" +
                "id=" + id +
                ", subject=" + subject +
                ", mark=" + mark +
                '}';
    }
}
