//package com.barca.ss.domain;
//
//import java.util.Objects;
//
//
//public class UserEntered {
//    private Speciality speciality;
//    private User user;
//
//    public UserEntered() {    }
//
//    public UserEntered(Speciality speciality, User user) {
//        this.speciality = speciality;
//        this.user = user;
//    }
//
//    public Speciality getSpeciality() {
//        return speciality;
//    }
//
//    public void setSpeciality(Speciality speciality) {
//        this.speciality = speciality;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserEntered that = (UserEntered) o;
//        return speciality.equals(that.speciality) &&
//                user.equals(that.user);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(speciality, user);
//    }
//
//    @Override
//    public String toString() {
//        return "UserEntered{" +
//                "speciality=" + speciality +
//                ", user=" + user +
//                '}';
//    }
//}
