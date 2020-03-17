package com.barca.ss.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "submission_of_document")
public class SubmissionOfDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer priority;

    @Column(name = "is_entered")
    private Boolean isEntered;

    @Column(name = "average_mark")
    private Double averageMark;

    @ManyToOne/*(optional = false, cascade = CascadeType.ALL)*/
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    @ManyToOne/*(optional = true, cascade = CascadeType.ALL)*/
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Transient
    private Integer place;

    public SubmissionOfDocument() {    }

    public SubmissionOfDocument(Boolean isEntered, Double averageMark, Speciality speciality, User user) {
        this.isEntered = isEntered;
        this.averageMark = averageMark;
        this.speciality = speciality;
        this.user = user;
    }

    public SubmissionOfDocument(Integer id, Integer priority, Boolean isEntered, Double averageMark, Speciality speciality, User user) {
        this.id = id;
        this.priority = priority;
        this.isEntered = isEntered;
        this.averageMark = averageMark;
        this.speciality = speciality;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getEntered() {
        return isEntered;
    }

    public void setEntered(Boolean entered) {
        isEntered = entered;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionOfDocument that = (SubmissionOfDocument) o;
        return priority.equals(that.priority) &&
                speciality.equals(that.speciality) &&
                averageMark.equals(that.averageMark) &&
                user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, averageMark, speciality, user);
    }

    @Override
    public String toString() {
        return "SubmissionOfDocument{" +
                "id=" + id +
                ", priority=" + priority +
                ", isEntered=" + isEntered +
                ", averageMark=" + averageMark +
                ", place=" + place +
                '}';
    }
}
