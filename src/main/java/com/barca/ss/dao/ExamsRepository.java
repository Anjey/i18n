package com.barca.ss.dao;

import com.barca.ss.domain.Exams;
import com.barca.ss.domain.Subjects;
import com.barca.ss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ExamsRepository extends JpaRepository<Exams, Integer> {
    Set<Exams> getAllByUser(User user);
    Exams getByUserAndSubject(User user, Subjects subjects);
    void deleteAllByUserId(Integer id);
}
