package com.barca.ss.dao;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.SubmissionOfDocument;
import com.barca.ss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionOfDocumentRepository extends JpaRepository<SubmissionOfDocument, Integer> {
    List<SubmissionOfDocument> findAllByUser(User user);
    List<SubmissionOfDocument> findAllBySpecialityOrderByAverageMarkDesc(Speciality speciality);
//    List<SubmissionOfDocument> findAllBySpeciality(Speciality speciality);
    void deleteAllByUserAndIsEntered(User user, Boolean isEntered);
//    List<SubmissionOfDocument> findAllBySpecialityOrderByAverageMarkDesc
}
