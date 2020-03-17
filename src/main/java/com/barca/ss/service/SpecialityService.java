package com.barca.ss.service;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.University;
import com.barca.ss.dao.SpecialityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {
    private Logger logger = LoggerFactory.getLogger(SpecialityService.class);

    @Autowired
    private SpecialityRepository specialityRepository;

    public Speciality save(Speciality speciality) {
        logger.info("save speciality " + speciality);
        return specialityRepository.save(speciality);
    }

    public List<Speciality> getAllSpecialitiesByUniversity(University university) {
        logger.info("get all specialities in university " + university);
        return specialityRepository.getSpecialitiesByUniversity(university);
    }

    public Speciality getSpeciality(Integer id) {
        logger.info("get speciality by id " + id);
        return specialityRepository.getOne(id);
    }

    public List<Speciality> getAllSpecialities() {
        logger.info("get all specialities ");
        return specialityRepository.findAll();
    }

}
