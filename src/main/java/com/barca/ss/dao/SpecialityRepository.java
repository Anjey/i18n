package com.barca.ss.dao;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    List<Speciality> getSpecialitiesByUniversity(University university);
}
