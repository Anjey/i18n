package com.barca.ss.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

//    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
//    private Set<Speciality> specialities = new HashSet<>();

    public University() {    }

    public University(String name/*, Set<Speciality> specialities*/) {
        this.name = name;
//        this.specialities = specialities;
    }

    public University(Integer id, String name/*, Set<Speciality> specialities*/) {
        this.id = id;
        this.name = name;
//        this.specialities = specialities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Speciality> getSpecialities() {
//        return specialities;
//    }
//
//    public void setSpecialities(Set<Speciality> specialities) {
//        this.specialities = specialities;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
