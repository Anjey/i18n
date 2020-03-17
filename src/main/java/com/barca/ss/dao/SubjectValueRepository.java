package com.barca.ss.dao;

import com.barca.ss.domain.Speciality;
import com.barca.ss.domain.SubjectValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SubjectValueRepository extends JpaRepository<SubjectValue, Integer> {
    Set<SubjectValue> getAllBySpecialityOrderById(Speciality speciality);
}
