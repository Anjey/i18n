package com.barca.ss.service;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.SubjectValue;
import com.barca.ss.dao.SubjectValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubjectValueService {
    private Logger logger = LoggerFactory.getLogger(SubjectValueService.class);

    @Autowired
    private SubjectValueRepository subjectValueRepository;

    public List<SubjectValue> saveAll(Set<SubjectValue> subjectValue) {
        logger.info("save all subjects " + subjectValue);
        return subjectValueRepository.saveAll(subjectValue);
    }

    public Set<SubjectValue> getAllSubjectAndValueObjectsBySpeciality(Speciality speciality) {
        logger.info("get all subjects and value by speciality " + speciality);
        return subjectValueRepository.getAllBySpecialityOrderById(speciality);
    }
}
