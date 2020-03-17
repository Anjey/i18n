package com.barca.ss.controller;

import com.barca.ss.domain.*;
import com.barca.ss.service.ExamsService;
import com.barca.ss.service.SubmissionOfDocumentService;
import com.barca.ss.service.UniversityService;
import com.barca.ss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private ExamsService examsService;

    @Autowired
    private SubmissionOfDocumentService documentService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }

        userForm.setEncodedImage(null);
        userForm.setAccepted(false);
        userService.save(userForm);

        return "redirect:/home";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error != null) {
            model.addAttribute("error", "Your data are invalid");
        }

        if(logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView welcome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        User user = userService.findUserByEmail(userEmail);

        if(user.getUserRole() == UserRole.ROLE_ADMINISTRATOR) {
            List<User> users = userService.getUsersWithoutAccepting(Boolean.FALSE);
            List<Exams> exams = new ArrayList<>();
            for(User u : users) {
                exams.add(examsService.getCertificateForAccepting(u, Subjects.CERTIFICATE));
            }

            ModelAndView map = new ModelAndView("home");
            map.addObject("exams", exams);
            map.addObject("user", user);

            return map;
        } else if(user.getUserRole() == UserRole.ROLE_USER) {
            List<SubmissionOfDocument> submissions = documentService.getUserSubmission(user);

            if(submissions == null || submissions.size() == 0) {
                return null;
            } else {
                for (SubmissionOfDocument s : submissions) {
                    List<SubmissionOfDocument> allSubmissionFromSpeciality =
                            documentService.getOrderedByAverageMarkSubmission(s.getSpeciality());

                    int place = allSubmissionFromSpeciality.indexOf(s);
                    s.setPlace(++place);
                }
                return new ModelAndView("ownSubmission", "submissions", submissions);
            }
        }

        return new ModelAndView("home");
    }

    @RequestMapping(value = "/aRApply", method = RequestMethod.POST)
    public ModelAndView aRApply(@RequestParam String email, @RequestParam String button) {
        User user = userService.findUserByEmail(email);

        if(button.equalsIgnoreCase("Accept")) {
            user.setAccepted(Boolean.TRUE);
            userService.update(user);
        } else if(button.equalsIgnoreCase("Refuse")) {
//            Set<Exams> exams = examsService.getAllExamsByUser(user);
//            for(Exams e )
//            int id = user.getId();
            examsService.deleteUserExams(user);
            user.setEncodedImage(null);
            userService.update(user);
        }

        return welcome();
    }

    @RequestMapping(value = "/specialityControl", method = RequestMethod.GET)
    public ModelAndView specialityControl() {
        ModelAndView map = new ModelAndView();
        map.addObject("universities", universityService.getAllUniversities());
        map.addObject("subjects", Subjects.values());
        map.addObject("speciality", new Speciality());

        return map;
    }

    @RequestMapping(value = "/examsResult", method = RequestMethod.GET)
    public ModelAndView examsResult() {
        ModelAndView map = new ModelAndView();
        map.addObject("subjects", Subjects.values());
        map.addObject("exam", new Exams());

        return map;
    }

    @RequestMapping(value = "/getSpecialitiesForSubmission", method = RequestMethod.GET)
    public ModelAndView getSpecialitiesForSubmission() {
        ModelAndView map = new ModelAndView();
        map.addObject("universities", universityService.getAllUniversities());
//        map.addObject("submissionOfDocument", new SubmissionOfDocument());
//        map.addObject("specialities", new Speciality())

        return map;
    }

//    @RequestMapping(value = "/submissionApply", method = RequestMethod.GET)
//    public ModelAndView submissionApply() {
//        ModelAndView map = new ModelAndView();
////        map.addObject("universities", universityService.getAllUniversities());
////        map.addObject("submissionOfDocument", new SubmissionOfDocument());
////        map.addObject("specialities", new Speciality())
//
//        return map;
//    }

    @RequestMapping(value = "/university-control", method = RequestMethod.GET)
    public ModelAndView universityControl() {
        return new ModelAndView("universityControl", "university", new University());
    }
}
