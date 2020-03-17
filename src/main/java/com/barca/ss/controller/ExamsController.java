package com.barca.ss.controller;

import com.barca.ss.domain.Exams;
import com.barca.ss.domain.Subjects;
import com.barca.ss.domain.User;
import com.barca.ss.service.ExamsService;
import com.barca.ss.service.UserDTOHelper;
import com.barca.ss.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class ExamsController {

    @Autowired
    private ExamsService examsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addExams", method = RequestMethod.POST)
    public ModelAndView examsResult(@RequestParam Map<String, String> allParam,
                                    @RequestParam MultipartFile image) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        User user = userService.update(UserDTOHelper.createEntity(image, userService.findUserByEmail(userEmail)));

        Set<Exams> exams = new LinkedHashSet<>();

        for(int i = 0; i < 5; i++) {
            exams.add(new Exams(
                    Subjects.valueOf(allParam.get("subject" + (i + 1))),
                    Double.parseDouble(allParam.get("mark" + (i + 1))),
                    user
            ));
        }

        examsService.saveAllExams(exams);
//        user.setExams(exams);

        return new ModelAndView("redirect:/home");
    }

//    @RequestMapping(value = "/acceptUsersCertificateMark", method = RequestMethod.GET)
}
