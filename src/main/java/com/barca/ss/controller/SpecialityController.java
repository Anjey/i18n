package com.barca.ss.controller;

import com.barca.ss.domain.*;
import com.barca.ss.service.SpecialityService;
import com.barca.ss.service.SubjectValueService;
import com.barca.ss.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private SubjectValueService subjectValueService;

    @RequestMapping(value = "/addSpeciality", method = RequestMethod.POST)
    public ModelAndView specialityControl(@Valid @ModelAttribute("speciality") Speciality speciality,
                                          @RequestParam Map<String, String> allParam) {

        University university = universityService.getUniversity(Integer.parseInt(allParam.get("university")));
        if(university != null) {
            speciality.setUniversity(university);
            try {
                specialityService.save(speciality);
                Set<SubjectValue> subjectValueSet = new LinkedHashSet<>();

                String subjectNumber = "subject";
                String subjectNumberValue = "subjectValue";


                for(int i = 0; i < 5; i++) {
                    subjectValueSet.add(new SubjectValue(
                            Subjects.valueOf(allParam.get(subjectNumber + (i + 1))),
                            Double.parseDouble(allParam.get(subjectNumberValue + (i + 1))),
                            speciality));
                }
                subjectValueService.saveAll(subjectValueSet);
//                speciality.setSubjectsAndValue(subjectValueSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/getSpecialities", method = RequestMethod.POST)
    public ModelAndView getSpecialities(@RequestParam("university") Integer id) {

//        Integer id = university.getId();
        University university = universityService.getUniversity(id);

        List<Speciality> specialities = specialityService.getAllSpecialitiesByUniversity(university);
        Set<SubjectValue> subjectValueSet;

        for(Speciality speciality : specialities) {
            subjectValueSet = subjectValueService.getAllSubjectAndValueObjectsBySpeciality(speciality);

            if(subjectValueSet != null) {
                speciality.setSubjectsAndValue(subjectValueSet);
            }
        }

        return new ModelAndView("submissionApply", "specialities", specialities);
    }
}
