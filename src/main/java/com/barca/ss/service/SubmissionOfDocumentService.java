package com.barca.ss.service;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.SubmissionOfDocument;
import com.barca.ss.domain.User;
import com.barca.ss.dao.SubmissionOfDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubmissionOfDocumentService {
    private Logger logger = LoggerFactory.getLogger(SubmissionOfDocumentService.class);

    @Autowired
    private SubmissionOfDocumentRepository submission;

    public List<SubmissionOfDocument> getUserSubmission(User user) {
        logger.info("get submission by user " + user);
        return submission.findAllByUser(user);
    }

    public SubmissionOfDocument save(SubmissionOfDocument submissionOfDocument){
        logger.info("save submission " + submissionOfDocument);
        return submission.save(submissionOfDocument);
    }

    public List<SubmissionOfDocument> getOrderedByAverageMarkSubmission(Speciality speciality) {
        logger.info("get by speciality ordered by average mark submissions " + speciality);
        return submission.findAllBySpecialityOrderByAverageMarkDesc(speciality);
    }

    public SubmissionOfDocument update(SubmissionOfDocument submissionOfDocument) {
        logger.info("update submission " + submissionOfDocument);
        return submission.save(submissionOfDocument);
    }

    public void deleteAllByUserAndIsEnteredValue(User user, Boolean flag) {
        logger.info("delete all submission where user = " + user + " \nand is entered value = " + flag);
        submission.deleteAllByUserAndIsEntered(user, flag);
    }
}
