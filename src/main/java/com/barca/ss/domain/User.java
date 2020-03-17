package com.barca.ss.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @ManyToOne/*(optional = true, cascade = CascadeType.ALL)*/
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    @Lob
    private String encodedImage;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

    public User() {    }

    public User(String firstName, String lastName, String email, String password, UserRole userRole,
                /*Set<Exams> exams, Set<SubmissionOfDocument> submissions,*/ Speciality speciality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
//        this.exams = exams;
//        this.submissions = submissions;
        this.speciality = speciality;
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
        this.userRole = user.userRole;
//        this.exams = user.exams;
//        this.submissions = user.submissions;
        this.speciality = user.speciality;
    }

    public User(Integer id, String firstName, String lastName, String email, String password, UserRole userRole,
                /*Set<Exams> exams, Set<SubmissionOfDocument> submissions, */Speciality speciality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
       /* this.exams = exams;
        this.submissions = submissions;*/
        this.speciality = speciality;
    }

//    public User(String firstName, String lastName, String email, String password,
//                UserRole userRole, Speciality speciality, String encodedImage, Boolean isAccepted) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.userRole = userRole;
//        this.speciality = speciality;
//        this.encodedImage = encodedImage;
//        this.isAccepted = isAccepted;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    //    public Set<Exams> getExams() {
//        return exams;
//    }
//
//    public void setExams(Set<Exams> exams) {
//        this.exams = exams;
//    }
//
//    public Set<SubmissionOfDocument> getSubmissions() {
//        return submissions;
//    }
//
//    public void setSubmissions(Set<SubmissionOfDocument> submissions) {
//        this.submissions = submissions;
//    }

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
        User user = (User) o;
        return firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                userRole == user.userRole /*&&
                Objects.equals(exams, user.exams) &&
                Objects.equals(submissions, user.submissions)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, userRole/*, exams, submissions*/);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
