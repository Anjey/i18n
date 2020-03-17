package com.barca.ss.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "specialities")
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column(name = "number_of_students_for_entering")
    private Integer numberOfStudentsForEntering;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    private Set<SubjectValue> subjectsAndValue = new LinkedHashSet<>();

    @ManyToOne/*(optional = false, cascade = CascadeType.ALL)*/
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

//    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
//    private Set<SubmissionOfDocument> submissions = new HashSet<>();
//
//    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
//    private Set<User> students = new HashSet<>();

    public Speciality() {    }

    public Speciality(String name, Integer numberOfStudentsForEntering, /*Set<SubjectValue> subjectsAndValue,*/
                      University university/*, Set<SubmissionOfDocument> submissions, Set<User> students*/) {
        this.name = name;
        this.numberOfStudentsForEntering = numberOfStudentsForEntering;
//        this.subjectsAndValue = subjectsAndValue;
        this.university = university;
//        this.submissions = submissions;
//        this.students = students;
    }

    public Speciality(Integer id, String name, Integer numberOfStudentsForEntering, /*Set<SubjectValue> subjectsAndValue,*/
                      University university/*, Set<SubmissionOfDocument> submissions, Set<User> students*/) {
        this.id = id;
        this.name = name;
        this.numberOfStudentsForEntering = numberOfStudentsForEntering;
//        this.subjectsAndValue = subjectsAndValue;
        this.university = university;
//        this.submissions = submissions;
//        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SubjectValue> getSubjectsAndValue() {
        return subjectsAndValue;
    }

    public void setSubjectsAndValue(Set<SubjectValue> subjectsAndValue) {
        this.subjectsAndValue = subjectsAndValue;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Integer getNumberOfStudentsForEntering() {
        return numberOfStudentsForEntering;
    }

    public void setNumberOfStudentsForEntering(Integer numberOfStudentsForEntering) {
        this.numberOfStudentsForEntering = numberOfStudentsForEntering;
    }

//    public Set<SubmissionOfDocument> getSubmissions() {
//        return submissions;
//    }
//
//    public void setSubmissions(Set<SubmissionOfDocument> submissions) {
//        this.submissions = submissions;
//    }
//
//    public Set<User> getStudents() {
//        return students;
//    }
//
//    public void setStudents(Set<User> students) {
//        this.students = students;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return name.equals(that.name) &&
                numberOfStudentsForEntering.equals(that.numberOfStudentsForEntering) &&
//                subjectsAndValue.equals(that.subjectsAndValue) &&
                university.equals(that.university); /*&&*/
//                Objects.equals(submissions, that.submissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfStudentsForEntering/*, subjectsAndValue*/, university/*, submissions*/);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                "name=" + name +
                ", numberOfStudentsForEntering=" + numberOfStudentsForEntering +
//                ", subjectsAndValue=" + subjectsAndValue +
                '}';
    }
}
