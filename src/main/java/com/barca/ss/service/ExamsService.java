package com.barca.ss.service;

import com.barca.ss.domain.Exams;
import com.barca.ss.domain.Subjects;
import com.barca.ss.domain.User;
import com.barca.ss.dao.ExamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ExamsService {
    private Logger logger = LoggerFactory.getLogger(ExamsService.class);

    @Autowired
    private ExamsRepository examsRepository;

   public List<Exams> saveAllExams(Set<Exams> exams) {
       logger.info("save all user exams " + exams);
       return examsRepository.saveAll(exams);
   }

   public Set<Exams> getAllExamsByUser(User user) {
       logger.info("get all exams by user " + user);
       return examsRepository.getAllByUser(user);
   }

   public Exams getCertificateForAccepting(User user, Subjects subjects) {
       logger.info("get user certificate for accepting " + user + "\n" + subjects);
       return examsRepository.getByUserAndSubject(user, subjects);
   }

   public void deleteUserExams(User user) {
       logger.info("delete exams by user " + user);
       examsRepository.deleteAllByUserId(user.getId());
   }
}
