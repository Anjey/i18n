package com.barca.ss.service;

import com.barca.ss.domain.University;
import com.barca.ss.dao.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    private Logger logger = LoggerFactory.getLogger(UniversityService.class);

    @Autowired
    private UniversityRepository universityRepository;

    public University save(University university) {
        logger.info("save university " + university);
        return universityRepository.save(university);
    }

    public List<University> getAllUniversities() {
        logger.info("get all universities ");
        return universityRepository.findAll();
    }

    public University getUniversity(Integer id) {
        logger.info("get university by id " + id);
        return universityRepository.getOne(id);
    }
}
