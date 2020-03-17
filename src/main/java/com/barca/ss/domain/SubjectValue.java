package com.barca.ss.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject_and_value")
public class SubjectValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Subjects subjects;

    @Column
    private Double value;

    @ManyToOne/*(optional = false, cascade = CascadeType.ALL)*/
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    public SubjectValue() {    }

    public SubjectValue(Subjects subjects, Double value, Speciality speciality) {
        this.subjects = subjects;
        this.value = value;
        this.speciality = speciality;
    }

    public SubjectValue(Integer id, Subjects subjects, Double value, Speciality speciality) {
        this.id = id;
        this.subjects = subjects;
        this.value = value;
        this.speciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectValue that = (SubjectValue) o;
        return subjects == that.subjects &&
                value.equals(that.value) &&
                speciality.equals(that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjects, value, speciality);
    }

    @Override
    public String toString() {
        return "SubjectValue{" +
                "id=" + id +
                ", subjects=" + subjects +
                ", value=" + value +
                '}';
    }
}
