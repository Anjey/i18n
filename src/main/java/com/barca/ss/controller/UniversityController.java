package com.barca.ss.controller;

import com.barca.ss.domain.University;
import com.barca.ss.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @RequestMapping(value = "/addUniversity", method = RequestMethod.POST)
    public ModelAndView universityControl(@Valid @ModelAttribute("university") University university) {
        universityService.save(university);

        return new ModelAndView("redirect:/home");
    }
}
