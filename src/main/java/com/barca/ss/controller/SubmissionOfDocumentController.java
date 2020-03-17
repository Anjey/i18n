package com.barca.ss.controller;

import com.barca.ss.domain.*;
import com.barca.ss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class SubmissionOfDocumentController {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    private SubmissionOfDocumentService submissionService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private SubjectValueService subjectValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamsService examsService;

    @Autowired
    private UniversityService universityService;

    @RequestMapping(value = "/submissionDocuments", method = RequestMethod.POST)
    public ModelAndView submissionDocuments(@RequestParam("chosen-speciality") String specialityId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        User user = userService.findUserByEmail(userEmail);

        if(!user.getAccepted()) {
            return new ModelAndView("403");
        }

        Speciality speciality = specialityService.getSpeciality(Integer.parseInt(specialityId));
        Set<SubjectValue> subjectValueSet = subjectValueService.getAllSubjectAndValueObjectsBySpeciality(speciality);
        speciality.setSubjectsAndValue(subjectValueSet);

        Set<Exams> exams = examsService.getAllExamsByUser(user);

        List<Double> marks = new ArrayList<>();

        int i = 0, j = 0;
        for(SubjectValue s : subjectValueSet) {
//            int j = 0;
            for(Exams e : exams) {
                if(i < 2) {
                    if(s.getSubjects() == e.getSubject()) {
                        marks.add(s.getValue() * e.getMark());
                        break;
                    }
                } else if(i >= 2 && i < 5 && marks.size() >= 2) {
                    if(s.getSubjects() == e.getSubject()) {
                        if(s.getSubjects().name().equals("CERTIFICATE")) {
                            double certificateMark = (e.getMark() * 200) / 12;
                            marks.add(s.getValue() * certificateMark);
                            break;
                        }
                        marks.add(s.getValue() * e.getMark());
                        break;
                    }
                } else {
                    System.out.println("error");
                    return null;
                }
            }
            i++;
        }

        if(marks.size() == 5) {
            double additionalSubject = marks.get(2);
            double alternativeSubject = marks.get(3);

            if(additionalSubject >= alternativeSubject) {
                marks.remove(3);
            } else {
                marks.remove(2);
            }
        }

        Double averageMark = 0.0;
        for(Double d : marks) {
            averageMark += d;
        }

        //Unchecked
        averageMark = Double.parseDouble(df.format(averageMark));

        SubmissionOfDocument submission = new SubmissionOfDocument(false, averageMark, speciality, user);

        List<SubmissionOfDocument> submissions = submissionService.getUserSubmission(user);
        if(submissions.size() == 0) {
            submission.setPriority(1);
            submissionService.save(submission);
        } else {
            if(submissions.size() < 5) {
                submission.setPriority(submissions.size() + 1);
                submissionService.save(submission);
            }
        }

        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/ownSubmission", method = RequestMethod.GET)
    public ModelAndView getSubmission() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        User user = userService.findUserByEmail(userEmail);
        List<SubmissionOfDocument> submissions = submissionService.getUserSubmission(user);

        if(submissions == null || submissions.size() == 0) {
            return null;
        } else {
            for(SubmissionOfDocument s : submissions) {
                List<SubmissionOfDocument> allSubmissionFromSpeciality =
                        submissionService.getOrderedByAverageMarkSubmission(s.getSpeciality());

                int place = allSubmissionFromSpeciality.indexOf(s);
                s.setPlace(++place);
            }

            ModelAndView map = new ModelAndView();
            map.addObject("submissions", submissions);

            return map;
        }
    }

    @RequestMapping(value = "/submissionOnSpeciality", method = RequestMethod.GET)
    public ModelAndView getSpecialitySubmission() {
        ModelAndView map = new ModelAndView();

        map.addObject("universities", universityService.getAllUniversities());
        map.addObject("specialities", specialityService.getAllSpecialities());

        return map;
    }

    @RequestMapping(value = "/getSubmission", method = RequestMethod.POST)
    public ModelAndView getSubmissionForParticularSpeciality(@RequestParam("speciality") Integer id) {
        Speciality speciality = specialityService.getSpeciality(id);
        List<SubmissionOfDocument> submissions = submissionService.getOrderedByAverageMarkSubmission(speciality);

        int i = 1;
        for(SubmissionOfDocument s : submissions) {
            s.setPlace(i);
            i++;
        }

        return new ModelAndView("ownSubmission", "submissions", submissions);

    }

    @RequestMapping(value = "/stopSubmission", method = RequestMethod.POST)
    public ModelAndView stopSubmission() {
        for(int i = 0; i < 5; i++) {
            studentEnrollmentAlgorithm(i + 1);
        }


        return new ModelAndView("home");// didnt check
    }

    private void studentEnrollmentAlgorithm(Integer priority) {
        List<Speciality> specialities = specialityService.getAllSpecialities();

        for(Speciality s: specialities) {
            List<SubmissionOfDocument> documents = submissionService.getOrderedByAverageMarkSubmission(s);
            int counter = 0;
            for(SubmissionOfDocument sub : documents) {
                if(counter < s.getNumberOfStudentsForEntering()) {
                    if(sub.getPriority() <= priority && !sub.getEntered()) {
                        sub.setEntered(true);
//                        sub.setPlace(counter + 1);
                        User user = sub.getUser();
                        user.setSpeciality(s);
                        sub = submissionService.update(sub);
                        user = userService.update(user);

                        submissionService.deleteAllByUserAndIsEnteredValue(user, Boolean.FALSE);
                    }
                    counter++;
                } else {
                    break;
                }
            }
        }
    }
}